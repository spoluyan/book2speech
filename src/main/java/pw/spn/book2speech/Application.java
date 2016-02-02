package pw.spn.book2speech;

import org.apache.commons.cli.CommandLine;

import com.ivona.services.tts.model.Voice;

import pw.spn.book2speech.model.AWSRegion;
import pw.spn.book2speech.model.CommandLineOption;
import pw.spn.book2speech.model.Gender;
import pw.spn.book2speech.model.Rate;
import pw.spn.book2speech.model.SoundCodec;
import pw.spn.book2speech.model.TransformationOptions;
import pw.spn.book2speech.model.Volume;
import pw.spn.book2speech.service.ArgumentsParser;
import pw.spn.book2speech.service.IvonaAPIClient;

public class Application {
    private static final String DEFAULT_LANGUAGE = "ru-RU";
    private static final String DEFAULT_ENCODING = "UTF-8";

    public static void main(String[] args) {
        ArgumentsParser parser = new ArgumentsParser();

        CommandLine cmd = parser.parseAndValidateArguments(args);

        if (cmd == null) {
            return;
        }

        try {
            String accessKey = cmd.getOptionValue(CommandLineOption.ACCESS_KEY.getShortcut());
            String secretKey = cmd.getOptionValue(CommandLineOption.SECRET_KEY.getShortcut());
            String endpoint = cmd.getOptionValue(CommandLineOption.ENDPOINT.getShortcut());
            if (endpoint != null) {
                endpoint = AWSRegion.fromKey(endpoint).getEndpoint();
            } else {
                endpoint = AWSRegion.EU.getEndpoint();
            }
            String language = cmd.getOptionValue(CommandLineOption.LANGUAGE.getShortcut());
            String name = cmd.getOptionValue(CommandLineOption.NAME.getShortcut());
            String gender = cmd.getOptionValue(CommandLineOption.GENDER.getShortcut());
            if (gender != null) {
                gender = Gender.fromKey(gender).getGender();
            }

            IvonaAPIClient apiClient = new IvonaAPIClient(accessKey, secretKey, endpoint);

            if (cmd.hasOption(CommandLineOption.VOICE_LIST.getShortcut())) {
                Voice voice = new Voice();
                voice.setGender(gender);
                voice.setLanguage(language);
                voice.setName(name);
                apiClient.getVoiceList(voice);
                return;
            }

            TransformationOptions options = new TransformationOptions();
            options.setInputFile(cmd.getOptionValue(CommandLineOption.INPUT_FILE.getShortcut()));
            options.setOutputDir(cmd.getOptionValue(CommandLineOption.OUTPUT_DIR.getShortcut()));

            String codec = cmd.getOptionValue(CommandLineOption.CODEC.getShortcut());
            if (codec != null) {
                codec = SoundCodec.fromKey(codec).getCodec();
            } else {
                codec = SoundCodec.MP3.getCodec();
            }
            options.setCodec(codec);

            String rate = cmd.getOptionValue(CommandLineOption.RATE.getShortcut());
            if (rate != null) {
                rate = Rate.fromKey(rate).getRate();
            } else {
                rate = Rate.MEDIUM.getRate();
            }
            options.setRate(rate);

            String volume = cmd.getOptionValue(CommandLineOption.VOLUME.getShortcut());
            if (volume != null) {
                volume = Volume.fromKey(volume).getVolume();
            } else {
                volume = Volume.MEDIUM.getVolume();
            }
            options.setVolume(volume);

            options.setName(name);

            if (language == null) {
                language = DEFAULT_LANGUAGE;
            }
            options.setLanguage(language);

            if (gender == null) {
                gender = Gender.FEMALE.getGender();
            }

            options.setGender(gender);

            String encoding = cmd.getOptionValue(CommandLineOption.ENCODING.getShortcut(), DEFAULT_ENCODING);
            options.setEncoding(encoding);

            apiClient.transformTextToSpeech(options);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            parser.printHelp();
        }
    }
}

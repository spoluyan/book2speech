package pw.spn.book2speech;

import org.apache.commons.cli.CommandLine;

import com.ivona.services.tts.model.Voice;

import pw.spn.book2speech.model.AWSRegion;
import pw.spn.book2speech.model.CommandLineOption;
import pw.spn.book2speech.model.Gender;
import pw.spn.book2speech.service.ArgumentsParser;
import pw.spn.book2speech.service.IvonaAPIClient;

public class Application {
    public static void main(String[] args) {
        ArgumentsParser parser = new ArgumentsParser();

        CommandLine cmd = parser.parseAndValidateArguments(args);

        if (cmd == null) {
            return;
        }

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

    }
}

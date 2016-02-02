package pw.spn.book2speech.service;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import pw.spn.book2speech.model.CommandLineOption;

public class ArgumentsParser {
    private final Options options;

    public ArgumentsParser() {
        options = new Options();

        Option accessKey = Option.builder(CommandLineOption.ACCESS_KEY.getShortcut()).hasArg().argName("accessKey")
                .desc("Access key for Ivona API.").build();
        options.addOption(accessKey);
        Option secretKey = Option.builder(CommandLineOption.SECRET_KEY.getShortcut()).hasArg().argName("secretKey")
                .desc("Secret key for Ivona API.").build();
        options.addOption(secretKey);
        Option input = Option.builder(CommandLineOption.INPUT_FILE.getShortcut()).hasArg().argName("inputFile")
                .desc("Path to input file.").build();
        options.addOption(input);
        Option output = Option.builder(CommandLineOption.OUTPUT_DIR.getShortcut()).hasArg().argName("outputDir")
                .desc("Path to output directory.").build();
        options.addOption(output);
        Option endpoint = Option.builder(CommandLineOption.ENDPOINT.getShortcut()).hasArg().argName("[eu | use | usw]")
                .desc("AWS region.").build();
        options.addOption(endpoint);
        Option codec = Option.builder(CommandLineOption.CODEC.getShortcut()).hasArg().argName("[mp3 | mp4 | ogg]")
                .desc("Preferred sound codec.").build();
        options.addOption(codec);
        Option rate = Option.builder(CommandLineOption.RATE.getShortcut()).hasArg().argName("[xs | s | m | f | xf]")
                .desc("The speed of speech.").build();
        options.addOption(rate);
        Option volume = Option.builder(CommandLineOption.VOLUME.getShortcut()).hasArg()
                .argName("[xxs | xs | s | m | l | xl]").desc("The volume of speech.").build();
        options.addOption(volume);
        Option name = Option.builder(CommandLineOption.NAME.getShortcut()).hasArg().argName("name")
                .desc("Voice name returned by the voiceList.").build();
        options.addOption(name);
        Option language = Option.builder(CommandLineOption.LANGUAGE.getShortcut()).hasArg().argName("language")
                .desc("The language BCP47 code of the voice.").build();
        options.addOption(language);
        Option gender = Option.builder(CommandLineOption.GENDER.getShortcut()).hasArg().argName("[f | m]")
                .desc("Gender of the voice.").build();
        options.addOption(gender);
        Option voiceList = Option.builder(CommandLineOption.VOICE_LIST.getShortcut())
                .desc("Return all available voices.").build();
        options.addOption(voiceList);
        Option help = Option.builder(CommandLineOption.HELP.getShortcut()).desc("Show help.").build();
        options.addOption(help);
        Option encoding = Option.builder(CommandLineOption.ENCODING.getShortcut()).hasArg().argName("encoding")
                .desc("Specify input file encoding.").build();
        options.addOption(encoding);
    }

    public CommandLine parseAndValidateArguments(String[] args) {
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            printHelp();
            return null;
        }

        if (cmd.hasOption(CommandLineOption.HELP.getShortcut())) {
            printHelp();
            return null;
        }

        if (!cmd.hasOption(CommandLineOption.ACCESS_KEY.getShortcut())
                || !cmd.hasOption(CommandLineOption.SECRET_KEY.getShortcut())) {
            System.err.println("Parameters accessKey and secretKey are required.");
            printHelp();
            return null;
        }

        if (!cmd.hasOption(CommandLineOption.VOICE_LIST.getShortcut())) {
            if (!cmd.hasOption(CommandLineOption.INPUT_FILE.getShortcut())
                    || !cmd.hasOption(CommandLineOption.OUTPUT_DIR.getShortcut())) {
                System.err.println("Parameters inputFile and outputDir are required.");
                printHelp();
                return null;
            }
        }

        return cmd;
    }

    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar book2speech.jar", options, true);
    }
}

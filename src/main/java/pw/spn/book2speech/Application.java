package pw.spn.book2speech;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Application {
    private static Options options;

    public static void main(String[] args) {
        createOptions();

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            printHelp();
        }
    }

    private static void createOptions() {
        options = new Options();

        Option accessKey = Option.builder("a").longOpt("accessKey").hasArg().argName("accessKey")
                .desc("Access key for Ivona API.").build();
        options.addOption(accessKey);
        Option secretKey = Option.builder("s").longOpt("secretKey").hasArg().argName("secretKey")
                .desc("Secret key for Ivona API.").build();
        options.addOption(secretKey);
        Option input = Option.builder("i").longOpt("input").hasArg().argName("inputFile").desc("Path to input file.")
                .build();
        options.addOption(input);
        Option output = Option.builder("o").longOpt("output").hasArg().argName("outputDir")
                .desc("Path to output directory.").build();
        options.addOption(output);
        Option endpoint = Option.builder("e").longOpt("endpoint").hasArg().argName("endpoint").desc("AWS region.")
                .build();
        options.addOption(endpoint);
        Option codec = Option.builder("c").longOpt("codec").hasArg().argName("codec").desc("Preferred sound codec.")
                .build();
        options.addOption(codec);
        Option rate = Option.builder("r").longOpt("rate").hasArg().argName("rate").desc("The speed of speech.").build();
        options.addOption(rate);
        Option volume = Option.builder("v").longOpt("volume").hasArg().argName("volume").desc("The volume of speech.")
                .build();
        options.addOption(volume);
        Option name = Option.builder("n").longOpt("name").hasArg().argName("name")
                .desc("Any voice name returned by the voiceList.").build();
        options.addOption(name);
        Option language = Option.builder("l").longOpt("language").hasArg().argName("language")
                .desc("The language code of the voice.").build();
        options.addOption(language);
        Option gender = Option.builder("g").longOpt("gender").hasArg().argName("gender").desc("Gender of the voice.")
                .build();
        options.addOption(gender);
        Option voiceList = Option.builder("vl").longOpt("voiceList").desc("Return all available voices.").build();
        options.addOption(voiceList);
        Option help = Option.builder("h").longOpt("help").desc("Show help.").build();
        options.addOption(help);
    }

    private static void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar book2speech.jar", options, true);
    }
}

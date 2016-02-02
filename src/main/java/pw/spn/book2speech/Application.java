package pw.spn.book2speech;

import java.io.File;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Application {
    private static Options options;

    public static void main(String[] args) {
        createOptions();
    }

    private static void createOptions() {
        options = new Options();

        Option accessKey = Option.builder("a").longOpt("accessKey").hasArg().argName("accessKey").type(String.class)
                .required().desc("Access key for Ivona API.").build();
        Option secretKey = Option.builder("s").longOpt("secretKey").hasArg().argName("secretKey").type(String.class)
                .required().desc("Secret key for Ivona API.").build();
        Option input = Option.builder("i").longOpt("input").hasArg().argName("inputFile").type(File.class)
                .desc("Path to input file.").build();
        Option output = Option.builder("o").longOpt("output").hasArg().argName("outputDir").type(File.class)
                .desc("Path to output directory.").build();
    }

}

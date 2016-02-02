package pw.spn.book2speech;

import org.apache.commons.cli.CommandLine;
import pw.spn.book2speech.service.ArgumentsParser;

public class Application {
    public static void main(String[] args) {
        ArgumentsParser parser = new ArgumentsParser();

        CommandLine cmd = parser.parseAndValidateArguments(args);
    }
}

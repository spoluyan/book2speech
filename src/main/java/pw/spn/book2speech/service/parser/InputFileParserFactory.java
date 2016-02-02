package pw.spn.book2speech.service.parser;

import java.io.File;

import pw.spn.book2speech.service.parser.impl.DefaultInputFileParser;

public class InputFileParserFactory {
    private InputFileParserFactory() {
    }

    public static InputFileParser getParser(File inputFile) {
        return new DefaultInputFileParser();
    }
}

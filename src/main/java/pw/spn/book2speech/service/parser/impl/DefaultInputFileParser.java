package pw.spn.book2speech.service.parser.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

import pw.spn.book2speech.service.parser.InputFileParser;

public class DefaultInputFileParser implements InputFileParser {

    @Override
    public List<String> toPlainText(File inputFile, String encoding) {
        try {
            return Files.readAllLines(inputFile.toPath(), Charset.forName(encoding));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}

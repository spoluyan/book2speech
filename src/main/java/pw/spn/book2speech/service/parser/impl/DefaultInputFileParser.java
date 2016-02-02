package pw.spn.book2speech.service.parser.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

import pw.spn.book2speech.service.parser.InputFileParser;

public class DefaultInputFileParser implements InputFileParser {

    @Override
    public List<String> toPlainText(File inputFile) {
        try {
            return Files.readAllLines(inputFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}

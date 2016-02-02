package pw.spn.book2speech.service.parser;

import java.io.File;
import java.util.List;

public interface InputFileParser {
    List<String> toPlainText(File inputFile);
}

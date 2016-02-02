package pw.spn.book2speech.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.util.IOUtils;
import com.ivona.services.tts.IvonaSpeechCloudClient;
import com.ivona.services.tts.model.CreateSpeechRequest;
import com.ivona.services.tts.model.CreateSpeechResult;
import com.ivona.services.tts.model.Input;
import com.ivona.services.tts.model.ListVoicesRequest;
import com.ivona.services.tts.model.ListVoicesResult;
import com.ivona.services.tts.model.OutputFormat;
import com.ivona.services.tts.model.Parameters;
import com.ivona.services.tts.model.Voice;

import pw.spn.book2speech.model.TransformationOptions;
import pw.spn.book2speech.service.parser.InputFileParserFactory;

public class IvonaAPIClient {
    private static final int TEXT_BLOCK_MAX_SIZE = 8192;
    private static final short SENTENCE_BREAK = 1000;

    private final IvonaSpeechCloudClient cloudClient;

    public IvonaAPIClient(String accessKey, String secretKey, String endpoint) {
        cloudClient = new IvonaSpeechCloudClient(new BasicAWSCredentials(accessKey, secretKey));
        cloudClient.setEndpoint(endpoint);
    }

    public void getVoiceList(Voice filter) {
        ListVoicesRequest allVoicesRequest = new ListVoicesRequest();
        allVoicesRequest.setVoice(filter);
        ListVoicesResult allVoicesResult = cloudClient.listVoices(allVoicesRequest);
        System.out.println("Available voices (" + allVoicesResult.getVoices().size() + "):");
        allVoicesResult.getVoices().forEach(voice -> {
            System.out.println("---------");
            System.out.println("Language: " + voice.getLanguage());
            System.out.println("Name: " + voice.getName());
            System.out.println("Gender: " + voice.getGender());
        });
    }

    public void transformTextToSpeech(TransformationOptions options) {
        System.out.println("Transforming " + options.getInputFile() + " to speech.");
        System.out.println("Output dir is " + options.getOutputDir());

        File inputFile = new File(options.getInputFile());
        File outputDir = new File(options.getOutputDir());

        validateFiles(inputFile, outputDir);

        List<String> content = readFile(inputFile, options.getEncoding());

        int numbersInOutputFilesNames = calculateLengthOfNumber(content.size());

        OutputFormat outputFormat = new OutputFormat();
        outputFormat.setCodec(options.getCodec());

        Voice voice = new Voice();
        voice.setGender(options.getGender());
        voice.setLanguage(options.getLanguage());
        voice.setName(options.getName());

        Parameters parameters = new Parameters();
        parameters.setRate(options.getRate());
        parameters.setVolume(options.getVolume());
        parameters.setSentenceBreak(SENTENCE_BREAK);

        AtomicInteger counter = new AtomicInteger(0);
        IntStream.rangeClosed(1, content.size()).forEach(i -> {
            String text = content.get(i - 1);
            System.out.println("Processing file " + counter.incrementAndGet() + " of " + content.size());

            CreateSpeechRequest createSpeechRequest = new CreateSpeechRequest();

            Input input = new Input();
            input.setData(text);
            createSpeechRequest.setInput(input);

            createSpeechRequest.setOutputFormat(outputFormat);

            createSpeechRequest.setVoice(voice);

            createSpeechRequest.setParameters(parameters);

            CreateSpeechResult createSpeechResult = cloudClient.createSpeech(createSpeechRequest);
            writeFile(outputDir, numbersInOutputFilesNames, i, options.getCodec(), createSpeechResult.getBody());
        });
        System.out.println("Done!");
    }

    private void validateFiles(File inputFile, File outputDir) {
        if (!inputFile.exists()) {
            System.err.println("Input file does not exists.");
            System.exit(0);
        }
        if (!inputFile.isFile()) {
            System.err.println("Input file is not a file.");
            System.exit(0);
        }
        if (!outputDir.exists() || !outputDir.isDirectory()) {
            boolean outputDirCreated = outputDir.mkdirs();
            if (!outputDirCreated) {
                System.err.println("Unable to create output dir.");
                System.exit(0);
            }
        }
    }

    private List<String> readFile(File inputFile, String encoding) {
        List<String> lines = InputFileParserFactory.getParser(inputFile).toPlainText(inputFile, encoding);

        List<String> concated = new ArrayList<>();

        IntStream.range(0, lines.size()).forEachOrdered(i -> {
            String line = lines.get(i);
            if (line.isEmpty()) {
                return;
            }
            if (concated.size() == 0) {
                concated.add(line);
                return;
            }
            String newLine = concated.get(concated.size() - 1) + line;
            int length = newLine.length();
            if (length <= TEXT_BLOCK_MAX_SIZE) {
                concated.remove(concated.size() - 1);
                concated.add(newLine);
            } else {
                concated.add(line);
            }
        });

        return concated;
    }

    private int calculateLengthOfNumber(int number) {
        int result = 0;

        for (int i = number; i > 0; i = i / 10) {
            result++;
        }

        return result;
    }

    private void writeFile(File outputDir, int numbersInOutputFilesNames, int index, String codec, InputStream stream) {
        String fileName = generateFileName(outputDir, numbersInOutputFilesNames, index, codec);
        Path outputFile = new File(fileName).toPath();
        try {
            byte[] content = IOUtils.toByteArray(stream);
            Files.write(outputFile, content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(stream, null);
        }
    }

    private String generateFileName(File outputDir, int numbersInOutputFilesNames, int index, String codec) {
        int reservedNumbers = calculateLengthOfNumber(index);
        StringBuilder name = new StringBuilder(outputDir.getAbsolutePath()).append(File.separatorChar);
        for (int i = 0; i < numbersInOutputFilesNames - reservedNumbers; i++) {
            name.append('0');
        }
        name.append(index).append('.').append(codec.toLowerCase());
        return name.toString();
    }
}

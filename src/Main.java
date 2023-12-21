import java.io.*;

/**
 * Project Part 3: LLVM
 */
public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -jar part3.jar [FILE]");
            return;
        }

        try {
            processFile(args[0]);
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private static void processFile(String filePath) throws IOException, Exception {
        ParseTree parseTree;
        try (FileReader codeSource = new FileReader(filePath)) {
            Parser parser = new Parser(codeSource);
            parseTree = parser.parse();
        }
        // generate LLVM code
        parseTree.program();
        String fileNameWithoutExtension = extractFileNameWithoutExtension(filePath);
        String llvmCode = parseTree.getLlvmCodeOutput().toString();
        System.out.println(llvmCode);

        saveToFile(llvmCode, "more/results", fileNameWithoutExtension + ".ll");
    }

    private static String extractFileNameWithoutExtension(String filePath) {
        String fileName = new File(filePath).getName();
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }

    private static void saveToFile(String content, String directoryName, String fileName) throws IOException {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File outputFile = new File(directory, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(content);
        }
        System.out.println("Output saved to: " + outputFile.getAbsolutePath());
    }
}

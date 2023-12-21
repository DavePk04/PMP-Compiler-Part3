import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * Project Part 2: Parser
 *
 * @author Marie Van Den Bogaard, Léo Exibard, Gilles Geeraerts, Sarah Winter, edited by Mathieu Sassolas
 *
 */

public class Main{
    /**
     *
     * The parser
     *
     * @param args  The argument(s) given to the program
     * @throws IOException java.io.IOException if an I/O-Error occurs
     * @throws FileNotFoundException java.io.FileNotFoundException if the specified file does not exist
     *
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, SecurityException, Exception{
        // Display the usage when no arguments are given
        if(args.length == 0){
            System.out.println("Usage:  java -jar part2.jar [OPTION] [FILE]\n"
                               + "\tOPTION:\n"
                               + "\t -wt (write-tree) filename.tex: writes latex tree to filename.tex\n"
                               + "\tFILE:\n"
                               + "\tA .ppm file containing a PascalMaisPresque program\n"
                               );
            System.exit(0);
        } else {
            boolean writeTree = false;
            boolean fullOutput = false;
            BufferedWriter bwTree = null;
            FileWriter fwTree = null;
            FileReader codeSource = null;
            try {
                codeSource = new FileReader(args[args.length-1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ParseTree parseTree = null;
            String tex="\\documentclass{standalone}\\begin{document}Parsing error, no tree produced.\\end{document}";

            for (int i = 0 ; i < args.length; i++) {
                if (args[i].equals("-wt") || args[i].equals("--write-tree")) {
                    writeTree = true;
                    try {
                        fwTree = new FileWriter(args[i+1]);
                        bwTree = new BufferedWriter(fwTree);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (args[i].equals("-dr") || args[i].equals("--display-rules") ) {
                    fullOutput = true;
                }
            }
            Parser parser = new Parser(codeSource);
            if (fullOutput) {parser.displayFullRules();}
            try {
                parseTree = parser.parse();
                parseTree.program();
                String inputFileName = args[args.length-1];
                String fileNameWithoutPath = inputFileName.substring(inputFileName.lastIndexOf('/') + 1);
                String fileNameWithoutExtension = fileNameWithoutPath.substring(0, fileNameWithoutPath.lastIndexOf('.'));
                System.out.println(fileNameWithoutExtension);
                System.out.println(parseTree.getCodeToOut(
                        ));
                String codeToOut = String.valueOf(parseTree.getCodeToOut());
                StringBuilder codeToOutRes = new StringBuilder(codeToOut);
                // create a directory named results if it does not exist
                // in this directory, we will put the output files : fileNameWithoutExtension.ll
                // write the contents of codeToOut to the output file
                File directory = new File("results");
                if (! directory.exists()){
                    directory.mkdir();
                }
                String outputFileName = "results/" + fileNameWithoutExtension + ".ll";
                try {
                    // Create a new file
                    File file = new File(outputFileName);

                    // Create a file writer object
                    FileWriter fileWriter = new FileWriter(file);

                    // Create a buffered writer object
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    // Write the contents of codeToOut to the output file
                    bufferedWriter.write(codeToOutRes.toString());

                    // Close the buffered writer object
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                String codeToOut = String.valueOf(parseTree.getCodeToOut());
//                // Construct the output file name with .txt extension
//                String outputFileName = "results/" + fileNameWithoutExtension + ".txt";
////
//                // Create a StringBuilder object
//                StringBuilder codeToOutRes = new StringBuilder(codeToOut);
//
//                // Write the contents of codeToOut to the output file only if the file does not exist
//                if (new File(outputFileName).exists() == false) {
//                    try {
//                        // Create a new file
//                        File file = new File(outputFileName);
//
//                        // Create a file writer object
//                        FileWriter fileWriter = new FileWriter(file);
//
//                        // Create a buffered writer object
//                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//
//                        // Write the contents of codeToOut to the output file
//                        bufferedWriter.write(codeToOutRes.toString());
//
//                        // Close the buffered writer object
//                        bufferedWriter.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                // if the content codeToOut is equal to the content of the output file, then the program is correct
//                // otherwise, the program is incorrect
//                if (codeToOut.equals(new String(Files.readAllBytes(Paths.get(outputFileName))))) {
//                    System.out.println("The program is correct");
//                } else {
//                    System.out.println("The program is incorrect");
//                }


                if (writeTree) {tex=parseTree.toLaTeX();};
            } catch (ParseException e) {
                System.out.println("Error:> " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error:> " + e);
            }
            if (writeTree) {
                try {
                    bwTree.write(tex);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bwTree != null)
                            bwTree.close();
                        if (fwTree != null)
                            fwTree.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}

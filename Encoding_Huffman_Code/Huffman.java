/*
 * Name:
 *  Huffman.java
 *
 * Version:
 *  1.0
 *
 * Revisions:
 *  None
 */

import java.io.*;
import java.util.Scanner;

/**
 * Class to generate Huffman encoding for all characters of a file
 *
 * @author Anuradha Bhave       ab5890
 * @author Himanshi Chetwani    hc9165
 */
public class Huffman {
    private int MAX_SIZE;
    private static int[] frequencyArray;
    private static HuffmanNode[] nodeArray;
    private int[] copyArray;
    private static File file;
    private static PrintWriter write;
    private static final char UTF_PREAMBLE = '\uFEFF';

    /**
     * Method to identify character having maximum ASCII value
     *
     * @throws FileNotFoundException exception thrown if file does not exist
     */
    private void findMaxCharacter() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream(file));
        while (sc.hasNext()) {
            String currentLine = sc.nextLine();
            for (int index = 0; index < currentLine.length(); index++) {
                if ((int) currentLine.charAt(index) > MAX_SIZE) {
                    MAX_SIZE = (int) currentLine.charAt(index);
                }
            }

        }
    }

    /**
     * Populate array based on occurrence of character
     *
     * @param input single character read from file
     */
    private void incrementFrequency(char input) {
        frequencyArray[(int) input]++;
    }

    /**
     * Parse one line of the file character by character
     *
     * @param line single line of inout file
     */
    private void breakInParts(String line) {
        for (int index = 0; index < line.length(); index++) {
            incrementFrequency(line.charAt(index));
        }
        makeArrayCopy();
    }

    /**
     * Function to generate copy of array
     */
    private void makeArrayCopy() {
        for (int i = 0; i < frequencyArray.length; i++) {
            copyArray[i] = frequencyArray[i];
        }
    }

    /**
     * Method to concatenate Huffman code for a character
     *
     * @param root Huffman Node
     * @param s    Contains Huffman code for the character
     */
    private void concatenateHuffmanCode(HuffmanNode root, String s) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            writeToCSVFile(s, root.c);
            return;
        }
        concatenateHuffmanCode(root.left, s + "0");
        concatenateHuffmanCode(root.right, s + "1");
    }

    /**
     * Write (character, binary representation) pair to .csv file
     *
     * @param code      binary Huffman code for character
     * @param character individual character of input file
     */
    private void writeToCSVFile(String code, char character) {
        write.println(character + "," + code);

    }

    /**
     * Generate encoded equivalent of the original text file
     *
     * @throws IOException thrown when I/O exception has occurred
     */
    private void encodeTextFile() throws IOException {
        String[] encodingsArray = new String[MAX_SIZE + 1];
        int numberOfPaddedZeroes = 0;
        File readCSV = new File("Output.csv");
        BufferedReader parseCSV = new BufferedReader(new FileReader(readCSV));
        File finalFile = new File("EncodedOutput.txt");
        if (!finalFile.exists()) {
            finalFile.createNewFile();
        }
        Scanner inputFileReader =
                new Scanner(new InputStreamReader(new FileInputStream(file)));
        String line;
        DataOutputStream writeToFile =
                new DataOutputStream(new FileOutputStream(finalFile));
        while ((line = parseCSV.readLine()) != null) {
            String temp[] = line.split(",");
            char c;
            if (temp.length == 3) { //edge condition, when the char is a comma
                c = ',';
                encodingsArray[(int) c] = temp[2];
            } else {
                c = temp[0].charAt(0);
                encodingsArray[(int) c] = temp[1];
            }
        }
        byte singleByte = 0b00000000;
        byte bitCounter = 0;
        while (inputFileReader.hasNext()) {
            String fileLine = inputFileReader.nextLine();
            for (int index = 0; index < fileLine.length(); index++) {
                char c = fileLine.charAt(index);
                if (c == UTF_PREAMBLE) {
                    continue;
                }
                String bitRepresentation =
                        (encodingsArray[(int) c]);
                //Now that we have a bit representation, accumulate it to a byte
                //and write that byte to the file whenever it's filled.
                for (int bitIndex = 0; bitIndex < bitRepresentation.length();
                     bitIndex++) {
                    char bit = bitRepresentation.charAt(bitIndex);
                    if (bit == '1') {
                        singleByte |= 0b1;
                    } else {
                        singleByte |= 0b0;
                    }
                    bitCounter++;
                    if (bitCounter == 8) {
                        writeToFile.writeByte(singleByte);
                        bitCounter = 0;
                        singleByte = 0;
                    }
                    singleByte <<= 1;
                }
            }
        }
        if (bitCounter != 0) {
            numberOfPaddedZeroes = 8 - bitCounter;
            singleByte <<= numberOfPaddedZeroes;
            writeToFile.writeByte(singleByte);
        }
        inputFileReader.close();
        parseCSV.close();
        createOutputFile(finalFile, numberOfPaddedZeroes);
    }

    /**
     *  Generates output file with header
     *
     *
     * @param output      Encoded file
     * @param offsetCount Number of 0s appended to last byte, which is the
     *                    header
     * @throws IOException when I/O exception occurs
     */
    private void createOutputFile(File output, int offsetCount) throws
            IOException {
        int currentByte;
        File outputFile = new File("Output.txt");
        DataInputStream input =
                new DataInputStream(new FileInputStream(output));
        DataOutputStream out =
                new DataOutputStream(new FileOutputStream(outputFile));
        out.writeByte(offsetCount);
        while ((currentByte = input.read()) != -1) {
            out.writeByte(currentByte);
        }
        output.deleteOnExit();
        input.close();
        out.close();

    }

    /**
     * The main method
     *
     * @param args name of text file
     * @throws IOException thrown when I/O exception occurs
     */
    public static void main(String[] args) throws IOException {
        Scanner sc2 = null;
        String fname = args[0];
        HuffmanNode nodeObj = new HuffmanNode();
        int count = 0;
        file = new File(fname);
        try {
            sc2 = new Scanner(new FileInputStream(file));
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        }
        File opFile = new File("Output.csv");
        if (!opFile.exists()) {
            opFile.createNewFile();
        }
        write = new PrintWriter(opFile);

        Huffman testObj = new Huffman();
        testObj.findMaxCharacter();
        frequencyArray = new int[testObj.MAX_SIZE + 1];
        testObj.copyArray = new int[testObj.MAX_SIZE + 1];
        assert sc2 != null;
        while (sc2.hasNext()) {
            testObj.breakInParts(sc2.nextLine());

        }
        for (int aFrequencyArray : frequencyArray) {
            if (aFrequencyArray != 0) {
                count++;
            }
        }
        nodeArray = new HuffmanNode[count];
        for (int i = 0, index = 0; i < frequencyArray.length; i++) {
            int check = frequencyArray[i];
            if (check != 0) {
                nodeArray[index] = new HuffmanNode(frequencyArray[i], (char) i);
                index++;
            }
        }
        do {
            nodeObj.sort(nodeArray);
            HuffmanNode firstElement = nodeObj.delete(nodeArray);
            nodeArray = nodeObj.truncateElement(nodeArray);
            HuffmanNode secondElement = nodeObj.delete(nodeArray);
            nodeArray = nodeObj.truncateElement(nodeArray);
            int sum = firstElement.frequency + secondElement.frequency;
            HuffmanNode sumNode = new HuffmanNode(sum, firstElement,
                    secondElement);
            HuffmanNode[] temp;
            temp = nodeArray;
            nodeArray = new HuffmanNode[temp.length + 1];
            for (int i = 0; i < temp.length; i++) {
                nodeArray[i] = temp[i];
            }
            nodeArray[nodeArray.length - 1] = sumNode;
            nodeObj.sort(nodeArray);
        } while (nodeArray.length != 1);
        testObj.concatenateHuffmanCode(nodeArray[0], "");
        write.close();
        testObj.encodeTextFile();

    }
}
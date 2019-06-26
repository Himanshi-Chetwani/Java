/*
 * Name:
 *  Huffman.java
 *
 * Version:
 *  1.1
 *
 * Revisions:
 *  Decode() added
 */

import java.io.*;
import java.util.Scanner;

/**
 * Class to generate Huffman encoding for all characters of a inputFile
 *
 * @author Anuradha Bhave       ab5890
 * @author Himanshi Chetwani    hc9165
 */
public class Huffman {
    private int MAX_SIZE;
    private int[] frequencyArray;
    private HuffmanNode[] nodeArray;
    private File inputFile, outputFile;
    private BufferedWriter bufferedWriter;
    private int position;
    private boolean flagForDecode = false;
    private boolean flagForEncode = false;

    /**
     * Method to identify character having maximum ASCII value
     *
     * @throws FileNotFoundException exception thrown if inputFile does not
     * exist
     */
    private void findMaxCharacter(File inputFile) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream(inputFile));
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
     * @param input single character read from inputFile
     */
    private void incrementFrequency(char input) {
        frequencyArray[(int) input]++;
    }

    /**
     * Parse the input file byte by byte to populate frequency array
     * @param inputFile original text file, provided by user
     * @throws IOException if I/O exception occurs in read()
     */
    private void populateFrequencyArray(File inputFile) throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader
                        (new FileInputStream(inputFile)));

        int readInt;
        while ((readInt = bufferedReader.read()) != -1) {
            if(readInt == 13){
                incrementFrequency('\r');
            }
            char c = (char) readInt;
            incrementFrequency(c);
        }
        bufferedReader.close();
    }

    /**
     * Creates array of Huffman nodes from available frequencies
     */
    private void createNodeArray() {
        int count = 0;
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
        HuffmanNode nodeObj = new HuffmanNode();
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

    }

    /**
     * Concatenate HuffmanCode by traversing tree
     * @param root first element of nodeArray containing Huffman nodes
     * @param s contains encoding
     * @param bufferedWriter BufferedWriter object to write to CSV file
     * @throws IOException if I/O Exception occurs in writeToCSVFile()
     */
    private void writeHuffmanCodeToFile(HuffmanNode root, String s,
                                        BufferedWriter bufferedWriter)
            throws IOException {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            writeToCSVFile(s, root.c, bufferedWriter);
            return;
        }
        writeHuffmanCodeToFile(root.left, s + "0", bufferedWriter);
        writeHuffmanCodeToFile(root.right, s + "1", bufferedWriter);
    }

    /**
     * Write (character, binary representation) pair to .csv file
     * We check for edge cases of newline, and carriage return since both
     * must be appended with an escape character before being stored in a file
     *
     * @param code      binary Huffman code for character
     * @param character individual character of input inputFile
     * @param bufferedWriter BufferedWriter to write to csv file
     *
     */
    private void writeToCSVFile(String code, char character,
                                BufferedWriter bufferedWriter) throws IOException
    {
        if (character == '\n') {
            bufferedWriter.write("\\n" + "," + code);
        }
        else if (character == '\r') {
            bufferedWriter.write("\\r" + "," + code);
        }
        else {
            bufferedWriter.write(character + "," + code);
        }
        bufferedWriter.write("\n");
    }

    /**
     * Generate encoded equivalent of the original text inputFile
     *
     * @throws IOException thrown when file is not found(FileNotFound
     * exception is a subclass), or when readLine() throws an exception
     */
    private void encodeTextFile() throws IOException {
        String[] encodingsArray = new String[MAX_SIZE + 1];
        int numberOfPaddedZeroes = 0; // is the header, keeps a count of number
        // of bits appended to the last byte.
        File csvFile = new File("Output.csv");
        BufferedReader csvFileBufferedReader = new BufferedReader
                (new FileReader(csvFile));
        File outputFileWithoutHeader = new File("EncodedOutput.txt");
        BufferedReader inputFileReader =
                new BufferedReader(new InputStreamReader(new
                        FileInputStream(inputFile)));
        String line;
        DataOutputStream writeToFile =
                new DataOutputStream(new FileOutputStream
                        (outputFileWithoutHeader));
        while ((line = csvFileBufferedReader.readLine()) != null) {
            String temp[] = line.split(",");
            char c;
            if (temp.length == 3) { //edge condition, when the char is a comma
                c = ',';
                encodingsArray[(int) c] = temp[2];
            } else { // checks if the character is a carriage return or new
                // line. Both are stored with an appended escape character,
                // which must not be considered
                switch (temp[0]) {
                    case "\\r":
                        c = '\r';
                        break;
                    case "\\n":
                        c = '\n';
                        break;
                    default:
                        c = temp[0].charAt(0);
                        break;
                }
                encodingsArray[(int) c] = temp[1];
            }
        }
        //Since we cannot directly write bits, we must bundle them into a
        // byte before writing to file
        byte singleByte = 0b00000000;
        byte bitCounter = 0;
        int readInt;    //.read() returns an int, use this to store it. This is
        //later converted to char

        while ((readInt = inputFileReader.read()) != -1) {
            char c = (char) readInt;
            String bitRepresentation =
                    (encodingsArray[(int) c]);
            //Now that we have a bit representation, accumulate it to a byte
            //and write that byte to the inputFile whenever it's filled.
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

        singleByte >>= 1;
        if (bitCounter != 0) { // Append necessary number of 0s to make one byte
            numberOfPaddedZeroes = 8 - bitCounter;
            singleByte <<= numberOfPaddedZeroes;
            writeToFile.writeByte(singleByte);

        }
        inputFileReader.close();
        csvFileBufferedReader.close();
        addHeaderToOutputFile(outputFileWithoutHeader, numberOfPaddedZeroes);
    }

    /**
     * Stores an element of nodeArray, which is the tree for access during
     * decoding
     * @throws IOException when file is not found(FileNotFoundException is a
     * subclass of IO) or when data stream object invokes method
     */
    private void createSerializableFileForArray() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(
                "SerializedArrayFile.ser"));
        ObjectOutputStream objectoutputStream =
                new ObjectOutputStream(fileOutputStream);
        objectoutputStream.writeObject(nodeArray[0]);
        objectoutputStream.close();
        fileOutputStream.close();

    }

    /**
     * Adds a header to the output inputFile.
     *
     * @param output      Encoded inputFile
     * @param offsetCount Number of 0s appended to last byte, which is the
     *                    header
     * @throws IOException when file not found(FileNotFoundException is a
     * subclass of IO), or when data stream object invokes a method
     */
    private void addHeaderToOutputFile(File output, int offsetCount) throws
            IOException{
        int currentByte;
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
     * Decodes the encoded file
     * @param encodedFile file encoded with Huffman codes
     * @throws IOException thrown if file not found or by a data stream
     * method invocation
     * @throws ClassNotFoundException thrown if serialized object does not
     * load correctly
     */

    private void decodeFile(File encodedFile) throws IOException,
            ClassNotFoundException {
        int offset;
        byte currentByte;
        File serializedFile = new File("SerializedArrayFile.ser");
        StringBuilder output = new StringBuilder();
        BufferedWriter writeToFile =
                new BufferedWriter(new FileWriter(outputFile));
        DataInputStream readEncoded =
                new DataInputStream(new FileInputStream(encodedFile));
        offset = readEncoded.readByte();
        while (true) {
            try {
                currentByte = readEncoded.readByte();
                for (int i = 7; i >= 0; i--) {
                    if (((currentByte >> i) & 0b1) == 0) {
                        output.append("0");
                    } else {
                        output.append("1");
                    }
                }
            } catch (EOFException e) {
                break;
            }
        }

        StringBuilder finalResultBuilder = new StringBuilder();
        String fileContent = output.toString();

        int difference = fileContent.length() - offset;

        FileInputStream serFileReader = new FileInputStream(serializedFile);
        ObjectInputStream objectReader = new ObjectInputStream(serFileReader);
        HuffmanNode root = (HuffmanNode) objectReader.readObject();
        while (position < difference) {
            char c;
            try {
                c = traverseTree(fileContent, root);
            } catch (StringIndexOutOfBoundsException s) {
                break;
            }

            finalResultBuilder.append(c);
        }
        String finalResult = finalResultBuilder.toString();
        writeToFile.write(finalResult);
        writeToFile.close();
        readEncoded.close();
        serFileReader.close();
        serializedFile.deleteOnExit();

    }

    /**
     * Recursive function to traverse the tree containing characters and
     * respective Huffman codes to generate decoded character
     * @param fileContent the encoded file passed as a String
     * @param root which is the first Huffman Node of the tree
     * @return decoded root
     */
    private char traverseTree(String fileContent, HuffmanNode root) {

        if (root.left == null && root.right == null) {
            return root.c;
        }
        char c = fileContent.charAt(position++);
        if (c == '0') {
            return traverseTree(fileContent, root.left);
        } else {
            return traverseTree(fileContent, root.right);
        }
    }

    /**
     * Parse command line arguments
     * @param commandLineArguments provided by user, contain names of files
     *                             and type of operation
     * @throws IOException if thrown by encode()/decode()
     * @throws ClassNotFoundException if thrown by decode()
     */
    private void parseArguments(String[] commandLineArguments)
            throws IOException, ClassNotFoundException {
        int inputFileFlag, outputFileFlag;
        for (int index = 0; index < commandLineArguments.length; index++) {
            if (commandLineArguments[index].equals("-i")) {
                inputFileFlag = index;
                inputFile = new File(commandLineArguments[inputFileFlag + 1]);
            }
            if (commandLineArguments[index].equals("-o")) {
                outputFileFlag = index;
                outputFile = new File(commandLineArguments[outputFileFlag+1]);
            }
            if(commandLineArguments[index].equals("-e")){
                flagForEncode = true;
            }

            if(commandLineArguments[index].equals("-d")){
                flagForDecode = true;
            }

        }
        if(flagForEncode){
            encode();
        }
        if(flagForDecode){
            decode();
        }
    }

    /**
     * Creates an array to map the occurrence of a character to its ASCII value
     * @param inputFile the original text file
     * @throws FileNotFoundException if file does not exist
     */
    private void createFrequencyArray(File inputFile) throws
            FileNotFoundException {
        findMaxCharacter(inputFile);
        frequencyArray = new int[MAX_SIZE + 1];

    }

    /**
     * Creates a BufferedWriter object to write to CSV file
     * @throws IOException if thrown by FileWriter
     */
    private void createCSVWriter() throws IOException{
        File opFile = new File("Output.csv");
        bufferedWriter = new BufferedWriter(new FileWriter(opFile));
    }

    /**
     * Closes the writer stream for CSV file
     * @throws IOException if thrown by the writer object
     */
    private void closeCSVWriter() throws IOException {
        bufferedWriter.close();
    }

    /**
     * Encodes the given input file to generate a .CSV and .txt file which
     * contain all characters and their Huffman encodings, and  the encoded
     * input file respectively
     * @throws IOException if thrown by one of the methods called
     */
    private void encode() throws IOException {
        createFrequencyArray(inputFile);
        populateFrequencyArray(inputFile);
        createNodeArray();
        createSerializableFileForArray();
        writeHuffmanCodeToFile(nodeArray[0], "", bufferedWriter);
        closeCSVWriter();
        encodeTextFile();
    }

    /**
     * Decodes the encoded input file
     * @throws IOException if thrown by decodeFile()
     * @throws ClassNotFoundException if thrown by decodeFile()
     */
    private void decode() throws IOException, ClassNotFoundException {
        decodeFile(inputFile);
    }

    /**
     * The main method
     * @param args command line arguments - name of input file, output file
     *             and type of operation
     */
    public static void main(String[] args) {
        Huffman huffmanObject = new Huffman();
        try {
            huffmanObject.createCSVWriter();
            huffmanObject.parseArguments(args);

        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
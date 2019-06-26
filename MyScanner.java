/*
 * Name:
 *  MyScanner.java
 *
 * Version:
 *  1.0
 *
 * Revisions:
 *  None
 */
import java.io.DataInputStream; //Used to read File contents
import java.io.File;// Used to create new File
import java.io.BufferedInputStream;// Used as InputStream for DataInputStream
import java.io.FileInputStream;//Used as input for BufferedInputStream
import java.io.IOException;
/**
 * This class acts as a wrapper class for the inbuilt Scanner class present
 * in JDK
 *
 * @author Anuradha Bhave       ab5890
 * @author Himanshi Chetwani    hc9165
 */
public class MyScanner {
    static String nameOfFile;
    private File file;
    private DataInputStream processor;
    private DataInputStream lineProcessor, characterProcessor;
    private boolean closeFlag = false;

    /**
     * Parameterized constructor for the class
     * @param fileName file to be accessed by scanner
     */
    public MyScanner(String fileName) {
        try {
            nameOfFile = fileName;
            file = new File(nameOfFile);
            processor =
                    new DataInputStream(new BufferedInputStream
                            (new FileInputStream(file)));
        } catch(IOException constructorException){
            System.err.println("Exception found in: Constructor");
            constructorException.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Used to check if scanner has another token in its input stream
     * @return true if unread token exists
     */
    public boolean hasNext() {
        try {
            processor.mark(100);
            int nextByte = processor.read();
            processor.reset();
            return nextByte != -1;
        } catch(IOException hasNextException){
            System.err.println("Exception found in: hasNext()");
            hasNextException.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    /**
     * Used to check if another line is present in the scanner input
     * @return true if an unread line is present in the input
     */
    public String nextLine() {
        try {
            StringBuilder returnLine = new StringBuilder();
            int currentChar;
            while (hasNext()) {
                currentChar = processor.read();
                if (currentChar == 10) {
                    return returnLine.toString();
                } else {
                    returnLine.append((char) currentChar);
                }
            }
            return returnLine.toString();
        } catch(IOException nextLineException){
            System.err.println("Exception found in: nextLine()");
            nextLineException.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    /**
     * Count the total number of lines present in the file
     * @return number of lines in file
     */
    public int getLineCount() {
        try {
            if (closeFlag) {
                throw new IOException();
            }
            int lineCount = 0;
            boolean emptyFileFlag = true;
            lineProcessor =
                    new DataInputStream(new FileInputStream(nameOfFile));
            int currentCharacter;
            while (true) {
                currentCharacter = lineProcessor.read();
                if (currentCharacter == -1) {
                    break;
                }
                if (currentCharacter == 10) {
                    lineCount++;

                }
                emptyFileFlag = false;
            }
            lineCount++;
            lineProcessor.close();
            if (!emptyFileFlag) {
                return lineCount;
            } else {
                return 0;
            }
        } catch(IOException lineCountException){
            System.err.println("Exception found in: getLineCount()");
            lineCountException.printStackTrace();
            System.exit(1);
        }
        return 0;
    }
    /**
     * Count number of characters in the file
     * @return number of characters in the file
     */
    public int getCharacterCount() {
        try {
            if (closeFlag) {
                throw new IllegalStateException();
            }
            int characterCount = 0;
            characterProcessor =
                    new DataInputStream(new FileInputStream(nameOfFile));
            int currentCharacter;
            while (true) {
                currentCharacter = characterProcessor.read();
                if (currentCharacter == -1) {
                    break;
                } else {
                    characterCount++;
                }
            }
            characterProcessor.close();
            return characterCount;
        } catch(IOException charCountException){
            System.err.println("Exception found in : getCharacterCount()");
            charCountException.printStackTrace();
            System.exit(1);
        }
        return 0;
    }

    /**
     * Used to close the scanner, and release any resources it has acquired
     */
    public void close() {
        try {
            processor.close();
            closeFlag = true;
        } catch (IOException closeException) {
            System.err.println("Exception found in: close()");
            closeException.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Provides testing methods for the class's implementations
     * @param args name of file
     */
    public static void main(String[] args) {
        nameOfFile = args[0];
        MyScanner testScanner = new MyScanner(nameOfFile);
        System.out.println("Test for nextLine()");
        System.out.println("Printing first line");
        System.out.println(testScanner.nextLine());
        System.out.println("Printing second line");
        System.out.println(testScanner.nextLine());
        System.out.println("----------------------------");
        System.out.println("Test for line count");
        System.out.println("Line count: "+testScanner.getLineCount());
        System.out.println("----------------------------");
        System.out.println("Test for character count");
        System.out.println("Character count: "+testScanner.getCharacterCount());
        System.out.println("----------------------------");
        System.out.println("Test for hasNext()");
        while(testScanner.hasNext()) {
            System.out.println(testScanner.nextLine());
        }
        System.out.println("----------------------------");
        System.out.println("Test for close()");
        testScanner.close();
        System.out.println(testScanner.getLineCount());
        System.out.println(testScanner.nextLine());
    }
}
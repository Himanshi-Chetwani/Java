/*
 * Name:
 *      BinarySum.java
 *
 * Version:
 *      1.0
 *
 * Revisions:
 *      None
 *
 */

/**
 * This class contains methods to accept binary strings as command line
 * arguments, add them and display the result in decimal, binary and
 * hexadecimal formats. It also contains supplementary methods to check the
 * validity of the command line arguments and to prepend arguments to
 * represent them as 8 bits.
 *
 * @author Anuradha Bhave       ab5890
 * @author Himanshi Chetwani    hc9165
 */
public class BinarySum {
    private final static char[] HEXCONVERSIONREMAINDERS = {'0',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
            'C', 'D', 'E', 'F'};

    /**
     * This method is used to check if the command line arguments are valid
     * against the following conditions:
     * 1. The number of arguments should be at least 2, since we are performing
     * summation
     * 2. The arguments should be binary strings - ie must only contain 1's
     * and 0's
     * 3. Each argument should be represented by at the most 8 bits.
     *
     * @param inputArguments contain command line arguments passed to method
     *                       from main method
     */
    private static void checkArgs(String inputArguments[]) {
        if (inputArguments.length < 2) {
            System.out.println("Error: Insufficient arguments. Summation " +
                    "requires at least 2 numbers");
            System.exit(1);
        }
        for (int loopVariable = 0; loopVariable < inputArguments.length;
             loopVariable++) {
            for (int index = 0; index < inputArguments[loopVariable].length();
                 index++) {
                if (inputArguments[loopVariable].charAt(index) != '1' &&
                        inputArguments[loopVariable].charAt(index) != '0') {
                    System.out.println("Error: Invalid input, not a binary " +
                            "string");
                    System.exit(1);
                }
            }
        }
    }

    /**
     * This method is used to prepend an argument with 0s to represent it as
     * the nearest byte.
     *
     * @param inputString which is the binary string to be modified
     * @return modified string which has a byte representation
     */
    private static String prependArgument(String inputString) {

            int loopVariable = 8 - (inputString.length() % 8);
            for (int i = 0; i < loopVariable; i++) {
                inputString = "0" + inputString;
            }

        return inputString;
    }

    /**
     * This method is used to convert the command line arguments, which are
     * binary strings,to decimal numbers.
     *
     * @param inputString binary string to be converted to a decimal number
     * @return decimal equivalent of the binary input
     */
    private static int convertToDecimal(String inputString) {
        int multiplier;
        int decimalNumber = 0;
        for (int loop = 0; loop < inputString.length(); loop++) {
            if (inputString.charAt(loop) == '1') {
                multiplier = ((inputString.length() - 1) - loop);
                decimalNumber = (int) (decimalNumber + Math.pow(2, multiplier));
            }
        }
        return decimalNumber;

    }

    /**
     * This method is used to represent the sum of the binary strings as a
     * hexadecimal number
     *
     * @param decimalNumber sum whose hexadecimal value is calculated
     * @return hexadecimal representation of the decimal input
     */
    private static String convertToHex(int decimalNumber) {
        String finalResult = "";
        String concatenatedCharacters = "0x";
        while (decimalNumber != 0) {
            finalResult = (HEXCONVERSIONREMAINDERS[decimalNumber % 16])
                    + finalResult;
            decimalNumber = decimalNumber / 16;
        }
        return concatenatedCharacters + finalResult;

    }

    /**
     * This method is used to convert the decimal sum obtained to its binary
     * equivalent.
     *
     * @param decimalNumber sum whose binary value is calculated
     * @return binary equivalent of decimal input
     */
    private static String convertToBinary(int decimalNumber) {
        String finalResult = "";
                while (decimalNumber != 0) {
            finalResult = (decimalNumber % 2) + finalResult;
            decimalNumber = decimalNumber / 2;
        }
        finalResult = prependArgument(finalResult);
        finalResult = "b'" + finalResult + "'";
        return finalResult;

    }

    /**
     * The main method which is responsible to accept command line
     * arguments as input, call necessary methods, and print the results.
     *
     * @param args binary strings whose sum is to be calculated
     */
    public static void main(String[] args) {
        int[] storeResults = new int[args.length];
        int sum = 0;
        String hexResult, binaryResult;
        checkArgs(args);
        for (int loop = 0; loop < args.length; loop++) {
            args[loop] = prependArgument(args[loop]);
        }
        for (int loop = 0; loop < storeResults.length; loop++) {
            storeResults[loop] = convertToDecimal(args[loop]);
        }
        System.out.print("Adding: ");
        for (int loopVariable = 0; loopVariable < storeResults.length;
             loopVariable++) {
            System.out.print(storeResults[loopVariable] + " + ");
        }
        System.out.print("\b\b");
        System.out.println(" ");
        for (int storeResult : storeResults) {
            sum = sum + storeResult;
        }
        binaryResult = convertToBinary(sum);
        System.out.println("Sum in binary: " + binaryResult);
        System.out.println("Sum in decimal: " + sum);
        hexResult = convertToHex(sum);
        System.out.println("Sum in hex: " + hexResult);
    }
}

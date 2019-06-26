/**
 * Represents an Calculator that uses LE
 *
 * @author Anuradha Bhave
 * @author Himanshi Chetwani
 */

/**
 * Functional Interface taht performs the required oprations
 */
interface OperationalFunctionalInterface {
    int operation(int a, int b);
}

/**
 * helpMessage is static and it’s implementation is already given
 * (and we need to implement only performOperation) so Calculator Interface
 * is also be a functional interface
 */
interface CalculatorInterface {
    /**
     * Method that calls Operation, is a helper function and is the method to
     * be implemented in the Functional Interface
     * @param ofi
     * @param num1
     * @param num2
     * @return finalResult
     */
    int performOperation(OperationalFunctionalInterface ofi, int num1,int num2);

    /**
     * Returns with what exit code you need to exit
     * @param exitCode
     */
    static void helpMessage(int exitCode) {
        System.out.println("Usage: <num><operation><num>");
        System.exit(exitCode);
    }
}

/**
 * Implements calculator
 */
public class Calculator { //implements CalculatorInterface {
    /**
     * Calls the two Lambda Expressions
     * @param args
     */
    public static void main(String[] args) {
        int numb1 = 0, numb2 = 0;
        if (args.length != 3) {
            CalculatorInterface.helpMessage(1);
        }
        try {
            numb1 = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            CalculatorInterface.helpMessage(2);

        }
        try {
            numb2 = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            CalculatorInterface.helpMessage(2);

        }
        String operation = args[1];
        if (!(operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/")))
            CalculatorInterface.helpMessage(3);
        //Calculator calcObj = new Calculator();
        int resultUltimate ;
        CalculatorInterface calObj =
                (OperationalFunctionalInterface ofi, int num1,
                 int num2) -> {

                    int finalResult = 0;
                    finalResult = ofi.operation(num1, num2);
                    return finalResult;
                };
        // We need to use either /* or "*" in command line
        OperationalFunctionalInterface obj = (int number1, int number2) -> {
            int result = 0;
            if (operation.equals("+")) {
                result = number1 + number2;
            } else if (operation.equals("-")) {
                result = number1 - number2;
            } else if (operation.equals("*")) {
                result = 1;
                result = number1 * number2;
            } else if (operation.equals("/")) {
                result = number1 / number2;
            }
            return result;
        };
        //resultUltimate = calcObj.performOperation(obj, numb1, numb2);
        resultUltimate = calObj.performOperation(obj, numb1, numb2);
        System.out.println(resultUltimate);

    }

    // @Override
    /*public int performOperation(OperationalFunctionalInterface ofi, int num1, int num2) {
        ofi.operation(num1,num2);
        int finalResult = 0;
        finalResult = ofi.operation(num1, num2);
        return finalResult;
    }*/
}

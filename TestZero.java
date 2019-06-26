/*
 * Name:
 *      TestZero.java
 *
 * Version:
 *      1.0
 *
 * Revisions:
 *      None
 *
 */

/**
 * This class is used to test the functionality present in Zero.java. It
 * contains a method to represent the test set as a string.
 *
 * @author Anuradha Bhave       ab5890
 * @author Himanshi Chetwani    hc9165
 */
public class TestZero {
    /**
     * This method is used to print the test set
     *
     * @param testSet which is the set to be printed
     */
    static void printSet(int[] testSet) {
        System.out.print("Checking: [");
        for (int printLoop = 0; printLoop < testSet.length; printLoop++) {
            System.out.print(testSet[printLoop] + ", ");
        }
        System.out.print("\b\b]");
    }

    /**
     * The main method used to declare test sets and call the necessary
     * methods using an object of class Zero.
     *
     * @param args command line arguments, not taken in this program
     */
    public static void main(String[] args) {
        Zero testObj = new Zero();
        int[] testSetOne = {-1, -1, 2, 5, 6};
        int[] testSetTwo = {-1, 2, 2};
        int[] testSetThree = {0};
        int[] testSetFour = {1, -1, 2, -2, 3, -3};
        int[] testSetFive = {-1, 2, 5, 4, 6, 8, 1};
        int[] testSetSix = {4, 7, 8, 2, 3};
        printSet(testSetOne);
        System.out.println(" - should be true");
        testObj.checkSet(testSetOne);
        printSet(testSetTwo);
        System.out.println(" - should be false");
        testObj.checkSet(testSetTwo);
        printSet(testSetThree);
        System.out.println(" - should be true");
        testObj.checkSet(testSetThree);
        printSet(testSetFour);
        System.out.println(" - should be true");
        testObj.checkSet(testSetFour);
        printSet(testSetFive);
        System.out.println(" - should be true");
        testObj.checkSet(testSetFive);
        printSet(testSetSix);
        System.out.println(" - should be false");
        testObj.checkSet(testSetSix);
    }
}

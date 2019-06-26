/*
 * Name:
 *  HuffmanNode.java
 *
 * Version:
 *  1.1
 *
 * Revisions:
 *  Implements Serialiazable to store HuffmanArray in a file at an
 *  intermediate stage of execution
 */

import java.io.Serializable;

/**
 * This class creates a HuffMan node
 *
 * @author Anuradha Bhave       ab5890
 * @author Himanshi Chetwani    hc9165
 */
class HuffmanNode implements Serializable {
    int frequency;
    char c = (char) 255;
    HuffmanNode left;
    HuffmanNode right;

    /**
     * Default constructor
     */
    HuffmanNode() {

    }

    /**
     * Parameterized constructor
     * @param frequency occurrence of character
     * @param c character
     */
    HuffmanNode(int frequency, char c) {
        this.frequency = frequency;
        this.c = c;
    }

    /**
     * Parameterized constructor
     * @param frequency occurrence of character
     * @param left left child of Node
     * @param right right child of Node
     */
    HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    /**
     * Sorts the array according to frequencies. If the frequencies are the
     * same, sorts according to character unicode values
     * @param array Array of calculated frequencies
     */
    void sort(HuffmanNode[] array) {
        HuffmanNode temp;
        HuffmanNode left, right;
        for (int loop = 0; loop < array.length; loop++) {
            for (int innerLoop = 0; innerLoop < array.length - 1; innerLoop++) {
                left = array[innerLoop];
                right = array[innerLoop + 1];
                if (left.frequency == right.frequency) {
                    if (left.c > right.c) {
                        temp = array[innerLoop];
                        array[innerLoop] = array[innerLoop + 1];
                        array[innerLoop + 1] = temp;
                    }
                } else if (left.frequency >
                        right.frequency) {
                    temp = array[innerLoop];
                    array[innerLoop] = array[innerLoop + 1];
                    array[innerLoop + 1] = temp;
                }


            }

        }

    }
    /**
     * Extract minimum value of a frequency array
     * @param array Array of calculate frequencies
     * @return Deleted node
     */
    HuffmanNode delete(HuffmanNode[] array) {
        HuffmanNode deletedElement = array[0];
        for (int index = 0; index < array.length - 1; index++) {
            array[index] = array[index + 1];
        }
        return deletedElement;
    }

    /**
     * Truncate last element of array after minimum element is extracted
     * @param testArray Array of frequencies
     * @return Modified array
     */
    HuffmanNode[] truncateElement(HuffmanNode[] testArray) {
        HuffmanNode[] arrayCopy;
        arrayCopy = testArray;
        testArray = new HuffmanNode[arrayCopy.length - 1];
        for (int index = 0; index < testArray.length; index++) {
            testArray[index] = arrayCopy[index];
        }
        return testArray;
    }
}







/*
 * Name:
 *      Zero.java
 *
 * Version:
 *      1.0
 *
 * Revisions:
 *      None
 */

/**
 * This class contains a method to check if a given set contains a
 * combination of elements whose sum results in zero. It also contains a
 * supplementary method to calculate the total number of possible
 * combinations for a given set.
 *
 * @author Anuradha Bhave    ab5890
 * @author Himanshi Chetwani hc9165
 */
class Zero {

    /**
     * This method is used to generate subsets of a given input set through
     * bit manipulation. Once a subset is generated, its sum is compared with
     * 0. If the sum is zero, that subset is printed else the next
     * combination is checked.
     *
     * @param testSet whose subsets are to be checked for adding up to 0
     */
    void checkSet(int[] testSet) {
        int sizeOfSuperSet;
        //This calls the method calculatePowerSet which returns the number of
        // all possible subsets
        sizeOfSuperSet = calculatePowerSet(testSet);
        boolean flag = false;
        int sumOfSet = 0;
        String currentSubset = "";
        //This nested loop is used to generate subsets of a given set by bit
        // manipulation. It uses logical ANDing and left-shifting of bits to
        // traverse all elements of an array and set the bits one by one to
        // generate a subset
        for (int counterOfSuperSet = 0; counterOfSuperSet < sizeOfSuperSet;
             counterOfSuperSet++) {
            for (int loopVariable = 0; loopVariable < testSet.length;
                 loopVariable++) {
                if ((counterOfSuperSet & (1 << loopVariable)) != 0) {
                    currentSubset += testSet[loopVariable] + " ";
                    sumOfSet = sumOfSet + testSet[loopVariable];
                }
            }
            if (sumOfSet == 0 && !(currentSubset.equals(""))) {
                System.out.println("Found subset that sums to zero: "
                        + currentSubset);
                flag = true;
                break;
            }
            sumOfSet = 0;
            currentSubset = "";
        }
        if (!flag) {
            System.out.println("Unable to find subset that sums to zero");
        }

    }

    /**
     * This method is used to calculate the total number of possible subsets
     * of a given set, iw to calculate the size of the power set. It
     * implements the formula 2^n where n is the number of elements in the set.
     *
     * @param testSet whose size of power set is to be calculated
     *
     * @return size of power set
     *
     */
    private int calculatePowerSet(int[] testSet) {
        int sizeOfSuperSet = 1;
        int loopVariable = 0;
        do {
            sizeOfSuperSet = sizeOfSuperSet * 2;
            loopVariable++;

        } while (loopVariable < testSet.length);
        return sizeOfSuperSet;

    }

}

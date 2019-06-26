/*
 * Name: StorageAbstract.java
 *
 * Version:
 *  1.1
 *
 * Revisions:
 *  All functions are now generic
 */

/**
 * This class ise used to define common functions of an ArrayList and a
 * LinkedList
 * @param <T> specifies type of storage
 */
@SuppressWarnings("unchecked")
public abstract class StorageAbstract<T extends Comparable> implements
        StorageInterface<T> {

    int size;

    /**
     * Add all elements of input array to the system
     * @param os input array to be added to the list
     * @return true
     */
    @Override
    public boolean addAll(T[] os) {
        boolean flag = false;
        for (T o : os) {
            if (add(o)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Add all elements of the input array to a specific position in the system
     * @param index position at which elements should be inserted
     * @param os array of elements to be added
     * @return true
     */
    @Override
    public boolean addAll(int index, T[] os) {
        boolean flag = false;
        for (int value = os.length - 1; value >= 0; value--) {
            add(index, os[value]);
            flag = true;
        }
        return flag;
    }

    /**
     * Check if system contains all elements of input array
     * @param os input array whose elements are to be checked
     * @return false if element is not present else true
     */
    @Override
    public boolean containsAll(T[] os) {
        for (T element : os) {
            if (!(contains(element))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the list is empty
     * @return true is list is empty
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Remove from the list the elements in the specified array
     * @param os input array whose elements are to be removed from the list
     * @return true if elements are removed from the list
     */
    @Override
    public boolean removeAll(T[] os) {
        boolean flag = false;
        for (T loopVariable : os) {
            if (contains(loopVariable)) {
                remove(loopVariable);
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Calculates size of list
     * @return list size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Implements bubble sort on the list
     */
    @Override
    public void sort() {
        T tempOne, tempTwo;
        for (int loop = 0; loop < size(); loop++) {
            for (int innerLoop = 0; innerLoop < size() - 1; innerLoop++) {
                tempOne = get(innerLoop);
                tempTwo = get(innerLoop + 1);
                if (tempOne == null) {
                    remove(null);
                    add(size(), null);
                    continue;
                }
                if (tempTwo == null) {
                    remove(null);
                    add(size(), null);
                    continue;
                }
                if (tempOne.compareTo(tempTwo) > 0) {
                    set(innerLoop, tempTwo);
                    set(innerLoop + 1, tempOne);

                }

            }

        }

    }

    /**
     * Provides string representation
     * @return string equivalent of list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[  ");
        for (int i = 0; i < size(); i++) {
            result.append(get(i)).append(", ");
        }
        result.append("\b\b");
        result.append("  ]");
        return result.toString();
    }

    /**
     * Checks if two objects have the same memory reference
     * @param o second object, to be compared with current instance
     * @return true if they have same reference
     */
    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    /**
     * Checks if two objects contain the same elements in the same order
     * @param o object whose elements are to be compared with current instance
     * @return true if all elements are of same value and in same order
     */
    @Override
    public boolean contentEquals(StorageInterface o) {
        T tempOne, tempTwo;
        if (o == null) {
            return false;
        }
        if (size() != o.size()) {
            return false;
        } else {
            for (int loopVariable = 0; loopVariable < o.size(); loopVariable++) {
                tempOne = get(loopVariable);
                tempTwo = (T) o.get(loopVariable);
                if((tempOne == null && tempTwo!= null) ||
                        (tempOne!=null && tempTwo == null)){
                    return false;
                }
                if(tempOne == null){
                    continue;
                }
                if (!(tempOne.equals(tempTwo))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Calculates hash code of the list by calculating sum of hash codes of
     * individual elements
     * @return hash code of the list
     */
    @Override
    public int hashCode() {
        int code = 0;
        T value;
        for (int loopVariable = 0; loopVariable < size(); loopVariable++) {
            value = get(loopVariable);
            if (value == null) {
                continue;
            }
            code += get(loopVariable).hashCode();
        }
        return code;
    }
}

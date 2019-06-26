/*
 * Name: MyArrayList.java
 *
 * Version:
 *      1.2
 *
 * Revisions:
 *     None
 */

/**
 * Class used to define functions of an MyArrayList
 * @param <T> specifies type of storage
 */
@SuppressWarnings({"unchecked", "ManualArrayCopy"})
public class MyArrayList<T> extends StorageAbstract<T> {
    private T[] storage = (T[]) new Object[1];
    private int currentCapacity;

    /**
     * Constructor
     */
    MyArrayList() {
        size = 1;
    }

    /**
     * Returns total number of elements in the list
     * @return size of list
     */
    @Override
    public int size(){
        return currentCapacity;
    }

    /**
     * Dynamically re sizes the array
     */
    private void increaseCapacity() {
        T[] temporaryArray = (T[]) new Object[storage.length];
        for (int index = 0; index < temporaryArray.length; index++) {
            temporaryArray[index] = storage[index];
        }
        int earlierLength = storage.length;
        int newLength = earlierLength * 2;
        storage = (T[]) new Object[newLength];
        size = newLength;
        for (int index = 0; index < temporaryArray.length; index++) {
            storage[index] = temporaryArray[index];
        }
    }

    /**
     * Add an element to the list
     * @param o element to be added
     * @return true if list is modified
     */
    @Override
    public boolean add(T o) {
        if (currentCapacity < size) {
            storage[currentCapacity++] = o;
            return true;
        }
        increaseCapacity();
        storage[currentCapacity++] = o;
        return true;
    }


    /**
     * Remove an element from the list
     * @param index position at which element is to be removed
     * @return removed value
     */
    @Override
    public T remove(int index) {
        T oldValue = storage[index];
        for(int loop = index; loop < currentCapacity -1; loop++){
            storage[loop] = storage[loop+1];
        }
        currentCapacity--;
        return oldValue;
    }
    /**
     * Set value of an element at a position
     * @param index position at which value is to be set
     * @param element value to set
     * @return old value
     */
    @Override
    public T set(int index, T element) {
        T returnVariable = storage[index];
        storage[index] = element;
        return returnVariable;
    }

    /**
     * Get element at specific index
     * @param index position of element to be returned
     * @return element
     */
    @Override
    public T get(int index) {
        return storage[index];
    }

    /**
     * Clear the list
     */
    @Override
    public void clear() {
        size = 0;
        currentCapacity = 0;
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


}

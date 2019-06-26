/*
 * Name: ArrayList.java
 *
 * Version:
 *      1.0
 *
 * Revisions:
 *      None
 */

/**
 * Class used to define functions of an ArrayList
 * @param <T> specifies type of storage
 */
@SuppressWarnings({"unchecked", "ManualArrayCopy"})
public class ArrayList<T extends Comparable> extends StorageAbstract<T> {
    private T[] storage = (T[]) new Comparable[1];
    private int currentCapacity;

    /**
     * Constructor
     */
    ArrayList() {
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
        T[] temporaryArray = (T[]) new Comparable[storage.length];
        for (int index = 0; index < temporaryArray.length; index++) {
            temporaryArray[index] = storage[index];
        }
        int earlierLength = storage.length;
        int newLength = earlierLength * 2;
        storage = (T[]) new Comparable[newLength];
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
     * Add an element at an index
     * @param index position at which it should be added
     * @param element value to add
     */
    @Override
    public void add(int index, T element) {
        if(currentCapacity == size){
            increaseCapacity();
        }
        for(int loop = currentCapacity; loop > index; loop--){
            storage[loop] = storage[loop-1];
        }
        storage[index] = element;
        currentCapacity++;
    }

    /**
     * Check if list contains element
     * @param o element to be checked
     * @return true if list contains element
     */
    @Override
    public boolean contains(T o) {
        for(int loop = 0; loop < currentCapacity; loop++){
            if(storage[loop] == o){
                return true;
            }
        }
        return false;
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
     * Return position of first occurrence of element
     * @param o element to be checked
     * @return position
     */
    @Override
    public int indexOf(T o) {
        int indexToReturn = -1;
        for(int index = 0; index < currentCapacity;
            index++){
            if(storage[index] == o){
                indexToReturn = index;
                break;
            }
        }
        return indexToReturn;
    }

    /**
     * Return position of last occurrence of element
     * @param o element to be checked
     * @return position of last occurrence
     */
    @Override
    public int lastIndexOf(T o) {
        int indexToReturn;
        for(indexToReturn = currentCapacity-1; indexToReturn >=0;
            indexToReturn--){
            if(storage[indexToReturn] == o){
                break;
            }
        }
        return indexToReturn;
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
     * Remove a value from list
     * @param o value to be removed
     * @return true if list is modified
     */
    @Override
    public boolean remove(T o) {
            int elementIndex = indexOf(o);
            if(elementIndex != -1) {
                remove(elementIndex);
                return true;
            }

        return false;
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
     * Clear the list
     */
    @Override
    public void clear() {
        size = 0;
        currentCapacity = 0;
    }

}

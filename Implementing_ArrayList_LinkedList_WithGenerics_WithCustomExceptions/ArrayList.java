/*
 * Name: ArrayList.java
 *
 * Version:
 *      1.1
 *
 * Revisions:
 *      Only those elements that are of even length or value are added
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
    private boolean increaseCapacity() {
        T[] temporaryArray = makeArrayCopy();
        int earlierLength = storage.length;
        int newLength = earlierLength * 2;
        modifyStorage(newLength, temporaryArray);
        return true;
    }
    private T[] makeArrayCopy(){
        T[] temporaryArray = (T[]) new Comparable[currentCapacity];
        for (int index = 0; index < temporaryArray.length; index++) {
            temporaryArray[index] = storage[index];
        }
        return temporaryArray;

    }

    private void modifyStorage(int sizeOfNewArray, T[] temporaryArray){
        storage = (T[]) new Comparable[sizeOfNewArray];
        size = sizeOfNewArray;
        for (int index = 0; index < temporaryArray.length; index++) {
            storage[index] = temporaryArray[index];
        }

    }

    private void decreaseCapacity(){
        T[] temporaryArray = makeArrayCopy();
        int newLength = storage.length/2;
        modifyStorage(newLength, temporaryArray);

    }

    private void handleNonEvenElement(int index, boolean capacityFlag){
        System.out.println("Removing non even element");
        remove(index);
        if(capacityFlag){
            decreaseCapacity();
        }
    }

    /**
     * Add an element to the list
     * @param o element to be added
     * @return true if list is modified
     */
    @Override
    public boolean add(T o) {
        boolean flag = true;
        boolean increaseCapacityFlag = false;
        try {
            if (currentCapacity < size) {
                storage[currentCapacity++] = o;
                checkNonEvenException(o);
            }
            else {
                increaseCapacityFlag =increaseCapacity();
                storage[currentCapacity++] = o;
                checkNonEvenException(o);
            }
        } catch (NonEvenException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            handleNonEvenElement((currentCapacity-1), increaseCapacityFlag);
            flag = false;
        }
        return flag;
    }

    /**
     * Add an element at an index
     * @param index position at which it should be added
     * @param element value to add
     */
    @Override
    public void add(int index, T element) {
        boolean increaseCapacityflag = false;
        try {
            if (currentCapacity == size) {
                increaseCapacityflag = increaseCapacity();
            }
            for (int loop = currentCapacity; loop > index; loop--) {
                storage[loop] = storage[loop - 1];
            }
            storage[index] = element;
            currentCapacity++;
            checkNonEvenException(element);
        } catch(NonEvenException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            handleNonEvenElement(index,increaseCapacityflag);
        } catch(IndexOutOfBoundsException i){
            System.out.println("Incorrect index");
            i.printStackTrace();
        }
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
        T returnValue = (T) new Integer(-1);
        try {
            if(index > size() || index < 0){
                throw new ArrayIndexOutOfBoundsException();
            }
            returnValue = storage[index];
        } catch(IndexOutOfBoundsException ie){
            System.out.println("Incorrect index");
            ie.printStackTrace();
        }
        return returnValue;
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
        T oldValue = (T) (new Integer(-1));
        try {
            if(index > size() || index < 0)
            oldValue = storage[index];
            for (int loop = index; loop < currentCapacity - 1; loop++) {
                storage[loop] = storage[loop + 1];
            }
            currentCapacity--;

        } catch(IndexOutOfBoundsException ie){
            System.out.println("Incorrect index");
            ie.printStackTrace();
        }
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
        T returnVariable = (T) (new Integer(-1));
        try {
            if(index >= size() || index < 0){
                throw new ArrayIndexOutOfBoundsException();
            }
            try {
                returnVariable = storage[index];
                storage[index] = element;

                checkNonEvenException(element);
            } catch (NonEvenException e) {
                e.printStackTrace();
                storage[index] = returnVariable;
                return element;
            }
        } catch(IndexOutOfBoundsException ie){
            System.out.println("Incorrect index");
            ie.printStackTrace();
        }
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

/*
 * Name: StorageAbstract.java
 *
 * Version:
 *  1.2
 *
 * Revisions:
 *  Removed generics bound
 */

/**
 * This class ise used to define common functions of an ArrayList and a
 * LinkedList
 * @param <T> specifies type of storage
 */
@SuppressWarnings("unchecked")
public abstract class StorageAbstract<T> implements
        StorageInterface<T> {

    int size;

    /**
     * Checks if the list is empty
     *
     * @return true is list is empty
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Calculates size of list
     *
     * @return list size
     */
    @Override
    public int size() {
        return size;
    }


}
/*
 * Name:
 *  MyLinkedList.java
 *
 * Version:
 *  1.2
 *
 * Revisions:
 *  Removed some functions, removed generics bound
 */


/**
 * This class implements a singly linked list
 *
 * @author Anuradha Bhave   ab5890
 * @author Himanshi Chetwani hc9165
 */
public class MyLinkedList<T> extends StorageAbstract<T> {
    private Node firstElement;

    /**
     * Add an element to the linked list
     *
     * @param o value of node to be added to the linked list
     * @return true when list is modified
     */
    public boolean add(T o) {
        boolean flag = false;
        Node<T> temp;
        temp = firstElement;
        if (firstElement == null) {
            addFirst(o);
        } else {
            while (temp.nextLink != null) {
                temp = temp.nextLink;
            }
            Node tempElement = new Node(o);
            Node counterElement = firstElement;
            while (counterElement.nextLink != null) {
                counterElement = counterElement.nextLink;
            }
            counterElement.nextLink = tempElement;
            size++;
        }
        return flag;
    }



    /**
     * If list does not exist, create new list and add the first element to it
     *
     * @param value value of element added to list
     */
    private void addFirst(T value) {
        Node tempElement = new Node(value);
        tempElement.nextLink = firstElement;
        firstElement = tempElement;
        size++;
    }

    /**
     * Clears the list of all its contents
     */
    @Override
    public void clear() {
        firstElement = null;
        size = 0;

    }



    /**
     * Get an element at a specific position
     *
     * @param index index of the element to be returned
     * @return element at specified position
     */
    @Override
    public T get(int index) {
        T result;
        Node<T> elementToReturn = firstElement;
        for (int loopVariable = 0; loopVariable < index; loopVariable++) {
            elementToReturn = elementToReturn.nextLink;
        }
        return elementToReturn.data;
    }



    /**
     * Remove element at specified index
     *
     * @param index position at which element is to be removed
     * @return element previously at specified position
     */
    @Override
    public T remove(int index) {
        Node<T> dataToDelete;
        if (index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == 0) {
            dataToDelete = firstElement;
            firstElement = firstElement.nextLink;
            size--;

        } else {
            dataToDelete = firstElement;
            Node tempElement;
            for (int loop = 0; loop < index - 1; loop++) {
                dataToDelete = dataToDelete.nextLink;
            }
            tempElement = dataToDelete.nextLink;
            dataToDelete.nextLink = tempElement.nextLink;
            size--;
        }
        return dataToDelete.data;
    }



    /**
     * Set index in the list to a specific value
     *
     * @param index   index in the list
     * @param element value which is changed
     * @return previous value at the index
     */
    @Override
    public T set(int index, T element) {
        if (index > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<T> accessElement = firstElement;
        T returnOld;
        for (int loop = 0; loop < index; loop++) {
            accessElement = accessElement.nextLink;
        }
        returnOld = accessElement.data;
        accessElement.data = element;
        return returnOld;
    }
}

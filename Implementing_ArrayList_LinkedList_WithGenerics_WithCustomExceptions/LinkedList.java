/*
 * Name:
 *  LinkedList.java
 *
 * Version:
 *  1.1
 *
 * Revisions:
 *  Only those elements of even length or value are added
 */


/**
 * This class implements a singly linked list
 *
 * @author Anuradha Bhave   ab5890
 * @author Himanshi Chetwani hc9165
 */
public class LinkedList<T extends Comparable> extends StorageAbstract<T> {
    private Node firstElement;

    /**
     * Add an element to the linked list
     *
     * @param o value of node to be added to the linked list
     * @return true when list is modified
     */
    public boolean add(T o) {
        boolean flag;
        try {
            Node<T> temp;
            temp = firstElement;
            if (firstElement == null) {
                addFirst(o);
                flag = true;
                checkNonEvenException(o);
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
                flag = true;
                checkNonEvenException(o);
            }
//            while (temp != null && size > 0 && temp.nextLink != null) {
//                if (this.contains(temp.data)) {
//                    temp = temp.nextLink;
//                    flag = true;
//                }
//                else
//                {
//                    flag = false;
//                }
//            }

        } catch (NonEvenException e) {
            e.printStackTrace();
            System.out.println("Removing non even element");
            remove(size() - 1);
            flag = false;
        }
        return flag;
    }

    /**
     * Add an element at a specific position in the list
     *
     * @param index   position in the list where element is added
     * @param element value of the element to be added to the list
     */
    public void add(int index, T element) {
        try {
            if (index >= size() && index > 0 && firstElement != null|| index < 0)
            {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (index == 0 || firstElement == null) {
                addFirst(element);
                checkNonEvenException(element);
            } else {
                Node counterElement;
                Node tempElement;
                Node addNode = new Node(element);
                counterElement = firstElement;
                for (int loopVariable = 0; loopVariable < index - 1;
                     loopVariable++) {
                    counterElement = counterElement.nextLink;
                }
                tempElement = counterElement.nextLink;
                counterElement.nextLink = addNode;
                addNode.nextLink = tempElement;
                size++;
                checkNonEvenException(element);
            }
        } catch (NonEvenException e) {
            e.printStackTrace();
            System.out.println("Removing non even element");
            remove(index);
        } catch (IndexOutOfBoundsException ie) {
            System.out.println("Incorrect index");
            ie.printStackTrace();
        }

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
     * Checks if list contains an element
     *
     * @param o value to be checked
     * @return true if element is present in the list
     */
    @Override
    public boolean contains(T o) {
        Node isElementPresent;
        for (isElementPresent = firstElement; isElementPresent != null;
             isElementPresent =
                     isElementPresent.nextLink) {
            if (isElementPresent.data == o) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get an element at a specific position
     *
     * @param index index of the element to be returned
     * @return element at specified position
     */
    @Override
    public T get(int index) {
        T result = (T) new Integer(-1);
        try {
            if(index > size()|| index < 0){
                throw new IndexOutOfBoundsException();
            }
            Node<T> elementToReturn = firstElement;
            for (int loopVariable = 0; loopVariable < index; loopVariable++) {
                elementToReturn = elementToReturn.nextLink;
            }
            result = elementToReturn.data;
        } catch (IndexOutOfBoundsException ie) {
            System.out.println("Incorrect index");
            ie.printStackTrace();

        }
        return result;
    }

    /**
     * Returns index of first occurrence of the element, or returns -1 if
     * element does not exist
     *
     * @param o element to search for
     * @return index of first occurrence of element
     */
    @Override
    public int indexOf(T o) {
        Node traverseElement;
        int counter = 0;
        for (traverseElement = firstElement; traverseElement != null;
             traverseElement = traverseElement.nextLink) {
            if (traverseElement.data == o) {
                return counter;
            } else {
                counter++;
            }
        }
        return -1;
    }

    /**
     * Returns index of last occurrence of the element, or -1 if element does
     * not exist
     *
     * @param o element to search for
     * @return index of last occurrence of element
     */
    @Override
    public int lastIndexOf(T o) {
        Node traverseElement;
        int counter = 0;
        int temp = -1;
        for (traverseElement = firstElement; traverseElement != null;
             traverseElement = traverseElement.nextLink) {
            if (traverseElement.data == o) {
                temp = counter;
            }
            counter++;
        }
        return temp;
    }

    /**
     * Remove element at specified index
     *
     * @param index position at which element is to be removed
     * @return element previously at specified position
     */
    @Override
    public T remove(int index) {
        Node<T> dataToDelete = firstElement;
        try {
            if (index == 0 && size() == 1) {
                dataToDelete = firstElement;
                clear();
                return dataToDelete.data;

            }
            if ((size() != 0 && index >= size()) || index < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (size != 0 && index == 0) {
                dataToDelete = firstElement;
                firstElement = firstElement.nextLink;
                size--;
                return dataToDelete.data;
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
        } catch (IndexOutOfBoundsException ie) {
            System.out.println("Incorrect index");
            ie.printStackTrace();

        }
        return dataToDelete.data;
    }


    /**
     * Remove specified Integer
     *
     * @param o Integer to be removed
     * @return false
     */
    @Override
    public boolean remove(T o) {
        Node<T> elementToDelete;
        Node<T> temp;
        boolean flag = false;
        temp = firstElement;
        while (temp.nextLink != null) {
            temp = temp.nextLink;
        }
        elementToDelete = firstElement;
        if ((elementToDelete.data).compareTo(o) == 0) {
            firstElement = firstElement.nextLink;
            size--;
        } else {
            while (elementToDelete.nextLink.data.compareTo(o) != 0) {
                elementToDelete = elementToDelete.nextLink;
            }
            elementToDelete.nextLink = elementToDelete.nextLink.nextLink;
            size--;
        }
        while (temp != null && temp.nextLink != null) {
            if (this.contains(temp.data)) {
                temp = temp.nextLink;
                flag = true;
            } else
                flag = false;
        }

        return flag;

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
        T returnOld = (T)firstElement.data;
        try {
            if (index > size() || index < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Node<T> accessElement = firstElement;
            for (int loop = 0; loop < index; loop++) {
                accessElement = accessElement.nextLink;
            }
            returnOld = accessElement.data;
            accessElement.data = element;
            try {
                checkNonEvenException(element);
            } catch (NonEvenException e) {
                e.printStackTrace();
                accessElement.data = returnOld;
                return element;
            }
            return returnOld;
        } catch (IndexOutOfBoundsException ie) {
            System.out.println("Incorrect index");
            ie.printStackTrace();
        }
        return returnOld;
    }
}

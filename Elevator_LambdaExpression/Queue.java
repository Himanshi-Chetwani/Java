/*
 * Name:
 *  Queue.java
 *
 * Version:
 *  1.1
 *
 * Revisions:
 *  None
 */


/**
 * Class used to create a queue which is built on a Linked list
 * @param <T> type of queue
 */
public class Queue <T> {
    private MyLinkedList<T> queue = new MyLinkedList<>();

    /**
     * Add an element to queue
     * @param o of type T, element to be added
     */
    void add(T o){
        queue.add(o);
    }

    /**
     * Remove from queue element at position 0
     * @return element removed
     */
    T remove(){
       return queue.remove(0);
    }

    /**
     * Return size of queue
     * @return size
     */
    int size(){
        return queue.size();
    }

}

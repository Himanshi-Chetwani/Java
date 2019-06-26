/*
 * Name:
 *  Node.java
 *
 * Version:
 *  1.2
 *
 * Revisions:
 *  None
 */

/**
 * A class for describing a node, which is a building block of a Linked list
 *
 * @author Anuradha Bhave ab5890
 * @author Himanshi Chetwani hc9165
 */
class Node<T > {
    T data;
    Node<T> nextLink;

    Node(T value){

        this.data=value;
        this.nextLink=null;
    }
}

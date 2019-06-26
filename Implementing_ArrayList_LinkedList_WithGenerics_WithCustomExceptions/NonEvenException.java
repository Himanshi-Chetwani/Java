/*
 * Name : NonEvenException.java
 *
 * Version:
 *  1.0
 *
 * Revisions:
 *  None
 */
/**
 * Used to throw an exception when an element of odd length or value is
 * inserted in storage system
 */
public class NonEvenException extends Exception {
    NonEvenException(String messageToPrint){super(messageToPrint);}
}

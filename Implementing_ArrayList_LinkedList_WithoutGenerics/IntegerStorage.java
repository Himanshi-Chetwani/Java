
/*
 * Classname:IntegerStorage.java
 *
 * Version information:1
 *
 * Date:02/17/2019
 *@author : Anuradha Nitin Bhave and Himanshi Chetwani
 */

public interface IntegerStorage
{
    // Appends the specified element to the end of this list
    boolean	add(int o);

    // Inserts the specified element at the specified position in this list
    void	add(int index, int element);

    // Appends all of the elements in the specified array to the end of this list, in the same order that
    // they appear in the array
    boolean	addAll(int[] os);

    // Inserts all of the elements in the specified collection into this list at the specified position
    boolean	addAll(int index, int[] os);

    // Removes all of the elements from this list
    void clear();

    // Returns true if this list contains the specified element.
    boolean	contains(int o);

    // Returns true if this list contains all of the elements of the specified collection.
    boolean	containsAll(int[] os);

    // Compares the specified object with this storage for equality.
    boolean	equals(Object o);

    // Compares the specified IntegerStorage for content equality both in terms of values and order.
    boolean	contentEquals(IntegerStorage o);

    // Returns the element at the specified position in this storage.
    int get(int index);

    // Returns the hash code value for this storage. Defined as the sum of the elements in the storage
    int	hashCode();

    // Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
    int	indexOf(int o);

    // Returns true if this storage contains no elements.
    boolean	isEmpty();

    // Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
    int	lastIndexOf(int o);

    // Removes the element at the specified position in this list (optional operation).
    int	remove(int index);

    // Removes the first occurrence of the specified element from this list, if it is present.
    boolean	remove(Integer o);

    // Removes from this list all of its elements that are contained in the specified collection (optional operation).
    boolean	removeAll(int[] os);

    // Replaces the element at the specified position in this list with the specified element
    int	set(int index, int element);

    // Returns the number of elements in this storage.
    int	size();

    // Sorts this storage into ascending order.
    void sort();

    // Pretty print the storage
    String toString();

}

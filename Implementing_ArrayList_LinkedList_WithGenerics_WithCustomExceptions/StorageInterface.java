
public interface StorageInterface<T extends Comparable>
{
    // Appends the specified element to the end of this list
    boolean add(T o);

    // Inserts the specified element at the specified position in this list
    void add(int index, T element);

    // Appends all of the elements in the specified array to the end of this list, in the same order that
    // they appear in the array
    boolean	addAll(T[] os);

    // Inserts all of the elements in the specified collection into this list at the specified position
    boolean	addAll(int index, T[] os);

    // Removes all of the elements from this list
    void clear();

    // Returns true if this list contains the specified element.
    boolean contains(T o);

    // Returns true if this list contains all of the elements of the specified collection.
    boolean	containsAll(T[] os);

    // Compares the specified object with this storage for equality.
    boolean equals(Object o);

    // Compares the specified StorageInterface for content equality both in terms of values and order.
    boolean	contentEquals(StorageInterface o);

    // Returns the element at the specified position in this storage.
    T get(int index);

    // Returns the hash code value for this storage. Defined as the sum of the element hashcodes in the storage
    int	hashCode();

    // Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
    int indexOf(T o);

    // Returns true if this storage contains no elements.
    boolean	isEmpty();

    // Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
    int lastIndexOf(T o);

    // Removes the element at the specified position in this list
    T remove(int index);

    // Removes the first occurrence of the specified element from this list, if it is present.
    boolean remove(T o);

    // Removes from this list all of its elements that are contained in the specified collection
    boolean	removeAll(T[] os);

    // Replaces the element at the specified position in this list with the specified element
    T set(int index, T element);

    // Returns the number of elements in this storage.
    int	size();

    // Sorts this storage into ascending order.
    void sort();

    // Pretty print the storage
    String toString();

    //Throws an exception if input is odd valued or of odd length
    void checkNonEvenException(T o) throws NonEvenException;

}

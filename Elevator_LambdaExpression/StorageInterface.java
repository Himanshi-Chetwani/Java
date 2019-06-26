/*
 * Name: StorageInterface.java
 *
 * Version: 1.3
 *
 * Revisions: None
 */
public interface StorageInterface<T>
{
    // Appends the specified element to the end of this list
    boolean add(T o);

    // Removes all of the elements from this list
    void clear();

    // Returns true if this storage contains no elements.
    boolean	isEmpty();

    // Removes the element at the specified position in this list
    T remove(int index);

    // Returns the number of elements in this storage.
    int	size();

    // Pretty print the storage
    String toString();

    // Replaces the element at the specified position in this list with the
    // specified element
    T set(int index, T element);

    // Returns the element at the specified position in this storage.
    T get(int index);

}

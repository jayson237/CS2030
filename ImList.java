import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * An immutable implementation of the {@code ArrayList} using an 
 * immutable delegation design pattern.
 *
 * @author  cs2030
 * @param <E> the type of elements in this list
 */
public class ImList<E> implements Iterable<E> {
    private final ArrayList<E> elems;

    /**
     * Constructs an empty list.
     */
    public ImList() {
        this.elems = new ArrayList<E>();
    }

    /**
     * Constructs a list containing the elements of the specified list of
     * type {@code List}, in the order they are returned by the latter's
     * iterator.
     *
     * @param list the list whose elements are to be placed into this list
     * @throws NullPointerException if the specified list is null
     */    
    public ImList(List<? extends E> list) {
        this.elems = new ArrayList<E>(list);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param elem element to be appended to this list
     * @return the list with the element added
     */
    public ImList<E> add(E elem) {
        ImList<E> newList = new ImList<E>(this.elems);
        newList.elems.add(elem);
        return newList;
    }

    /**
     * Appends all of the elements in the specified immutable list to
     * the end of this list, in the order that they are returned by the
     * specified list's Iterator.  
     *
     * @param list list containing elements to be added to this list
     * @return the list with the all elements of the specified list appended
     * @throws NullPointerException if the specified collection is null
     */
    public ImList<E> addAll(ImList<? extends E> list) {
        return this.addAll(list.elems);
    }

    /**
     * Appends all of the elements in the specified (@code List) list to
     * the end of this list, in the order that they are returned by the
     * specified list's Iterator.  
     *
     * @param list list containing elements to be added to this list
     * @return the list with the all elements of the specified list appended
     * @throws NullPointerException if the specified collection is null
     */
    public ImList<E> addAll(List<? extends E> list) {
        ImList<E> newList = new ImList<E>(this.elems);
        newList.elems.addAll(list);
        return newList;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param  index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        return this.elems.get(index);
    }


    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(obj, get(i))},
     * or -1 if there is no such index.
     *
     * @param obj element to search for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    public int indexOf(Object obj) {
        return this.elems.indexOf(obj);
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */ 
    public boolean isEmpty() {
        return this.elems.isEmpty();
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    public Iterator<E> iterator() {
        return this.elems.iterator();
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the list after removal of the element
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public ImList<E> remove(int index) {
        ImList<E> newList = new ImList<E>(this.elems);
        if (index >= 0 && index < this.size()) {
            newList.elems.remove(index);
        }
        return newList;
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index index of the element to replace
     * @param elem element to be stored at the specified position
     * @return the list after replacement of the element
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public ImList<E> set(int index, E elem) {
        ImList<E> newList = new ImList<E>(this.elems);
        if (index >= 0 && index < this.size()) {
            newList.elems.set(index, elem);
        }
        return newList;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return this.elems.size();
    }

    /**
     * Sorts this list according to the order induced by the specified
     * {@link Comparator}.  The sort is <i>stable</i>: this method must not
     * reorder equal elements.
     *
     * <p>All elements in this list must be <i>mutually comparable</i> using the
     * specified comparator (that is, {@code c.compare(e1, e2)} must not throw
     * a {@code ClassCastException} for any elements {@code e1} and {@code e2}
     * in the list).
     *
     * <p>If the specified comparator is {@code null} then all elements in this
     * list must implement the {@link Comparable} interface and the elements'
     * {@linkplain Comparable natural ordering} should be used.
     *
     * @param cmp the {@code Comparator} used to compare list elements.
     *          A {@code null} value indicates that the elements'
     *          {@linkplain Comparable natural ordering} should be used
     * @return the sorted list
     * @throws ClassCastException if the list contains elements that are not
     *         <i>mutually comparable</i> using the specified comparator
     * @throws UnsupportedOperationException if the list's list-iterator does
     *         not support the {@code set} operation
     * @throws IllegalArgumentException
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     *         if the comparator is found to violate the {@link Comparator}
     *         contract
     */
    public ImList<E> sort(Comparator<? super E> cmp) {
        ImList<E> newList = new ImList<E>(this.elems);
        newList.elems.sort(cmp);
        return newList;
    }

    /**
     * Returns a string representation of this list.  The string
     * representation consists of a list of elements in the order they are
     * returned by its iterator, enclosed in square brackets ({@code "[]"}).  
     * Adjacent elements are separated by the characters {@code ", "} (comma and space).
     * Elements are converted to strings as by {@link String#valueOf(Object)}.
     *
     * @return a string representation of this list
     */
    @Override
    public String toString() {
        return this.elems.toString();
    }
}

/*
 * Copyright 2009, 2010 University of Paderborn
 *
 * This file is part of vMAGIC.
 *
 * vMAGIC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * vMAGIC is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with vMAGIC. If not, see <http://www.gnu.org/licenses/>.
 *
 * Authors: Ralf Fuest <rfuest@users.sourceforge.net>
 *          Christopher Pohl <cpohl@users.sourceforge.net>
 */

package de.upb.hni.vmagic.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Forwarding implementation of the <code>List</code> interface.
 * This class is used to easily implement lists which delegate most of its functions
 * to another list and only change some functions.
 * @param <E> the type of the list elements
 */
public class ForwardingList<E> implements List<E> {

    private final List<E> list;

    /**
     * Creates a forwarding list.
     * @param list the list
     */
    public ForwardingList(List<E> list) {
        this.list = list;
    }

    /**
     * Returns the size of this list.
     * @return the size
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns if this list is empty.
     * @return true, if the list is empty
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns if this list contains the given object.
     * @param o the object
     * @return true, if the list contains the give object
     */
    public boolean contains(Object o) {
        return list.contains(o);
    }

    /**
     * Returns an iterator for this list.
     * @return the iterator
     */
    public Iterator<E> iterator() {
        return list.iterator();
    }

    /**
     * Converts this list to an array.
     * @return the array
     */
    public Object[] toArray() {
        return list.toArray();
    }

    /**
     * Converts this list to an array with a specific type.
     * @param <T> the type
     * @param a the array
     * @return the array
     */
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    /**
     * Adds an element to this list.
     * @param e the element
     * @return <code>true</code>
     */
    public boolean add(E e) {
        return list.add(e);
    }

    /**
     * Removes an object from this list.
     * @param o the object
     * @return <code>true</code>, if the object was removed
     */
    public boolean remove(Object o) {
        return list.remove(o);
    }

    /**
     * Returns if this list contains all elements of thie given collection.
     * @param c the collection
     * @return <code>true</code>, if the list contains all elements
     */
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    /**
     * Adds all elements of a collection to this list.
     * @param c the collection
     * @return <code>true</code>, if this list was changed
     */
    public boolean addAll(Collection<? extends E> c) {
        return list.addAll(c);
    }

    /**
     * Adds all elements of a collection to a specific index in this list.
     * @param index the index
     * @param c the collection
     * @return <code>true</code>, if this list was changed
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        return list.addAll(index, c);
    }

    /**
     * Removes all elements in a collection from this list.
     * @param c the collection
     * @return <code>true</code>, if this list was changed
     */
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    /**
     * Removes all elements that are not in the given collection from this list.
     * @param c the collection
     * @return <code>true</code>, if this list was changed
     */
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    /**
     * Clears this list.
     */
    public void clear() {
        list.clear();
    }

    /**
     * Returns the element at the given index.
     * @param index the index
     * @return the element
     */
    public E get(int index) {
        return list.get(index);
    }

    /**
     * Sets the element at the given index.
     * @param index the index
     * @param element the new element
     * @return the old element
     */
    public E set(int index, E element) {
        return list.set(index, element);
    }

    /**
     * Adds an element to a specific position in this list.
     * @param index the index
     * @param element the element
     */
    public void add(int index, E element) {
        list.add(index, element);
    }

    /**
     * Removes the element at a given index.
     * @param index the index
     * @return the removed element
     */
    public E remove(int index) {
        return list.remove(index);
    }

    /**
     * Returns the index of the fist occurence of an object.
     * @param o the object
     * @return the index or -1 if the object is not in the list
     */
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    /**
     * Return the index of the last occurence of an object.
     * @param o the object
     * @return the index or -1 if the object is not in the list
     */
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    /**
     * Returns a list iterator for this list.
     * @return the list iterator
     */
    public ListIterator<E> listIterator() {
        return list.listIterator();
    }

    /**
     * Returns a list iterator with a start index.
     * @param index the index
     * @return the list iterator
     */
    public ListIterator<E> listIterator(int index) {
        return list.listIterator(index);
    }

    /**
     * Returns a sub list of this list.
     * @param fromIndex the first index
     * @param toIndex the last index
     * @return the sub list
     */
    public List<E> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }
}

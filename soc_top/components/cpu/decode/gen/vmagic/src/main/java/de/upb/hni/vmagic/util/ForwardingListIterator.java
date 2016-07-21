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

import java.util.ListIterator;

/**
 * Forwarding implementation of the <code>ListIterator</code> interface.
 * @param <E> the type of the list elements
 */
public class ForwardingListIterator<E> implements ListIterator<E> {

    private final ListIterator<E> iterator;

    /**
     * Creates a forwarding list iterator.
     * @param iterator the base iterator
     */
    public ForwardingListIterator(ListIterator<E> iterator) {
        this.iterator = iterator;
    }

    /**
     * Returns if a next element exists.
     * @return <code>true</code>, if a next element exists
     */
    public boolean hasNext() {
        return iterator.hasNext();
    }

    /**
     * Returns the next element.
     * @return the next element
     */
    public E next() {
        return iterator.next();
    }

    /**
     * Returns if a previous element exist.
     * @return <code>true</code>, if a previous element exists
     */
    public boolean hasPrevious() {
        return iterator.hasPrevious();
    }

    /**
     * Returns the previous element.
     * @return the previous element
     */
    public E previous() {
        return iterator.previous();
    }

    /**
     * Returns the next index.
     * @return the next index
     */
    public int nextIndex() {
        return iterator.nextIndex();
    }

    /**
     * Returns the previous index.
     * @return the previous index
     */
    public int previousIndex() {
        return iterator.previousIndex();
    }

    /**
     * Removes the current element.
     */
    public void remove() {
        iterator.remove();
    }

    /**
     * Sets the current element.
     * @param e the new value
     */
    public void set(E e) {
        iterator.set(e);
    }

    /**
     * Adds an element to the current location.
     * @param e the element
     */
    public void add(E e) {
        iterator.add(e);
    }
}

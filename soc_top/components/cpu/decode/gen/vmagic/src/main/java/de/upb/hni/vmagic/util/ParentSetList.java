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

import de.upb.hni.vmagic.DeclarativeRegion;
import de.upb.hni.vmagic.Scope;
import de.upb.hni.vmagic.Scopes;
import de.upb.hni.vmagic.VhdlElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Implementation of the <code>List</code> interfaces that sets the parent of
 * the added items.
 * @param <E> the type of the list elements
 */
public class ParentSetList<E extends VhdlElement> extends ForwardingList<E> {

    private final DeclarativeRegion parent;

    /**
     * Creates a new ParentSetList which uses an ArrayList for storage.
     * @param <E> the type of the list elements
     * @param parent the parent declarative region
     * @return the parent set list
     */
    public static <E extends VhdlElement> List<E> create(DeclarativeRegion parent) {
        return new ParentSetList<E>(parent);
    }

    /**
     * Creates a new ParentSetList.
     * @param <E> the type of the list elements
     * @param list the list used for element storage
     * @param parent the parent declarative region
     * @return the parent set list
     */
    public static <E extends VhdlElement> List<E> create(DeclarativeRegion parent, List<E> list) {
        return new ParentSetList<E>(parent, list);
    }

    /**
     * Creates a new proxy ParentSetList which uses an ArrayList for storage.
     * A proxy ParentSetList is used for parents which aren't declarative regions
     * (e.g. IfStatements).
     * @param <E> the type of the list elements
     * @param parent the parent
     * @return the proxy list
     */
    public static <E extends VhdlElement> List<E> createProxyList(VhdlElement parent) {
        return new ParentSetList<E>(new ParentProxy(parent));
    }

    /**
     * Creates a new proxy ParentSetList and initializes it from a given list.
     * A proxy ParentSetList is used for parents which aren't declarative regions
     * (e.g. IfStatements).
     * @param <E> the type of the list elements
     * @param list the list used for element storage
     * @param parent the parent
     * @return the proxy list
     */
    public static <E extends VhdlElement> List<E> createProxyList(List<E> list, VhdlElement parent) {
        return new ParentSetList<E>(new ParentProxy(parent), list);
    }

    private ParentSetList(DeclarativeRegion parent, List<E> list) {
        super(list);
        this.parent = parent;
    }

    private ParentSetList(DeclarativeRegion parent) {
        super(new ArrayList<E>());
        this.parent = parent;
    }

    @Override
    public boolean add(E e) {
        if (e != null) {
            e.setParent(parent);
        }

        return super.add(e);
    }

    @Override
    public void add(int index, E element) {
        if (element != null) {
            element.setParent(parent);
        }

        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            if (e != null) {
                e.setParent(parent);
            }
        }
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        for (E e : c) {
            if (e != null) {
                e.setParent(parent);
            }
        }
        return super.addAll(index, c);
    }

    @Override
    public boolean remove(Object o) {
        boolean removed = super.remove(o);
        if (removed) {
            if (o != null && o instanceof VhdlElement) {
                VhdlElement c = (VhdlElement) o;
                c.setParent(null);
            }
        }
        return removed;
    }

    @Override
    public E remove(int index) {
        E element = super.remove(index);
        if (element != null) {
            element.setParent(null);
        }
        return element;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removed = false;
        for (Object o : c) {
            if (remove(o)) {
                removed = true;
            }
        }
        return removed;
    }

    //TODO: implement
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        for (VhdlElement e : this) {
            if (e != null) {
                e.setParent(null);
            }
        }
        super.clear();
    }

    //TODO: implement
    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListIteratorImpl(super.listIterator(index));
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIteratorImpl(super.listIterator());
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return new ParentSetList<E>(parent, super.subList(fromIndex, toIndex));
    }

    private class ListIteratorImpl extends ForwardingListIterator<E> {

        public ListIteratorImpl(ListIterator<E> iterator) {
            super(iterator);
        }

        @Override
        public void add(E e) {
            if (e != null) {
                e.setParent(parent);
            }
            super.add(e);
        }

        //TODO: implement
        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        //TODO: implement
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static final class ParentProxy implements DeclarativeRegion {

        private final Scope scope;

        public ParentProxy(VhdlElement parent) {
            scope = Scopes.createScope(parent);
        }

        public Scope getScope() {
            return scope;
        }
    }
}

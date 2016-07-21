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

package de.upb.hni.vmagic.highlevel;

import java.util.AbstractList;
import java.util.List;

/**
 * Proxy list.
 */
//TODO: remove?
class ProxyList<E> extends AbstractList<E> {

    private final List<E> list1;
    private final List<E> list2;
    private final List<E> list3;

    public ProxyList(List<E> list1, List<E> list2) {
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = null;
    }

    public ProxyList(List<E> list1, List<E> list2, List<E> list3) {
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
    }

    @Override
    public E get(int index) {
        if (index < list1.size()) {
            return list1.get(index);
        }
        index -= list1.size();

        if (index < list2.size()) {
            return list2.get(index);
        }
        index -= list2.size();

        if (list3 != null && index < list3.size()) {
            return list3.get(index);
        }

        throw new IndexOutOfBoundsException();
    }

    @Override
    public int size() {
        int size = list1.size() + list2.size();
        if (list3 == null) {
            return size;
        } else {
            return size + list3.size();
        }
    }
}

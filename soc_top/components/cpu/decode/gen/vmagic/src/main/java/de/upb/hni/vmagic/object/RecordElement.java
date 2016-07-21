/*
 * Copyright 2009, 2010, 2011 University of Paderborn
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

package de.upb.hni.vmagic.object;

import de.upb.hni.vmagic.expression.Name;
import de.upb.hni.vmagic.type.SubtypeIndication;

/**
 * Record element.
 * @param <T> the object type
 */
//TODO: check if record element is a valid signal assignment or variable assignment target
public class RecordElement<T extends Name> extends Name<T>
        implements SignalAssignmentTarget, VariableAssignmentTarget {

    private final T prefix;
    private final String element;

    /**
     * Creates a record element.
     * @param prefix the prefix of this record element
     * @param element the identifier of the element
     */
    public RecordElement(T prefix, String element) {
        this.prefix = prefix;
        this.element = element;
    }

    /**
     * Returns the prefix of this record element.
     * @return the preifx
     */
    public T getPrefix() {
        return prefix;
    }

    /**
     * Returns the identifier of the record element.
     * @return the identifier
     */
    public String getElement() {
        return element;
    }

    @Override
    public SubtypeIndication getType() {
        //TODO: implement correctly
        return prefix.getType();
    }
}

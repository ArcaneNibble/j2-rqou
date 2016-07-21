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

package de.upb.hni.vmagic.type;

/**
 * Abstract base class for array types.
 */
public abstract class ArrayType extends Type {

    private SubtypeIndication elementType;

    /**
     * Creates array type.
     * @param identifier the identifier
     * @param elementType the type of the array elements
     */
    public ArrayType(String identifier, SubtypeIndication elementType) {
        super(identifier);
        this.elementType = elementType;
    }

    /**
     * Returns the type of the array elements.
     * @return the type
     */
    public SubtypeIndication getElementType() {
        return elementType;
    }

    /**
     * Sets the type of the array elements.
     * @param elementType the type
     */
    public void setElementType(SubtypeIndication elementType) {
        this.elementType = elementType;
    }
}

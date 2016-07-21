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
 * File type.
 */
public class FileType extends Type {

    //FIXME: use type_mark instead of subtype indication
    private SubtypeIndication valueType;

    /**
     * Creates a file type.
     * @param identifier the identifier of this file type
     * @param valueType the type of the values in this file type
     */
    public FileType(String identifier, SubtypeIndication valueType) {
        super(identifier);
        this.valueType = valueType;
    }

    /**
     * Returns the type of the values in this file type.
     * @return the type of the values
     */
    public SubtypeIndication getValueType() {
        return valueType;
    }

    /**
     * Sets the type of the values in this file type.
     * @param valueType the type of the values
     */
    public void setValueType(SubtypeIndication valueType) {
        this.valueType = valueType;
    }

    @Override
    void accept(TypeVisitor visitor) {
        visitor.visitFileType(this);
    }
}

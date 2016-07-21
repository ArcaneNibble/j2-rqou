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
 * Access type.
 */
public class AccessType extends Type {

    private SubtypeIndication designatedSubtype;

    /**
     * Creates a access type.
     * @param identifier the identifier
     * @param designatedSubtype the designated subtype
     */
    public AccessType(String identifier, SubtypeIndication designatedSubtype) {
        super(identifier);
        this.designatedSubtype = designatedSubtype;
    }

    /**
     * Returns the designated subtype.
     * @return the subtype
     */
    public SubtypeIndication getDesignatedSubtype() {
        return designatedSubtype;
    }

    /**
     * Sets the designated subtype.
     * @param designatedSubtype the subtype
     */
    public void setDesignatedSubtype(SubtypeIndication designatedSubtype) {
        this.designatedSubtype = designatedSubtype;
    }

    @Override
    void accept(TypeVisitor visitor) {
        visitor.visitAccessType(this);
    }
}

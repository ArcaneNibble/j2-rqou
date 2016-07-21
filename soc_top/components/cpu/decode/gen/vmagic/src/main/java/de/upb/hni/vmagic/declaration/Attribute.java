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

package de.upb.hni.vmagic.declaration;

import de.upb.hni.vmagic.NamedEntity;
import de.upb.hni.vmagic.type.SubtypeIndication;

/**
 * Attribute declaration.
 */
public class Attribute extends DeclarativeItem
        implements BlockDeclarativeItem, EntityDeclarativeItem, PackageDeclarativeItem,
        ProcessDeclarativeItem, SubprogramDeclarativeItem, NamedEntity {

    private String identifier;
    //FIXME: use type mark instead of subtype indication
    private SubtypeIndication type;

    /**
     * Creates a attribute declartion.
     * @param identifier the identifer
     * @param type the type of this attribtue
     */
    public Attribute(String identifier, SubtypeIndication type) {
        this.identifier = identifier;
        this.type = type;
    }

    /**
     * Returns the identifier of this attribtue.
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier of this attribute.
     * @param identifier the identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the type of this attribtue.
     * @return the type
     */
    public SubtypeIndication getType() {
        return type;
    }

    /**
     * Sets the type of this attribtue.
     * @param type the type
     */
    public void setType(SubtypeIndication type) {
        this.type = type;
    }

    @Override
    void accept(DeclarationVisitor visitor) {
        visitor.visitAttributeDeclaration(this);
    }
}

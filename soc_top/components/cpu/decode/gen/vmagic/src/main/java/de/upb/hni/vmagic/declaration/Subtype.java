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
 * Subtype declaration.
 */
public class Subtype extends DeclarativeItem implements BlockDeclarativeItem,
        EntityDeclarativeItem, PackageBodyDeclarativeItem, PackageDeclarativeItem,
        ProcessDeclarativeItem, SubprogramDeclarativeItem, SubtypeIndication, NamedEntity {

    private String identifier;
    private SubtypeIndication subtypeIndication;

    /**
     * Creates a subtype declaration.
     * @param identifier the identifier of this subtype declaration
     * @param subtypeIndication the subtype indication
     */
    public Subtype(String identifier, SubtypeIndication subtypeIndication) {
        this.identifier = identifier;
        this.subtypeIndication = subtypeIndication;
    }

    /**
     * Returns the identifier of this subtye.
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier of this subtype.
     * @param identifier the identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Return the subtype indication.
     * @return the subtype indication
     */
    public SubtypeIndication getSubtypeIndication() {
        return subtypeIndication;
    }

    /**
     * Sets the subtype indication.
     * @param subtypeIndication the subtype indication
     */
    public void setSubtypeIndication(SubtypeIndication subtypeIndication) {
        this.subtypeIndication = subtypeIndication;
    }

    @Override
    void accept(DeclarationVisitor visitor) {
        visitor.visitSubtypeDeclaration(this);
    }
}

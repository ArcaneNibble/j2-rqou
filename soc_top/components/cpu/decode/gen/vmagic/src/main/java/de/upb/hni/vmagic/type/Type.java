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

import de.upb.hni.vmagic.NamedEntity;
import de.upb.hni.vmagic.VhdlElement;
import de.upb.hni.vmagic.declaration.BlockDeclarativeItem;
import de.upb.hni.vmagic.declaration.EntityDeclarativeItem;
import de.upb.hni.vmagic.declaration.PackageBodyDeclarativeItem;
import de.upb.hni.vmagic.declaration.PackageDeclarativeItem;
import de.upb.hni.vmagic.declaration.ProcessDeclarativeItem;
import de.upb.hni.vmagic.declaration.SubprogramDeclarativeItem;

/**
 * Type.
 */
public abstract class Type extends VhdlElement
        implements BlockDeclarativeItem, EntityDeclarativeItem,
        PackageBodyDeclarativeItem, PackageDeclarativeItem, ProcessDeclarativeItem,
        SubprogramDeclarativeItem, SubtypeIndication, NamedEntity {

    private String identifier;

    /**
     * Creates a type.
     * @param identifier the type's identifier
     */
    public Type(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the type's identifier.
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the type's identifier.
     * @param identifier the identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    abstract void accept(TypeVisitor visitor);
}

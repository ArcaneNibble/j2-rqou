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

/**
 * Unresolved type.
 */
//TODO: remove class?
public class UnresolvedType implements SubtypeIndication, NamedEntity {

    /** Unresolved type with unknown name. */
    public static final UnresolvedType NO_NAME = new UnresolvedType("no_name");
    private String identifier;

    /**
     * Creates an unresolved type.
     * @param identifier the identifier
     */
    public UnresolvedType(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Sets the identifier of this type.
     * @param identifier the identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the identifier of this type.
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }
}

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

import de.upb.hni.vmagic.output.OutputEnum;

/**
 * Entity class enumeration.
 * The entity class enumeration is used to describe the type of objects in attribute
 * specifications and group template declartions.
 */
public enum EntityClass implements OutputEnum {

    /** Entity. */
    ENTITY("entity"),
    /** Architecture. */
    ARCHITECTURE("architecture"),
    /** Configuration. */
    CONFIGURATION("configuration"),
    /** Procedure. */
    PROCEDURE("procedure"),
    /** Function. */
    FUNCTION("function"),
    /** Package. */
    PACKAGE("package"),
    /** Type. */
    TYPE("type"),
    /** Subtype. */
    SUBTYPE("subtype"),
    /** Constant. */
    CONSTANT("constant"),
    /** Signal. */
    SIGNAL("signal"),
    /** Variable. */
    VARIABLE("variable"),
    /** Component */
    COMPONENT("component"),
    /** Label. */
    LABEL("label"),
    /** Literal. */
    LITERAL("literal"),
    /** Units. */
    UNITS("units"),
    /** Group. */
    GROUP("group"),
    /** File. */
    FILE("file");
    private final String lower;
    private final String upper;

    EntityClass(String text) {
        this.lower = text;
        this.upper = text.toUpperCase();
    }

    public String getUpperCase() {
        return upper;
    }

    public String getLowerCase() {
        return lower;
    }
}

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

package de.upb.hni.vmagic.builtin;

import de.upb.hni.vmagic.declaration.Attribute;

/**
 * Predefined signal attributes.
 */
//TODO: fix types
public class SignalAttributes {

    /** DELAYED attribute. */
    public static final Attribute DELAYED = new Attribute("DELAYED", null);
    /** STABLE attribute. */
    public static final Attribute STABLE = new Attribute("STABLE", Standard.BOOLEAN);
    /** QUIET attribute. */
    public static final Attribute QUIET = new Attribute("QUIET", Standard.BOOLEAN);
    /** TRANSACTION attribute. */
    public static final Attribute TRANSACTION = new Attribute("TRANSACTION", Standard.BIT);
    /** EVENT attribute. */
    public static final Attribute EVENT = new Attribute("EVENT", Standard.BOOLEAN);
    /** ACTIVE attribute. */
    public static final Attribute ACTIVE = new Attribute("ACTIVE", Standard.BOOLEAN);
    /** LAST_EVENT attribute. */
    public static final Attribute LAST_EVENT = new Attribute("LAST_EVENT", null);
    /** LAST_ACTIVE attribute. */
    public static final Attribute LAST_ACTIVE = new Attribute("LAST_ACTIVE", null);
    /** LAST_VALUE attribute. */
    public static final Attribute LAST_VALUE = new Attribute("LAST_VALUE", null);
    /** DRIVING attribute. */
    public static final Attribute DRIVING = new Attribute("DRIVING", Standard.BOOLEAN);
    /** DRIVING_VALUE attribute. */
    public static final Attribute DRIVING_VALUE = new Attribute("DRIVING_VALUE", null);

    /**
     * Prevent instantiation.
     */
    private SignalAttributes() {
    }
}

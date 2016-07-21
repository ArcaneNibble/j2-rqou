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

package de.upb.hni.vmagic.literal;

import de.upb.hni.vmagic.type.SubtypeIndication;

/**
 * Decimal literal.
 */
public class DecimalLiteral extends AbstractLiteral<DecimalLiteral> {

    private String value;

    /**
     * Creates a decimal literal.
     * @param value the value
     */
    public DecimalLiteral(String value) {
        this.value = value;
    }

    /**
     * Creates a decimal literal by converting a integer value.
     * @param value the integer value
     */
    public DecimalLiteral(int value) {
        this.value = String.valueOf(value);
    }

    /**
     * Returns the value.
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     * @param value the value
     */
    public void setValue(String value) {
        this.value = value;
    }

    public SubtypeIndication getType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DecimalLiteral copy() {
        return new DecimalLiteral(value);
    }

    @Override
    public String toString() {
        return value;
    }
}

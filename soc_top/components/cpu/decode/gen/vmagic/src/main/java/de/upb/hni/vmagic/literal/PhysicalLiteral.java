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

import de.upb.hni.vmagic.expression.Literal;
import de.upb.hni.vmagic.type.SubtypeIndication;

/**
 * Physical literal.
 */
public class PhysicalLiteral extends Literal<PhysicalLiteral> {

    private String value;
    private String unit;

    /**
     * Creates a physical literal containing only a unit.
     * @param unit the unit
     */
    public PhysicalLiteral(String unit) {
        this.unit = unit;
    }

    /**
     * Creates a physical literal.
     * @param value the value
     * @param unit the unit
     */
    public PhysicalLiteral(String value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    /**
     * Returns the unit.
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the unit.
     * @param unit the unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
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
    public PhysicalLiteral copy() {
        return new PhysicalLiteral(value, unit);
    }

    @Override
    public String toString() {
        if (value != null) {
            return value + ' ' + unit;
        } else {
            return unit;
        }
    }
}

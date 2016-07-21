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

import de.upb.hni.vmagic.RangeProvider;

/**
 * Integer type.
 */
public class IntegerType extends Type {

    private RangeProvider range;

    /**
     * Creates a integer type.
     * @param identifier the identifier of this type
     * @param range the range of this integer type
     */
    public IntegerType(String identifier, RangeProvider range) {
        super(identifier);
        this.range = range;
    }

    /**
     * Returns the range of this integer type.
     * @return the range
     */
    public RangeProvider getRange() {
        return range;
    }

    /**
     * Sets the range of this integer type.
     * @param range the range
     */
    public void setRange(RangeProvider range) {
        this.range = range;
    }

    @Override
    void accept(TypeVisitor visitor) {
        visitor.visitIntegerType(this);
    }
}

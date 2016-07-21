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

package de.upb.hni.vmagic;

import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.output.OutputEnum;

/**
 * Range attribute name.
 */
//TODO: replace class by a better solution
public class RangeAttributeName extends RangeProvider<RangeAttributeName> {

    private String prefix;
    private Type type;
    private Expression index;

    /**
     * Creates a range attribute name.
     * @param prefix the prefix
     * @param type the type
     */
    public RangeAttributeName(String prefix, Type type) {
        this.prefix = prefix;
        this.type = type;
    }

    /**
     * Creates a range attribute name.
     * @param prefix the prefix
     * @param type the type
     * @param index the index
     */
    public RangeAttributeName(String prefix, Type type, Expression index) {
        this.prefix = prefix;
        this.type = type;
        this.index = index;
    }

    /**
     * Returns the index.
     * @return the index
     */
    public Expression getIndex() {
        return index;
    }

    /**
     * Sets the index.
     * @param index the index
     */
    public void setIndex(Expression index) {
        this.index = index;
    }

    /**
     * Returns the prefix.
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets the prefix.
     * @param prefix the prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Returns the type.
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the type.
     * @param type the type
     */
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public RangeAttributeName copy() {
        return new RangeAttributeName(prefix, type, index.copy());
    }

    /**
     * Type of a range attribute name.
     */
    public enum Type implements OutputEnum {

        /** Name with 'RANGE suffix. */
        RANGE,
        /** Name with 'REVERSE_RANGE suffix. */
        REVERSE_RANGE;

        public String getLowerCase() {
            return toString().toLowerCase();
        }

        public String getUpperCase() {
            return toString().toUpperCase();
        }
    }
}

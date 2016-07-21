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
 * Binary literal.
 */
public class BinaryLiteral extends Literal<BinaryLiteral> {

    private final String value;

    /**
     * Creates a binary literal.
     * @param value the value
     */
    public BinaryLiteral(String value) {
        this.value = value;
    }

    /**
     * Creates a binary literal by converting a integer.
     * @param value the integer value
     * @param width the width of the binary literal in bits
     */
    public BinaryLiteral(int value, int width) {
        this.value = toBinary(value, width);
    }

    /**
     * Converts an integer to binary.
     * If the value is to big to be expressed with the given width only the
     * lower bits are used.
     * @param value the numeric value
     * @param width the width in bits
     * @return a string containing the binary
     */
    private String toBinary(int value, int width) {
        String binString = Integer.toBinaryString(value);

        int length = binString.length();

        if (length > width) {
            binString = binString.substring(length - width, length);
        } else {
            for (int i = length; i < width; i++) {
                if (value >= 0) {
                    binString = "0" + binString;
                } else {
                    binString = "1" + binString;
                }
            }
        }
        return binString;
    }

    public SubtypeIndication getType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BinaryLiteral copy() {
        return new BinaryLiteral(value);
    }

    @Override
    public String toString() {
        return "b\"" + value + '"';
    }
}

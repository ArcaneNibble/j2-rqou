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

package de.upb.hni.vmagic.expression;

/**
 * Expression precedence constants.
 */
class ExpressionPrecedences {

    /**
     * Logical expression precedence.
     */
    public static final int LOGICAL_EXPRESSION = 1;

    /**
     * Relational expression precedence.
     */
    public static final int RELATIONAL_EXPRESSION = 2;

    /**
     * Shift expression precedence.
     */
    public static final int SHIFT_EXPRESSION = 3;

    /**
     * Adding expression precedence.
     */
    public static final int ADDING_EXPRESSION = 4;

    /**
     * Multiplying expression precedence.
     */
    public static final int MULTIPLYING_EXPRESSION = 5;

    /**
     * Miscellaneous expression precedence.
     */
    public static final int MISCELLANEOUS_EXPRESSION = 6;

    /**
     * Primary precedence.
     */
    public static final int PRIMARY = 7;

    /**
     * Prevent instantiation.
     */
    private ExpressionPrecedences() {
    }
}

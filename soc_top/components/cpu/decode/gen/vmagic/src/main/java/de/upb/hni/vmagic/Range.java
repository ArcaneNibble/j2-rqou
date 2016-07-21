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

import de.upb.hni.vmagic.literal.DecimalLiteral;
import de.upb.hni.vmagic.expression.Expression;

/**
 * Range.
 */
public class Range extends RangeProvider {

    private Expression from;
    private Direction direction;
    private Expression to;

    /**
     * Creates a range.
     * @param from the from expression
     * @param direction the direction
     * @param to the to expression
     */
    public Range(Expression from, Direction direction, Expression to) {
        this.from = from;
        this.direction = direction;
        this.to = to;
    }

    /**
     * Creates a range with integer bounds.
     * @param from the from value
     * @param direction the direction
     * @param to the to value
     */
    public Range(int from, Direction direction, int to) {
        this(new DecimalLiteral(from), direction, new DecimalLiteral(to));
    }

    /**
     * Sets the direction of this range.
     * @param direction the direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Returns the direction of this range.
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the from expression.
     * @param from the from expression
     */
    public void setFrom(Expression from) {
        this.from = from;
    }

    /**
     * Sets the from expression.
     * @param from the value of the from expression
     */
    public void setFrom(int from) {
        this.from = new DecimalLiteral(from);
    }

    /**
     * Returns the from expression.
     * @return the from expression
     */
    public Expression getFrom() {
        return from;
    }

    /**
     * Sets the to expression.
     * @param to the to expression
     */
    public void setTo(Expression to) {
        this.to = to;
    }

    /**
     * Sets the to expression.
     * @param to the value of the to expression
     */
    public void setTo(int to) {
        this.to = new DecimalLiteral(to);
    }

    /**
     * Returns the to expression.
     * @return the to expression
     */
    public Expression getTo() {
        return to;
    }

    @Override
    public Choice copy() {
        return new Range(from.copy(), direction, to.copy());
    }

    /**
     * Range direction.
     */
    public enum Direction {

        /** TO direction */
        TO,
        /** DOWNTO direction */
        DOWNTO
    }
}

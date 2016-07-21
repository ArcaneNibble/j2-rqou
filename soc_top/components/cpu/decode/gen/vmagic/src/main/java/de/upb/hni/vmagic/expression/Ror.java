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
 * Rotate right logical expression.
 */
public class Ror extends ShiftExpression<Ror> {

    /**
     * Create a rotate right logical expression.
     * @param left the left-hand side expression
     * @param right the right-hand side expression
     */
    public Ror(Expression left, Expression right) {
        super(left, ExpressionKind.ROR, right);
    }

    @Override
    public Ror copy() {
        return new Ror(getLeft().copy(), getRight().copy());
    }
}

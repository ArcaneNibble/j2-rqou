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

import de.upb.hni.vmagic.type.SubtypeIndication;

/**
 * Concatenation expression.
 */
public class Concatenate extends AddingExpression<Concatenate> {

    /**
     * Creates a concatenation epxression
     * @param left the left-hand side expression
     * @param right the right-hand side expression
     */
    public Concatenate(Expression left, Expression right) {
        super(left, ExpressionKind.CONCATENATE, right);
    }

    //TODO: implement using subtype indication
    public SubtypeIndication getType() {
        throw new UnsupportedOperationException();
        /*SubtypeIndication leftType = getLeft().getType();
        SubtypeIndication rightType = getLeft().getType();

        boolean isLeftArray = leftType instanceof ArrayTypeDefinition;
        boolean isRightArray = rightType instanceof ArrayTypeDefinition;

        if (!isLeftArray && !isRightArray) {
        return new ConstrainedArrayDefinition(null, new Range(1, Range.Direction.TO, 0), leftType);
        } else {
        return null;
        }*/
    }

    @Override
    public Concatenate copy() {
        return new Concatenate(getLeft().copy(), getRight().copy());
    }
}

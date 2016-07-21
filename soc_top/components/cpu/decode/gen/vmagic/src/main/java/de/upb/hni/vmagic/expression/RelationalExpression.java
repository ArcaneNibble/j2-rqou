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

import de.upb.hni.vmagic.builtin.Standard;
import de.upb.hni.vmagic.type.SubtypeIndication;

/**
 * Abstract base class for relational expressions.
 */
public abstract class RelationalExpression<T extends RelationalExpression> extends BinaryExpression<T> {

    /**
     * Creates a <code>RelationalExpression</code>.
     * @param left the left-hand side expression
     * @param kind the expression kind
     * @param right the right-hand side expression
     */
    RelationalExpression(Expression left, ExpressionKind kind, Expression right) {
        super(left, kind, right, ExpressionPrecedences.RELATIONAL_EXPRESSION);
    }

    public SubtypeIndication getType() {
        return Standard.BOOLEAN;
    }
}

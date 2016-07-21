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
 * Abstract base class for unary expressions.
 */
public abstract class UnaryExpression<T extends UnaryExpression> extends Expression<T> {

    private Expression expression;
    private final int precedence;
    private final ExpressionKind kind;

    UnaryExpression(Expression expression, ExpressionKind kind, int precedence) {
        this.expression = expression;
        this.precedence = precedence;
        this.kind = kind;
    }

    /**
     * Sets the parameter expression.
     * @return the parameter expression
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Sets the parameter expression.
     * @param expression the parameter expression
     */
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public SubtypeIndication getType() {
        return expression.getType();
    }

    @Override
    public int getPrecedence() {
        return precedence;
    }

    /**
     * Returns the kind of this expression.
     * @return the expression kind
     */
    //TODO: make package private?
    public ExpressionKind getExpressionKind() {
        return kind;
    }

    @Override
    void accept(ExpressionVisitor visitor) {
        visitor.visitUnaryExpression(this);
    }
}

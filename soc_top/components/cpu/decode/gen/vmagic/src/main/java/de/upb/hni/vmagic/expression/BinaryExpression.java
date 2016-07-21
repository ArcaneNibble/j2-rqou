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
 * Abstract base class for binary expressions.
 */
public abstract class BinaryExpression<T extends BinaryExpression> extends Expression<T> {

    private Expression left;
    private Expression right;
    private final ExpressionKind kind;
    private final int precedence;

    /**
     * Creates a new <code>BinaryExpression</code>.
     * @param left the left-hand side expression
     * @param kind the expression kind
     * @param right the right-hand side expression
     * @param precedence the precedence of this operation
     */
    BinaryExpression(Expression left, ExpressionKind kind, Expression right,
            int precedence) {
        this.left = left;
        this.kind = kind;
        this.right = right;
        this.precedence = precedence;
    }

    /**
     * Returns the left-hand side expression of this binary exprssion.
     * @return the left-hand side expression
     */
    public Expression getLeft() {
        return left;
    }

    /**
     * Sets the left-hand side expression of this binary expression.
     * @param left the left-hand side expression
     */
    public void setLeft(Expression left) {
        this.left = left;
    }

    /**
     * Returns the right-hand side expression of this binary exprssion.
     * @return the right-hand side expression
     */
    public Expression getRight() {
        return right;
    }

    /**
     * Sets the right-hand side expression of this binary expression.
     * @param right the right-hand side expression
     */
    public void setRight(Expression right) {
        this.right = right;
    }

    /**
     * Returns the expression kind
     * @return the expression kind
     */
    //TODO: make package private?
    public ExpressionKind getExpressionKind() {
        return kind;
    }

    public int getPrecedence() {
        return precedence;
    }

    @Override
    void accept(ExpressionVisitor visitor) {
        visitor.visitBinaryExpression(this);
    }
}

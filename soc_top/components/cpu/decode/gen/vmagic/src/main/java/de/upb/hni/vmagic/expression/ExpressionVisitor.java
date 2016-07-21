/*
 * Copyright 2009, 2010, 2011 University of Paderborn
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
 * Expression visitor.
 * The expression visitor visits all parts of an expression.
 * To use this class you need to subclass it and override the <code>visit...()</code> methods
 * you want to handle.
 */
public class ExpressionVisitor {

    /**
     * Visits an expression.
     * No visit method is called when the parameter is <code>null</code>.
     * @param expression the expression
     */
    public void visit(Expression expression) {
        if (expression != null) {
            expression.accept(this);
        }
    }

    /**
     * Visits an aggregate.
     * @param expression the aggregate
     */
    protected void visitAggregate(Aggregate expression) {
    }

    /**
     * Visits a binary expression.
     * @param expression the expression
     */
    protected void visitBinaryExpression(BinaryExpression expression) {
        visit(expression.getLeft());
        visit(expression.getRight());
    }

    /**
     * Visits a function call.
     * @param expression the function call
     */
    protected void visitFunctionCall(FunctionCall expression) {
    }

    /**
     * Visits a literal.
     * @param expression the literal
     */
    protected void visitLiteral(Literal expression) {
    }

    /**
     * Visits a parentheses expression.
     * @param expression the parentheses expression
     */
    protected void visitParentheses(Parentheses expression) {
    }

    /**
     * Visits a qualified expression.
     * @param expression the qualified expression
     */
    protected void visitQualifiedExpression(QualifiedExpression expression) {
    }

    /**
     * Vistis a qualified expression allocator.
     * @param expression the qualified expression allocator
     */
    protected void visitQualifiedExpressionAllocator(QualifiedExpressionAllocator expression) {
    }

    /**
     * Visits a subtype indication allocator.
     * @param expression the subtype indication allocator
     */
    protected void visitSubtypeIndicationAllocator(SubtypeIndicationAllocator expression) {
    }

    /**
     * Visits a type conversion.
     * @param expression the type conversion
     */
    protected void visitTypeConversion(TypeConversion expression) {
        visit(expression.getExpression());
    }

    /**
     * Visits a unary expression.
     * @param expression the expression
     */
    protected void visitUnaryExpression(UnaryExpression expression) {
        visit(expression.getExpression());
    }

    /**
     * Visits a name.
     * @param name the name
     */
    protected void visitName(Name object) {
    }
}

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

package de.upb.hni.vmagic.output;

import de.upb.hni.vmagic.Choice;
import de.upb.hni.vmagic.expression.Aggregate;
import de.upb.hni.vmagic.expression.BinaryExpression;
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.expression.ExpressionKind;
import de.upb.hni.vmagic.expression.ExpressionVisitor;
import de.upb.hni.vmagic.expression.FunctionCall;
import de.upb.hni.vmagic.expression.Literal;
import de.upb.hni.vmagic.expression.Name;
import de.upb.hni.vmagic.expression.Parentheses;
import de.upb.hni.vmagic.expression.QualifiedExpression;
import de.upb.hni.vmagic.expression.QualifiedExpressionAllocator;
import de.upb.hni.vmagic.expression.SubtypeIndicationAllocator;
import de.upb.hni.vmagic.expression.TypeConversion;
import de.upb.hni.vmagic.expression.UnaryExpression;

/**
 * Expression output visitor.
 */
class VhdlExpressionVisitor extends ExpressionVisitor {

    private final VhdlWriter writer;
    private final OutputModule output;

    public VhdlExpressionVisitor(VhdlWriter writer, OutputModule output) {
        this.writer = writer;
        this.output = output;
    }

    @Override
    public void visit(Expression expression) {
        VhdlOutputHelper.handleAnnotationsBefore(expression, writer);
        super.visit(expression);
        VhdlOutputHelper.handleAnnotationsAfter(expression, writer);
    }

    private void appendExpression(Expression expression, int precedence) {
        if (expression == null) {
            writer.append("null");
            return;
        }

        boolean writeParenthesis = expression.getPrecedence() < precedence;

        if (writeParenthesis) {
            writer.append("(");
        }
        visit(expression);
        if (writeParenthesis) {
            writer.append(")");
        }
    }

    @Override
    protected void visitAggregate(Aggregate expression) {
        writer.append('(');

        boolean first = true;
        for (Aggregate.ElementAssociation association : expression.getAssociations()) {
            if (first) {
                first = false;
            } else {
                writer.append(", ");
            }

            if (!association.getChoices().isEmpty()) {
                boolean first2 = true;
                for (Choice choice : association.getChoices()) {
                    if (first2) {
                        first2 = false;
                    } else {
                        writer.append(" | ");
                    }
                    output.writeChoice(choice);
                }
                writer.append(" => ");
            }
            visit(association.getExpression());
        }

        writer.append(')');
    }

    @Override
    protected void visitBinaryExpression(BinaryExpression expression) {
        appendExpression(expression.getLeft(), expression.getPrecedence());
        writer.append(' ');
        writer.append(expression.getExpressionKind());
        writer.append(' ');
        appendExpression(expression.getRight(), expression.getPrecedence());
    }

    @Override
    protected void visitFunctionCall(FunctionCall expression) {
        writer.appendIdentifier(expression.getFunction());
        if (!expression.getParameters().isEmpty()) {
            writer.append('(');
            output.getMiscellaneousElementOutput().functionCallParameters(expression.getParameters());
            writer.append(')');
        }
    }

    @Override
    protected void visitLiteral(Literal expression) {
        writer.append(expression.toString());
    }

    @Override
    protected void visitParentheses(Parentheses expression) {
        writer.append("(");
        visit(expression.getExpression());
        writer.append(")");
    }

    @Override
    protected void visitQualifiedExpression(QualifiedExpression expression) {
        output.writeSubtypeIndication(expression.getType());
        writer.append('\'');
        visit(expression.getOperand());
    }

    @Override
    protected void visitQualifiedExpressionAllocator(QualifiedExpressionAllocator expression) {
        writer.append(Keyword.NEW).append(' ');
        visit(expression.getExpression());
    }

    @Override
    protected void visitSubtypeIndicationAllocator(SubtypeIndicationAllocator expression) {
        writer.append(Keyword.NEW).append(' ');
        output.writeSubtypeIndication(expression.getType());
    }

    @Override
    protected void visitTypeConversion(TypeConversion expression) {
        output.writeSubtypeIndication(expression.getType());
        writer.append('(');
        visit(expression.getExpression());
        writer.append(')');
    }

    @Override
    protected void visitUnaryExpression(UnaryExpression expression) {
        writer.append(expression.getExpressionKind());
        if (expression.getExpressionKind() != ExpressionKind.MINUS
                && expression.getExpressionKind() != ExpressionKind.PLUS) {
            writer.append(' ');
        }
        appendExpression(expression.getExpression(), expression.getPrecedence());
    }

    @Override
    protected void visitName(Name name) {
        VhdlObjectOutputHelper.name(name, writer, output);
    }
}

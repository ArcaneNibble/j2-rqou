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
 * Allocator with qualified expression parameter.
 */
public class QualifiedExpressionAllocator extends Primary<QualifiedExpressionAllocator> {

    private QualifiedExpression expression;

    /**
     * Creates a qualified expression allocator.
     * @param expression the qualified expression
     */
    public QualifiedExpressionAllocator(QualifiedExpression expression) {
        this.expression = expression;
    }

    /**
     * Returns the qualified expression.
     * @return the qualified expression
     */
    public QualifiedExpression getExpression() {
        return expression;
    }

    /**
     * Sets the qualified expression.
     * @param expression the qualified expression
     */
    public void setExpression(QualifiedExpression expression) {
        this.expression = expression;
    }

    public SubtypeIndication getType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public QualifiedExpressionAllocator copy() {
        return new QualifiedExpressionAllocator(expression.copy());
    }

    @Override
    void accept(ExpressionVisitor visitor) {
        visitor.visitQualifiedExpressionAllocator(this);
    }
}

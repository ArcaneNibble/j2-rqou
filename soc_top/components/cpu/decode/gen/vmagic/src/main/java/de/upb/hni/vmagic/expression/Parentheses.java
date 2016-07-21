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
 * Parentheses expression.
 */
public class Parentheses extends Primary<Parentheses> {

    private Expression expression;

    /**
     * Creates a parentheses expression.
     * @param expression the expression inside the parentheses
     */
    public Parentheses(Expression expression) {
        this.expression = expression;
    }

    /**
     * Returns the expression inside the parentheses.
     * @return the expression inside the parentheses
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Sets the expression inside the parentheses.
     * @param expression the expression inside the parentheses
     */
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public SubtypeIndication getType() {
        return expression.getType();
    }

    @Override
    void accept(ExpressionVisitor visitor) {
        visitor.visitParentheses(this);
    }

    @Override
    public Parentheses copy() {
        return new Parentheses(expression.copy());
    }
}

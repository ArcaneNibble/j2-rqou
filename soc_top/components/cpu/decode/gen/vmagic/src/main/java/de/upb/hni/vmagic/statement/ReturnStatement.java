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

package de.upb.hni.vmagic.statement;

import de.upb.hni.vmagic.expression.Expression;

/**
 * Return statement.
 */
public class ReturnStatement extends SequentialStatement {

    private Expression returnedExpression;

    /**
     * Creates a new ReturnStatement without a returned expression.
     */
    public ReturnStatement() {
    }

    /**
     * Creates a new ReturnStatement with a returned expression.
     * @param returnedExpression the returned expression
     */
    public ReturnStatement(Expression returnedExpression) {
        this.returnedExpression = returnedExpression;
    }

    /**
     * Returns the returned expression.
     * @return the returned expression or <code>null</code> if no expression is
     * returned
     */
    public Expression getReturnedExpression() {
        return returnedExpression;
    }

    /**
     * Sets the returned expression.
     * @param returnedExpression the returned expression or <code>null</code> to
     * remove the returned expression
     */
    public void setReturnedExpression(Expression returnedExpression) {
        this.returnedExpression = returnedExpression;
    }

    @Override
    void accept(SequentialStatementVisitor visitor) {
        visitor.visitReturnStatement(this);
    }
}

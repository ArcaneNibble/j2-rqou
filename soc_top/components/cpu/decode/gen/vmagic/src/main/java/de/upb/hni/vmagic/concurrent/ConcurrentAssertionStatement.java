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

package de.upb.hni.vmagic.concurrent;

import de.upb.hni.vmagic.expression.Expression;

/**
 * Concurrent assertion statement.
 */
public class ConcurrentAssertionStatement extends EntityStatement {

    private Expression condition;
    private Expression reportedExpression;
    private Expression severity;

    /**
     * Creates a concurrent assertion statement.
     * @param condition the asertion condition
     */
    public ConcurrentAssertionStatement(Expression condition) {
        this.condition = condition;
    }

    /**
     * Creates a concurrent assertion statement with a reported message.
     * @param condition the condition
     * @param reportedExpression the reported message
     */
    public ConcurrentAssertionStatement(Expression condition, Expression reportedExpression) {
        this.condition = condition;
        this.reportedExpression = reportedExpression;
    }

    //TODO: find other solution
    /*
    public ConcurrentAssertionStatement(Expression condition, Expression severity) {
    this.condition = condition;
    this.severity = severity;
    }*/
    /**
     * Creates a concurrent assertion statement with reported message and severity.
     * @param condition the condition
     * @param reportedExpression the reported message
     * @param severity the severity
     */
    public ConcurrentAssertionStatement(Expression condition, Expression reportedExpression,
            Expression severity) {
        this.condition = condition;
        this.reportedExpression = reportedExpression;
        this.severity = severity;
    }

    /**
     * Returns the assertion condition.
     * @return the condition
     */
    public Expression getCondition() {
        return condition;
    }

    /**
     * Sets the assertion condition.
     * @param condition the condition
     */
    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    /**
     * Returns the reported message expression.
     * @return the reported expression
     */
    public Expression getReportedExpression() {
        return reportedExpression;
    }

    /**
     * Sets the reported expression.
     * @param reportedExpression the reported message
     */
    public void setReportedExpression(Expression reportedExpression) {
        this.reportedExpression = reportedExpression;
    }

    /**
     * Returns the severity of this assertion.
     * @return the severity or <code>null</code> if no severity is specified
     */
    public Expression getSeverity() {
        return severity;
    }

    /**
     * Sets the severity of this assertion.
     * @param severity the severity or <code>null</code> to remove the severity
     */
    public void setSeverity(Expression severity) {
        this.severity = severity;
    }

    @Override
    void accept(ConcurrentStatementVisitor visitor) {
        visitor.visitConcurrentAssertionStatement(this);
    }
}

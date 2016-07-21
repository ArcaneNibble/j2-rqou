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
import de.upb.hni.vmagic.literal.StringLiteral;

/**
 * Assertion statement.
 *
 * @vmagic.example
 * AssertionStatement statement = new AssertionStatement(
 *  Standard.BOOLEAN_TRUE, "true is not true", Standard.SEVERITY_LEVEL_NOTE);
 * ---
 * assert TRUE report "true is true" severity NOTE;
 */
public class AssertionStatement extends SequentialStatement {

    private Expression condition;
    private Expression reportedExpression;
    private Expression severity;

    /**
     * Creates an assertion statement.
     * @param condition the assertion condition
     */
    public AssertionStatement(Expression condition) {
        this.condition = condition;
    }

    /**
     * Creates an assertion statement with a reported message.
     * @param condition the assertion condtion
     * @param reportedExpression the reported message
     */
    public AssertionStatement(Expression condition, Expression reportedExpression) {
        this.condition = condition;
        this.reportedExpression = reportedExpression;
    }

    /**
     * Creates an assertion statement with a reported expression.
     * @param condition the assertion condition
     * @param reportedExpression the reported message
     */
    public AssertionStatement(Expression condition, String reportedExpression) {
        this(condition, new StringLiteral(reportedExpression));
    }

    //TODO: find other solution
    /*public AssertionStatement(Expression condition, Expression severity) {
    this.condition = condition;
    this.severity = severity;
    }*/
    /**
     * Creates an assertion statement with reported message and severity.
     * @param condition the assertion condition
     * @param reportedExpression the reported message
     * @param severity the severity
     */
    public AssertionStatement(Expression condition, Expression reportedExpression,
            Expression severity) {
        this.condition = condition;
        this.reportedExpression = reportedExpression;
        this.severity = severity;
    }

    /**
     * Creates an assertion statement with reported message and severity.
     * @param condition the assertion condition
     * @param reportedExpression the reported message
     * @param severity the severity
     */
    public AssertionStatement(Expression condition, String reportedExpression,
            Expression severity) {
        this(condition, new StringLiteral(reportedExpression), severity);
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
     * Returns the reported message.
     * @return the reported message
     */
    public Expression getReportedExpression() {
        return reportedExpression;
    }

    /**
     * Sets the reported message.
     * @param reportedExpression the reported message
     */
    public void setReportedExpression(Expression reportedExpression) {
        this.reportedExpression = reportedExpression;
    }

    /**
     * Sets the reported message.
     * @param reportedExpression the reported message
     */
    public void setReportedExpression(String reportedExpression) {
        this.reportedExpression = new StringLiteral(reportedExpression);
    }

    /**
     * Returns the severity.
     * @return the severity
     */
    public Expression getSeverity() {
        return severity;
    }

    /**
     * Sets the severity.
     * @param severity the severity
     */
    public void setSeverity(Expression severity) {
        this.severity = severity;
    }

    @Override
    void accept(SequentialStatementVisitor visitor) {
        visitor.visitAssertionStatement(this);
    }
}

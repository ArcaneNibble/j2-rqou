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
 * Report statement.
 *
 * @vmagic.example
 * ReportStatement statement = new ReportStatement("reported error",
 *  Standard.SEVERITY_LEVEL_ERROR);
 * ---
 * report "reported error" severity ERROR;
 */
public class ReportStatement extends SequentialStatement {

    private Expression reportExpression;
    private Expression severity;

    /**
     * Creates a report statement.
     * @param reportExpression the reported message
     */
    public ReportStatement(Expression reportExpression) {
        this.reportExpression = reportExpression;
    }

    /**
     * Creates a report statement.
     * @param reportExpression the reported message
     */
    public ReportStatement(String reportExpression) {
        this(new StringLiteral(reportExpression));
    }

    /**
     * Creates a report statement with severity.
     * @param reportExpression the reported message
     * @param severity the severity
     */
    public ReportStatement(Expression reportExpression, Expression severity) {
        this.reportExpression = reportExpression;
        this.severity = severity;
    }

    /**
     * Creates a report statement with severity.
     * @param reportExpression the reported message
     * @param severity the severity
     */
    public ReportStatement(String reportExpression, Expression severity) {
        this(new StringLiteral(reportExpression), severity);
    }

    /**
     * Returns the reported message.
     * @return the reported expression
     */
    public Expression getReportExpression() {
        return reportExpression;
    }

    /**
     * Sets the reported message.
     * @param reportExpression the reported expression
     */
    public void setReportExpression(Expression reportExpression) {
        this.reportExpression = reportExpression;
    }

    /**
     * Sets the reported message.
     * @param reportExpression the reported message
     */
    public void getReportExpression(String reportExpression) {
        this.reportExpression = new StringLiteral(reportExpression);
    }

    /**
     * Returns the severity.
     * @return the severity or <code>null</code> if no severity is set
     */
    public Expression getSeverity() {
        return severity;
    }

    /**
     * Sets the severity
     * @param severity the severity or <code>null</code> to remove severity
     */
    public void setSeverity(Expression severity) {
        this.severity = severity;
    }

    @Override
    void accept(SequentialStatementVisitor visitor) {
        visitor.visitReportStatement(this);
    }
}

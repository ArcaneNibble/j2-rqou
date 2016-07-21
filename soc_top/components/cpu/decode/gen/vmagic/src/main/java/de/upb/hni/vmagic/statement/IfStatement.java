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
import de.upb.hni.vmagic.util.ParentSetList;
import java.util.ArrayList;
import java.util.List;

/**
 * If statement.
 *
 * @vmagic.example
 * IfStatement statement = new IfStatement(Standard.BOOLEAN_FALSE);
 * statement.getStatements().add(new ReportStatement("if part"));
 * statement.createElsifPart(Standard.BOOLEAN_TRUE).
 *  getStatements().add(new ReportStatement("elsif part"));
 * statement.getElseStatements().add(new ReportStatement("else part"));
 * ---
 * if FALSE then
 *  report "if part";
 * elsif TRUE then
 *  report "elsif part";
 * else
 *  report "else part";
 * end if;
 */
public class IfStatement extends SequentialStatement {

    private Expression condition;
    private final List<SequentialStatement> statements =
            ParentSetList.createProxyList(this);
    private final List<ElsifPart> elsifParts = new ArrayList<ElsifPart>();
    private final List<SequentialStatement> elseStatements =
            ParentSetList.createProxyList(this);

    /**
     * Creates an if statement.
     * @param condition the if condition
     */
    public IfStatement(Expression condition) {
        this.condition = condition;
    }

    /**
     * Returns the if condition.
     * @return the condition
     */
    public Expression getCondition() {
        return condition;
    }

    /**
     * Returns the if condition.
     * @param condition the condition
     */
    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    /**
     * Returns the statement.
     * @return a modifiable list of sequential statements
     */
    public List<SequentialStatement> getStatements() {
        return statements;
    }

    /**
     * Returns the statement in the else part of the if statement.
     * If the list is empty no else part will be created.
     * @return a modifiable list of sequential statements
     */
    public List<SequentialStatement> getElseStatements() {
        return elseStatements;
    }

    /**
     * Creates a elsif part and adds it to this if statement.
     * @param condition the condition for the elsif part
     * @return the creates elsif part
     */
    public ElsifPart createElsifPart(Expression condition) {
        ElsifPart part = new ElsifPart(condition);
        elsifParts.add(part);
        return part;
    }

    /**
     * Returns the elsif parts.
     * @return a modifiable list of elsif parts
     */
    public List<ElsifPart> getElsifParts() {
        return elsifParts;
    }

    @Override
    void accept(SequentialStatementVisitor visitor) {
        visitor.visitIfStatement(this);
    }

    /**
     * Elsif part in an if statement.
     */
    public class ElsifPart {

        private Expression condition;
        private final List<SequentialStatement> statements =
                ParentSetList.createProxyList(IfStatement.this);

        private ElsifPart(Expression condition) {
            this.condition = condition;
        }

        /**
         * Returns the condition of this elsif part.
         * @return the condition
         */
        public Expression getCondition() {
            return condition;
        }

        /**
         * Sets the condition of this elsif part.
         * @param condition the condition
         */
        public void setCondition(Expression condition) {
            this.condition = condition;
        }

        /**
         * Returns the statement.
         * @return a modifiable list of sequential statements
         */
        public List<SequentialStatement> getStatements() {
            return statements;
        }
    }
}

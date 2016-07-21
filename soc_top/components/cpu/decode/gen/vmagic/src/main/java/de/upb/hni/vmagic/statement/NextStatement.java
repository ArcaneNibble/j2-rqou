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
 * Next statement.
 *
 * @vmagic.example
 * LoopStatement loop = new LoopStatement();
 * loop.setLabel("INF_LOOP");
 * loop.getStatements().add(new NextStatement(loop));
 * loop.getStatements().add(new ReportStatement("not reached"));
 * ---
 * INF_LOOP : loop
 *  next INF_LOOP;
 *  report "not reached";
 * end loop;
 */
public class NextStatement extends SequentialStatement {

    private LoopStatement loop;
    private Expression condition;

    /**
     * Creates a next statement.
     */
    public NextStatement() {
    }

    /**
     * Creates a next statement for the given loop.
     * @param loop the loop
     */
    public NextStatement(LoopStatement loop) {
        this.loop = loop;
    }

    /**
     * Creates a next statement with the given condition.
     * @param condition the condition
     */
    public NextStatement(Expression condition) {
        this.condition = condition;
    }

    /**
     * Creates a next statement for the given loop with a condition.
     * @param loop the loop
     * @param condition the condition
     */
    public NextStatement(LoopStatement loop, Expression condition) {
        this.loop = loop;
        this.condition = condition;
    }

    /**
     * Returns the condition for this next statement.
     * @return the condition
     */
    public Expression getCondition() {
        return condition;
    }

    /**
     * Sets the condition for this next statement.
     * @param condition the condition
     */
    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    /**
     * Returns the associated loop statement.
     * @return the loop statement or <code>null</code> if no loop is set
     */
    public LoopStatement getLoop() {
        return loop;
    }

    /**
     * Sets the associated loop statement.
     * @param loop the loop statement or <code>null</code> to remove loop
     */
    public void setLoop(LoopStatement loop) {
        this.loop = loop;
    }

    @Override
    void accept(SequentialStatementVisitor visitor) {
        visitor.visitNextStatement(this);
    }
}

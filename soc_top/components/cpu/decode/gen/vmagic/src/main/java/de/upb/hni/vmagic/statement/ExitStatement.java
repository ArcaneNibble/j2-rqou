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
 * Exit statement.
 *
 * @vmagic.example
 * ForStatement loop = new ForStatement("I",
 *  new Range(0, Range.Direction.TO, 127));
 * loop.setLabel("I_LOOP");
 *
 * loop.getStatements().add(new ExitStatement(loop,
 *  new Equals(loop.getParameter(), new DecimalLiteral(100))));
 * ---
 * I_LOOP : for I in 0 to 127 loop
 *  exit I_LOOP when I = 100;
 * end loop;
 */
public class ExitStatement extends SequentialStatement {

    private LoopStatement loop;
    private Expression condition;

    /**
     * Creates an exit statement,
     */
    public ExitStatement() {
    }

    /**
     * Creates an exit statement for the given loop.
     * @param loop the loop
     */
    public ExitStatement(LoopStatement loop) {
        this.loop = loop;
    }

    /**
     * Creates an exit statement with the given condition.
     * @param condition the condition
     */
    public ExitStatement(Expression condition) {
        this.condition = condition;
    }

    /**
     * Creates an exit statement for the given loop with a condition.
     * @param loop the loop
     * @param condition the condition
     */
    public ExitStatement(LoopStatement loop, Expression condition) {
        this.loop = loop;
        this.condition = condition;
    }

    /**
     * Returns the condition for this exit statement.
     * @return the condition
     */
    public Expression getCondition() {
        return condition;
    }

    /**
     * Sets the condition for this exit statement.
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
        visitor.visitExitStatement(this);
    }
}

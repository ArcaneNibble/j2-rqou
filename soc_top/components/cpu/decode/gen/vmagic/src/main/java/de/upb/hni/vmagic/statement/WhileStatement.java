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
 * While loop.
 *
 * @vmagic.example
 * Variable i = new Variable("i", Standard.INTEGER, new DecimalLiteral(0));
 * Expression condition = new LessThan(i, new DecimalLiteral(100));
 * WhileStatement loop = new WhileStatement(condition);
 * loop.getStatements().add(new ReportStatement("loop"));
 * Expression addExpression = new Add(i, new DecimalLiteral(1));
 * loop.getStatements().add(new VariableAssignment(i, addExpression));
 * ---
 * while i &lt; 100 loop
 *   report "loop";
 *  i := i + 1;
 * end loop;
 */
public class WhileStatement extends LoopStatement {

    private Expression condition;

    /**
     * Creates a while loop.
     * @param condition the while condition
     */
    public WhileStatement(Expression condition) {
        this.condition = condition;
    }

    /**
     * Returns the condition.
     * @return the condition
     */
    public Expression getCondition() {
        return condition;
    }

    /**
     * Sets the condition.
     * @param condition the condition
     */
    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    @Override
    void accept(SequentialStatementVisitor visitor) {
        visitor.visitWhileStatement(this);
    }
}

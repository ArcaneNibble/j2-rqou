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

import de.upb.hni.vmagic.object.Signal;
import de.upb.hni.vmagic.expression.Expression;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Wait statement.
 *
 * @vmagic.example
 * Signal clk = new Signal("CLK", StdLogic1164.STD_LOGIC);
 * WaitStatement statement = new WaitStatement(Expressions.risingEdge(clk));
 * ---
 * wait for CLK'EVENT and CLK = '1';
 */
public class WaitStatement extends SequentialStatement {

    private final List<Signal> sensitivityList = new ArrayList<Signal>();
    private Expression condition;
    private Expression timeout;

    /**
     * Creates a wait statement.
     */
    public WaitStatement() {
    }

    /**
     * Create a wait statement with a sensitivity list.
     * @param sensitivityList the sensitivity list
     */
    public WaitStatement(Signal... sensitivityList) {
        this.sensitivityList.addAll(Arrays.asList(sensitivityList));
    }

    /**
     * Create a wait statement with a sensitivity list.
     * @param sensitivityList the sensitivity list
     */
    public WaitStatement(List<Signal> sensitivityList) {
        this.sensitivityList.addAll(sensitivityList);
    }

    /**
     * Creates a wait statement with timeout expression.
     * @param timeout the timeout expression
     */
    public WaitStatement(Expression timeout) {
        this.timeout = timeout;
    }

    /**
     * Creates a wait statement with condition and timeout expression.
     * @param condition the condtion
     * @param timeout the timeout expression
     */
    public WaitStatement(Expression condition, Expression timeout) {
        this.condition = condition;
        this.timeout = timeout;
    }

    /**
     * Returns the sensitivity list.
     * @return a modifiable list of signals
     */
    public List<Signal> getSensitivityList() {
        return sensitivityList;
    }

    /**
     * Returns the condition.
     * @return the condition or <code>null</code> if no condtion is set
     */
    public Expression getCondition() {
        return condition;
    }

    /**
     * Sets the condition.
     * @param condition the condition or <code>null</code> to remove condition
     */
    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    /**
     * Returns the timeout expression.
     * @return the timeout expression or <code>null</code> if no timeout is set
     */
    public Expression getTimeout() {
        return timeout;
    }

    /**
     * Sets the timeout expression.
     * @param timeout the timeout expression or <code>null</code> to remove timeout
     */
    public void setTimeout(Expression timeout) {
        this.timeout = timeout;
    }

    @Override
    void accept(SequentialStatementVisitor visitor) {
        visitor.visitWaitStatement(this);
    }
}

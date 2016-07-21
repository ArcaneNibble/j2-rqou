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

package de.upb.hni.vmagic.highlevel;

import de.upb.hni.vmagic.expression.Aggregate;
import de.upb.hni.vmagic.builtin.StdLogic1164;
import de.upb.hni.vmagic.object.Signal;
import de.upb.hni.vmagic.expression.Equals;
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.statement.IfStatement;
import de.upb.hni.vmagic.statement.SequentialStatement;
import de.upb.hni.vmagic.statement.SignalAssignment;
import de.upb.hni.vmagic.type.IndexSubtypeIndication;
import java.util.Arrays;
import java.util.List;

/**
 * Register.
 */
public class Register extends AbstractRegister {

    private Signal input;
    private Signal output;
    private Signal clock;
    private Signal reset;
    private Signal writeEnable;
    private Expression resetExpression;
    private ResetLevel resetLevel = ResetLevel.HIGH;
    private ResetType resetType = ResetType.ASYNCHRONOUS;

    /**
     * Creates a register with input, output and clock.
     * @param input the input signal
     * @param output the output signal
     * @param clock the clock signal
     */
    public Register(Signal input, Signal output, Signal clock) {
        this.input = input;
        this.output = output;
        this.clock = clock;
    }

    /**
     * Creates a named register with input, output and clock.
     * @param identifier the identifier
     * @param input the input signal
     * @param output the output signal
     * @param clock the clock signal
     */
    public Register(String identifier, Signal input, Signal output, Signal clock) {
        super(identifier);
        this.input = input;
        this.output = output;
        this.clock = clock;
    }

    /**
     * Creates a register with input, output, clock and reset.
     * @param input the input signal
     * @param output the output signal
     * @param clock the clock signal
     * @param reset the reset signal
     */
    public Register(Signal input, Signal output, Signal clock, Signal reset) {
        this.input = input;
        this.output = output;
        this.clock = clock;
        this.reset = reset;
    }

    /**
     * Creates a named register with input, output, clock and reset.
     * @param identifier the identifier
     * @param input the input signal
     * @param output the output signal
     * @param clock the clock signal
     * @param reset the reset signal
     */
    public Register(String identifier, Signal input, Signal output, Signal clock, Signal reset) {
        super(identifier);
        this.input = input;
        this.output = output;
        this.clock = clock;
        this.reset = reset;
    }

    /**
     * Returns the clock signal.
     * @return the clock signal
     */
    public Signal getClock() {
        return clock;
    }

    /**
     * Sets the clock signal.
     * @param clock the clock signal
     */
    public void setClock(Signal clock) {
        this.clock = clock;
    }

    /**
     * Returns the input signal.
     * @return the input signal
     */
    public Signal getInput() {
        return input;
    }

    /**
     * Sets the input signal.
     * @param input the input signal
     */
    public void setInput(Signal input) {
        this.input = input;
    }

    /**
     * Returns the output signal.
     * @return the output signal
     */
    public Signal getOutput() {
        return output;
    }

    /**
     * Sets the output signal.
     * @param output the output signal
     */
    public void setOutput(Signal output) {
        this.output = output;
    }

    /**
     * Returns the write enable signal.
     * @return the write enabel signal
     */
    public Signal getWriteEnable() {
        return writeEnable;
    }

    /**
     * Sets the write enable signal.
     * @param writeEnable the write enable signal
     */
    public void setWriteEnable(Signal writeEnable) {
        this.writeEnable = writeEnable;
    }

    /**
     * Returns the reset signal.
     * @return the reset signal
     */
    public Signal getReset() {
        return reset;
    }

    /**
     * Sets the reset signal.
     * @param reset the reset signal
     */
    public void setReset(Signal reset) {
        this.reset = reset;
    }

    /**
     * Returns the reset expression.
     * @return the reset expression
     */
    public Expression getResetExpression() {
        return resetExpression;
    }

    /**
     * Sets the reset expression.
     * @param resetExpression the reset expression
     */
    public void setResetExpression(Expression resetExpression) {
        this.resetExpression = resetExpression;
    }

    /**
     * Returns the reset type.
     * @return the reset type
     */
    public ResetType getResetType() {
        return resetType;
    }

    /**
     * Sets the reset type.
     * @param resetType the reset type
     */
    public void setResetType(ResetType resetType) {
        this.resetType = resetType;
    }

    /**
     * Returns the reset level.
     * @return the reset level
     */
    public ResetLevel getResetLevel() {
        return resetLevel;
    }

    /**
     * Sets the reset level
     * @param resetLevel the reset level
     */
    public void setResetLevel(ResetLevel resetLevel) {
        this.resetLevel = resetLevel;
    }

    @Override
    public List<Signal> getSensitivityList() {
        if (resetExpression != null && resetType == ResetType.ASYNCHRONOUS) {
            //FIXME: Add signals in resetExpression to sensitivity list
            return Arrays.asList(clock); //, resetExpression);
        } else {
            return Arrays.asList(clock);
        }
    }

    @Override
    Register getFirstRegister() {
        return this;
    }

    @Override
    void addClockAssignments(List<SequentialStatement> statements) {
        SequentialStatement signalAssignment = new SignalAssignment(output, input);

        if (writeEnable != null) {
            Expression writeEnableCondition = new Equals(writeEnable, StdLogic1164.STD_LOGIC_1);
            IfStatement writeEnableIf = new IfStatement(writeEnableCondition);
            writeEnableIf.getStatements().add(signalAssignment);
            statements.add(writeEnableIf);
        } else {
            statements.add(signalAssignment);
        }
    }

    @Override
    void addResetAssignments(List<SequentialStatement> statements) {
        if (resetExpression == null) {
            //TODO: doesn't work for integer or enumeration types
            Expression tmpReset;
            if (output.getType() instanceof IndexSubtypeIndication) {
                tmpReset = Aggregate.OTHERS(StdLogic1164.STD_LOGIC_0);
            } else {
                tmpReset = StdLogic1164.STD_LOGIC_0;
            }
            statements.add(new SignalAssignment(output, tmpReset));
        } else {
            statements.add(new SignalAssignment(output, resetExpression));
        }
    }

    /**
     * Register reset type.
     */
    public static enum ResetType {

        /** Synchronous reset. */
        SYNCHRONOUS,
        /** Asynchronous reset. */
        ASYNCHRONOUS
    }

    /**
     * Register reset level.
     */
    public static enum ResetLevel {

        /** Low active reset. */
        LOW,
        /** High active reset. */
        HIGH
    }
}

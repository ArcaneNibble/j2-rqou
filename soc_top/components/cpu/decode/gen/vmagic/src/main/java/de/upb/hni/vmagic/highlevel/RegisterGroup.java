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

import de.upb.hni.vmagic.object.Signal;
import de.upb.hni.vmagic.statement.SequentialStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Group of registers.
 * A register group allows to use a single VDHL process for more than one registered signal.
 */
//TODO: check if reset types and clock signals are equal for all registers
public class RegisterGroup extends AbstractRegister {

    private final List<Register> registers;

    /**
     * Creates a register group.
     * @param registers a list of registers
     */
    public RegisterGroup(List<Register> registers) {
        this.registers = new ArrayList<Register>(registers);
    }

    /**
     * Creates a register group.
     * @param registers a list of registers
     */
    public RegisterGroup(Register... registers) {
        this(Arrays.asList(registers));
    }

    /**
     * Returns the registers in this group
     * @return a list of registers
     */
    public List<Register> getRegisters() {
        return registers;
    }

    //TODO: add signals in reset expression
    @Override
    public List<Signal> getSensitivityList() {
        Register reg = getFirstRegister();

        if (reg == null) {
            return Collections.emptyList();
        } else {
            return Arrays.asList(reg.getClock());
        }
    }

    @Override
    Register getFirstRegister() {
        if (registers.isEmpty()) {
            return null;
        } else {
            return registers.get(0);
        }
    }

    @Override
    void addClockAssignments(List<SequentialStatement> statements) {
        for (Register register : registers) {
            register.addClockAssignments(statements);
        }
    }

    @Override
    void addResetAssignments(List<SequentialStatement> statements) {
        for (Register register : registers) {
            register.addResetAssignments(statements);
        }
    }
}

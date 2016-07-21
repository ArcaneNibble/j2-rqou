/*
 * Copyright 2009, 2010, 2011 University of Paderborn
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
import de.upb.hni.vmagic.object.VariableAssignmentTarget;

/**
 * Variable assignment.
 *
 * @vmagic.example
 * Variable target = new Variable("TARGET", Standard.BIT);
 * VariableAssignment assignment = new VariableAssignment(target, Standard.BIT_1);
 * ---
 * TARGET := '1';
 */
public class VariableAssignment extends SequentialStatement {

    private VariableAssignmentTarget target;
    private Expression value;

    /**
     * Creates a variable assignment.
     * @param target the variable assignment target
     * @param value the assigned value
     */
    public VariableAssignment(VariableAssignmentTarget target, Expression value) {
        this.target = target;
        this.value = value;
    }

    /**
     * Returns the variable assignement target.
     * @return the target
     */
    public VariableAssignmentTarget getTarget() {
        return target;
    }

    /**
     * Sets the variable assignment target.
     * @param target the target
     */
    public void setTarget(VariableAssignmentTarget target) {
        this.target = target;
    }

    /**
     * Returns the assigned value.
     * @return the value
     */
    public Expression getValue() {
        return value;
    }

    /**
     * Sets the assigned value.
     * @param value the value
     */
    public void setValue(Expression value) {
        this.value = value;
    }

    @Override
    void accept(SequentialStatementVisitor visitor) {
        visitor.visitVariableAssignment(this);
    }
}

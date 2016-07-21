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

package de.upb.hni.vmagic.object;

import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.type.SubtypeIndication;
import java.util.Collections;
import java.util.List;

/**
 * Variable.
 */
public class Variable extends DefaultVhdlObject<Variable> implements VariableAssignmentTarget {

    private Expression defaultValue;
    private boolean shared;

    /**
     * Creates a variable.
     * @param identifier the identifier
     * @param type the type
     */
    public Variable(String identifier, SubtypeIndication type) {
        super(identifier, type);
        setMode(Mode.IN);
    }

    /**
     * Creates a variable with a default value.
     * @param identifier the identifier
     * @param type the type
     * @param defaultValue the default value
     */
    public Variable(String identifier, SubtypeIndication type, Expression defaultValue) {
        this(identifier, type);
        this.defaultValue = defaultValue;
    }

    /**
     * Returns the default value.
     * @return the default value
     */
    public Expression getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the default value.
     * @param defaultValue the default value
     */
    public void setDefaultValue(Expression defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Returns if this variable is shared.
     * @return true if the variable is shared
     */
    public boolean isShared() {
        return shared;
    }

    /**
     * Sets if this variable is shared.
     * @param shared true if the variabel should be shared
     */
    public void setShared(boolean shared) {
        this.shared = shared;
    }

    @Override
    public void setMode(Mode mode) {
        if (mode == Mode.BUFFER || mode == Mode.LINKAGE) {
            throw new IllegalArgumentException("Mode " + mode + " is not allowed for a variable");
        }

        super.setMode(mode);
    }

    @Override
    public List<Variable> getVhdlObjects() {
        return Collections.singletonList(this);
    }

    @Override
    public ObjectClass getObjectClass() {
        return ObjectClass.VARIABLE;
    }
}

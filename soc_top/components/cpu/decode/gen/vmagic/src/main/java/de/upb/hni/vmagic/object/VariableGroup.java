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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Variable group.
 */
public class VariableGroup extends VhdlObjectGroup<Variable> {

    private final List<Variable> variables;

    /**
     * Creates a group of variables.
     * @param variables a list of variables
     */
    public VariableGroup(List<Variable> variables) {
        this.variables = new ArrayList<Variable>(variables);
    }

    /**
     * Creates a group of variables.
     * @param variables a variable number of variables
     */
    public VariableGroup(Variable... variables) {
        this(Arrays.asList(variables));
    }

    /**
     * Returns the variables in this group.
     * @return a modifiable list of variables
     */
    @Override
    public List<Variable> getElements() {
        return variables;
    }

    @Override
    public List<Variable> getVhdlObjects() {
        return Collections.unmodifiableList(variables);
    }
}

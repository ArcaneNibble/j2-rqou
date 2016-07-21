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

package de.upb.hni.vmagic.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Group of constants.
 */
//TODO: check for equal types
public class ConstantGroup extends VhdlObjectGroup<Constant> {

    private final List<Constant> constants;

    /**
     * Creates a group of constants.
     * @param constants a list of constants
     */
    public ConstantGroup(List<Constant> constants) {
        this.constants = new ArrayList<Constant>(constants);
    }

    /**
     * Creates a group of constants.
     * @param constants  a variable number of constants
     */
    public ConstantGroup(Constant... constants) {
        this(Arrays.asList(constants));
    }

    /**
     * Returns the constants in this group.
     * @return a modifiable list of constants
     */
    public List<Constant> getElements() {
        return constants;
    }

    public List<Constant> getVhdlObjects() {
        return Collections.unmodifiableList(constants);
    }
}

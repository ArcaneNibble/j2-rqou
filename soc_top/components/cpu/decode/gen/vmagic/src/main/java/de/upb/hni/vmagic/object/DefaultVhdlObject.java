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

import de.upb.hni.vmagic.type.SubtypeIndication;

/**
 * Default VHDL object.
 */
abstract class DefaultVhdlObject<T extends VhdlObject> extends VhdlObject<T> {

    private String identifier;
    private SubtypeIndication type;
    private Mode mode;

    public DefaultVhdlObject(String identifier, SubtypeIndication type) {
        this.identifier = identifier;
        this.type = type;
        this.mode = Mode.NONE;
    }

    /**
     * Returns the identifier of this object.
     * @return the identifier
     */
    @Override
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier of this object.
     * @param identifier the identifier
     */
    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the type of this object.
     * @return the type
     */
    @Override
    public SubtypeIndication getType() {
        return type;
    }

    /**
     * Sets the type of this object.
     * @param type the type
     */
    @Override
    public void setType(SubtypeIndication type) {
        this.type = type;
    }

    /**
     * Returns the mode of this vhdl object.
     * @return the mode
     */
    @Override
    public Mode getMode() {
        return mode;
    }

    /**
     * Sets the mode of this vhdl object.
     * @param mode the mode
     */
    @Override
    public void setMode(Mode mode) {
        if (mode == Mode.NONE) {
            throw new IllegalArgumentException("Setting the mode to NONE is not allowed");
        }
        this.mode = mode;
    }
}

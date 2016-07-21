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

import de.upb.hni.vmagic.NamedEntity;
import de.upb.hni.vmagic.expression.Name;
import de.upb.hni.vmagic.output.OutputEnum;
import de.upb.hni.vmagic.type.SubtypeIndication;

/**
 * VHDL object.
 */
public abstract class VhdlObject<T extends VhdlObject> extends Name<T>
        implements VhdlObjectProvider<T>, NamedEntity {

    /**
     * Returns the identifier of this object.
     * @return the identifier
     */
    @Override
    public abstract String getIdentifier();

    /**
     * Sets the identifier of this object.
     * @param identifier the identifier
     */
    public abstract void setIdentifier(String identifier);

    /**
     * Sets the type of this object.
     * @param type the type
     */
    public abstract void setType(SubtypeIndication type);

    /**
     * Returns the mode of this vhdl object.
     * @return the mode
     */
    public abstract Mode getMode();

    /**
     * Sets the mode of this vhdl object.
     * @param mode the mode
     */
    public abstract void setMode(Mode mode);

    /**
     * Returns the type of this VhdlObject.
     * @return the object class
     */
    public abstract ObjectClass getObjectClass();

    /**
     * Object class describes the type of VhdlObject.
     */
    public static enum ObjectClass implements OutputEnum {

        /** Constant. */
        CONSTANT("constant"),
        /** File. */
        FILE("file"),
        /** Signal. */
        SIGNAL("signal"),
        /** Variable. */
        VARIABLE("variable");
        private final String lower;
        private final String upper;

        ObjectClass(String text) {
            lower = text;
            upper = text.toUpperCase();
        }

        @Override
        public String getLowerCase() {
            return lower;
        }

        @Override
        public String getUpperCase() {
            return upper;
        }
    }

    /**
     * Vhdl object mode.
     */
    public static enum Mode implements OutputEnum {

        /** None. */
        NONE,
        /** In. */
        IN,
        /** Out. */
        OUT,
        /** InOut. */
        INOUT,
        /** Buffer. */
        BUFFER,
        /** Linkage. */
        LINKAGE;

        @Override
        public String getLowerCase() {
            if (this == NONE) {
                return "";
            }
            return this.toString().toLowerCase();
        }

        @Override
        public String getUpperCase() {
            if (this == NONE) {
                return "";
            }
            return this.toString().toUpperCase();
        }
    }
}

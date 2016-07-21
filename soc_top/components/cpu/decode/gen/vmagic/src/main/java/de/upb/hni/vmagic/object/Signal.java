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
import de.upb.hni.vmagic.output.OutputEnum;
import java.util.Collections;
import java.util.List;

/**
 * Signal.
 */
public class Signal extends DefaultVhdlObject<Signal> implements SignalAssignmentTarget {

    private Kind kind;
    private Expression defaultValue;

    /**
     * Creates a signal.
     * @param identifier the identifier of the signal
     * @param type the type of the signal
     */
    public Signal(String identifier, SubtypeIndication type) {
        super(identifier, type);
        setMode(Mode.IN);
        kind = Kind.DEFAULT;
    }

    /**
     * Creates a signal with a mode.
     * @param identifier the identifier of the signal
     * @param mode the mode of the signal
     * @param type the type of the signal
     */
    public Signal(String identifier, Mode mode, SubtypeIndication type) {
        super(identifier, type);
        setMode(mode);
        kind = Kind.DEFAULT;
    }

    /**
     * Creates a signal with a default value.
     * @param identifier the identifier of the signal
     * @param type the type of the signal
     * @param defaultValue the default value of the signal
     */
    public Signal(String identifier, SubtypeIndication type, Expression defaultValue) {
        super(identifier, type);
        setMode(Mode.IN);
        this.defaultValue = defaultValue;
        kind = Kind.DEFAULT;
    }

    /**
     * Creates a signal with a mode and a default value.
     * @param identifier the identifier of the signal
     * @param mode the mode of the signal
     * @param type the type of the signal
     * @param defaultValue the default value of the signal
     */
    public Signal(String identifier, Mode mode, SubtypeIndication type, Expression defaultValue) {
        super(identifier, type);
        this.defaultValue = defaultValue;
        setMode(mode);
        kind = Kind.DEFAULT;
    }

    /**
     * Returns the kind of this signal.
     * @return the signal kind
     */
    public Kind getKind() {
        return kind;
    }

    /**
     * Sets the kind of this signal.
     * @param kind the signal kind
     */
    public void setKind(Kind kind) {
        this.kind = kind;
    }

    /**
     * Returns the default value of this signal.
     * @return the default value
     */
    public Expression getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the default value of this signal.
     * @param defaultValue the default value
     */
    public void setDefaultValue(Expression defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public List<Signal> getVhdlObjects() {
        return Collections.singletonList(this);
    }

    @Override
    public ObjectClass getObjectClass() {
        return ObjectClass.SIGNAL;
    }

    /**
     * Signal kind.
     */
    public static enum Kind implements OutputEnum {

        /** Default signal kind. */
        DEFAULT(""),
        /** Register signal kind. */
        REGISTER("register"),
        /** Bus signal kind. */
        BUS("bus");
        private final String lower;
        private final String upper;

        Kind(String text) {
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
}

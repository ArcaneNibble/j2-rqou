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
 * Constant.
 */
public class Constant extends DefaultVhdlObject<Constant> {

    private Expression defaultValue;

    /**
     * Creates a constant.
     * @param identifier the identifier
     * @param type the type
     */
    public Constant(String identifier, SubtypeIndication type) {
        super(identifier, type);
        setMode(Mode.IN);
    }

    /**
     * Creates a constant with a default value.
     * @param identifier the identifier
     * @param type the type
     * @param defaultValue the default value
     */
    public Constant(String identifier, SubtypeIndication type, Expression defaultValue) {
        super(identifier, type);
        this.defaultValue = defaultValue;
        setMode(Mode.IN);
    }

    /**
     * Returns the default value of this constant.
     * @return the default value
     */
    public Expression getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the default value of this constant.
     * @param defaultValue the default value
     */
    public void setDefaultValue(Expression defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public void setMode(Mode mode) {
        if (mode != Mode.IN) {
            throw new IllegalArgumentException("Mode " + mode + " is not allowed for a constant");
        }
        super.setMode(mode);
    }

    @Override
    public List<Constant> getVhdlObjects() {
        return Collections.singletonList(this);
    }

    @Override
    public ObjectClass getObjectClass() {
        return ObjectClass.CONSTANT;
    }
}

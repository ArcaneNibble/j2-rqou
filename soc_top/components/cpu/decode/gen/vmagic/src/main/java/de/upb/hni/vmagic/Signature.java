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

package de.upb.hni.vmagic;

import de.upb.hni.vmagic.type.SubtypeIndication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Signature.
 */
public class Signature extends VhdlElement {

    //TODO: use type_mark
    private final List<SubtypeIndication> parameterTypes;
    private SubtypeIndication returnType;

    /**
     * Creates an empty signature.
     */
    public Signature() {
        this.returnType = null;
        this.parameterTypes = new ArrayList<SubtypeIndication>();
    }

    /**
     * Creates a signature with a return type and a variable number of parameter types.
     * @param returnType the return type
     * @param parameterTypes the parameter types
     */
    public Signature(SubtypeIndication returnType, SubtypeIndication... parameterTypes) {
        this(returnType, Arrays.asList(parameterTypes));
    }

    /**
     * Creates a signature with a return type and a list of parameter types.
     * @param returnType the return type
     * @param parameterTypes the list of parameter types
     */
    public Signature(SubtypeIndication returnType, List<SubtypeIndication> parameterTypes) {
        this.returnType = returnType;
        this.parameterTypes = new ArrayList<SubtypeIndication>(parameterTypes);
    }

    /**
     * Creates a signature with a return type.
     * @param returnType the return type.
     */
    public Signature(SubtypeIndication returnType) {
        this.returnType = returnType;
        this.parameterTypes = new ArrayList<SubtypeIndication>();
    }

    /**
     * Creates a signature with a list of parameter types.
     * @param parameterTypes the list of parameter types
     */
    public Signature(List<SubtypeIndication> parameterTypes) {
        this.returnType = null;
        this.parameterTypes = new ArrayList<SubtypeIndication>(parameterTypes);
    }

    /**
     * Returns the return type of this signature.
     * @return the return type or <code>null</code> if no return type is set
     */
    public SubtypeIndication getReturnType() {
        return returnType;
    }

    /**
     * Sets the return type of this signature.
     * @param returnType the return type or <code>null</code> to remove the return type
     */
    public void setReturnType(SubtypeIndication returnType) {
        this.returnType = returnType;
    }

    /**
     * Returns a list of parameter types.
     * @return a modifiable list of parameter types
     */
    public List<SubtypeIndication> getParameterTypes() {
        return parameterTypes;
    }
}

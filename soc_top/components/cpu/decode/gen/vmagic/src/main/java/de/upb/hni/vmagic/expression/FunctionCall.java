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

package de.upb.hni.vmagic.expression;

import de.upb.hni.vmagic.AssociationElement;
import de.upb.hni.vmagic.declaration.Function;
import de.upb.hni.vmagic.type.SubtypeIndication;
import java.util.ArrayList;
import java.util.List;

/**
 * Function call.
 */
public class FunctionCall extends Name<FunctionCall> {

    private Function function;
    private final List<AssociationElement> parameters = new ArrayList<AssociationElement>();

    /**
     * Creates a function call.
     * @param function the called function
     */
    public FunctionCall(Function function) {
        this.function = function;
    }

    /**
     * Returns the called function.
     * @return the calles function
     */
    public Function getFunction() {
        return function;
    }

    /**
     * Sets the called function.
     * @param function the function
     */
    public void setFunction(Function function) {
        this.function = function;
    }

    /**
     * Returns the function call parameters.
     * @return a modifiable list of association elements
     */
    public List<AssociationElement> getParameters() {
        return parameters;
    }

    @Override
    public SubtypeIndication getType() {
        return function.getReturnType();
    }

    @Override
    void accept(ExpressionVisitor visitor) {
        visitor.visitFunctionCall(this);
    }

    @Override
    public FunctionCall copy() {
        FunctionCall call = new FunctionCall(function);
        for (AssociationElement parameter : parameters) {
            call.getParameters().add(new AssociationElement(parameter.getFormal(),
                    parameter.getActual().copy()));
        }

        return call;
    }
}

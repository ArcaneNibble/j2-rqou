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

import de.upb.hni.vmagic.declaration.Attribute;
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.expression.Name;
import de.upb.hni.vmagic.type.SubtypeIndication;

/**
 * Attribute expression.
 * @param <T> the object type
 */
public class AttributeExpression<T extends Name> extends Name<T> {

    private final T prefix;
    private final Attribute attribute;
    private final Expression parameter;

    /**
     * Creates an attribute expression.
     * @param prefix the prefix of this attribute expression
     * @param attribute the attribute
     */
    public AttributeExpression(T prefix, Attribute attribute) {
        this.prefix = prefix;
        this.attribute = attribute;
        this.parameter = null;
    }

    /**
     * Creates an attribute expression with a parameter.
     * @param prefix the prefix of this attribute expression
     * @param attribute the attribute
     * @param parameter the parameter
     */
    public AttributeExpression(T prefix, Attribute attribute, Expression parameter) {
        this.prefix = prefix;
        this.attribute = attribute;
        this.parameter = parameter;
    }

    /**
     * Returns the prefix of this attribute expression.
     * @return the prefix
     */
    public T getPrefix() {
        return prefix;
    }

    /**
     * Returns the attribute.
     * @return the attribute
     */
    public Attribute getAttribute() {
        return attribute;
    }

    /**
     * Returns the parameter.
     * @return the parameter
     */
    public Expression getParameter() {
        return parameter;
    }

    @Override
    public SubtypeIndication getType() {
        //TODO: implement corrently
        return prefix.getType();
    }
}

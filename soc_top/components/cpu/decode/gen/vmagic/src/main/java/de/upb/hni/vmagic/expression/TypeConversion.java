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

package de.upb.hni.vmagic.expression;

import de.upb.hni.vmagic.type.SubtypeIndication;

/**
 * Type conversion expression.
 */
public class TypeConversion extends Primary<TypeConversion> {

    //TODO: replace SubtypeIndication by type mark
    private SubtypeIndication type;
    private Expression expression;

    /**
     * Creates a type conversion.
     * @param type the type
     * @param expression the converted expression
     */
    public TypeConversion(SubtypeIndication type, Expression expression) {
        this.type = type;
        this.expression = expression;
    }

    /**
     * Returns the converted expression.
     * @return the expression
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Sets the converted expression.
     * @param epxression the expression
     */
    public void setExpression(Expression epxression) {
        this.expression = epxression;
    }

    /**
     * Returns the type.
     * @return the type
     */
    public SubtypeIndication getType() {
        return type;
    }

    /**
     * Sets the type
     * @param type the type
     */
    public void setType(SubtypeIndication type) {
        this.type = type;
    }

    @Override
    public TypeConversion copy() {
        //TODO: copy subtype indication
        return new TypeConversion(type, expression.copy());
    }

    @Override
    void accept(ExpressionVisitor visitor) {
        visitor.visitTypeConversion(this);
    }
}

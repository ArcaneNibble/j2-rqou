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
 * Qualified expression.
 */
public class QualifiedExpression extends Primary<QualifiedExpression> {

    //TODO: use TypeMark instead of SubtypeIndication
    private SubtypeIndication type;
    private Aggregate operand;

    /**
     * Creates a qualified expression.
     * @param type the type
     * @param operand the operand
     */
    public QualifiedExpression(SubtypeIndication type, Aggregate operand) {
        this.type = type;
        this.operand = operand;
    }

    /**
     * Creates a qualified expression.
     * @param type the type
     * @param operand the operand
     */
    public QualifiedExpression(SubtypeIndication type, Expression operand) {
        this(type, new Aggregate(operand));
    }

    /**
     * Returns the operand.
     * @return the operand
     */
    public Aggregate getOperand() {
        return operand;
    }

    /**
     * Sets the operand.
     * @param operand the operand
     */
    public void setOperand(Aggregate operand) {
        this.operand = operand;
    }

    /**
     * Returns the type.
     * @return the type
     */
    public SubtypeIndication getType() {
        return type;
    }

    /**
     * Sets the type.
     * @param type the type
     */
    public void setType(SubtypeIndication type) {
        this.type = type;
    }

    @Override
    public QualifiedExpression copy() {
        return new QualifiedExpression(type, operand.copy());
    }

    @Override
    void accept(ExpressionVisitor visitor) {
        visitor.visitQualifiedExpression(this);
    }
}

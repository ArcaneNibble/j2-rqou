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

import de.upb.hni.vmagic.DiscreteRange;
import de.upb.hni.vmagic.declaration.Attribute;
import de.upb.hni.vmagic.object.ArrayElement;
import de.upb.hni.vmagic.object.AttributeExpression;
import de.upb.hni.vmagic.object.RecordElement;
import de.upb.hni.vmagic.object.Slice;
import de.upb.hni.vmagic.type.SubtypeIndication;
import java.util.List;

/**
 * A name of a VHDL element or function call.
 * @param <T> the type of the object or function call
 */
public abstract class Name<T extends Name> extends Primary<Name> {

    /**
     * Returns the type of this object.
     * @return the type
     */
    @Override
    public abstract SubtypeIndication getType();

    /**
     * Returns a slice of this object.
     * @param range the slice range.
     * @return the slice
     */
    public Slice<T> getSlice(DiscreteRange range) {
        //safe if T extends VhdlObject<T>
        @SuppressWarnings("unchecked")
        T base = (T) this;
        return new Slice<T>(base, range);
    }

    /**
     * Returns an array element of this object.
     * @param index the index of the array element
     * @return the array element
     */
    public ArrayElement<T> getArrayElement(Expression index) {
        //safe if T extends VhdlObject<T>
        @SuppressWarnings("unchecked")
        T base = (T) this;
        return new ArrayElement<T>(base, index);
    }

    /**
     * Returns an array element of this object.
     * @param index the index of the array element
     * @return the array element
     */
    public ArrayElement<T> getArrayElement(int index) {
        //safe if T extends VhdlObject<T>
        @SuppressWarnings("unchecked")
        T base = (T) this;
        return new ArrayElement<T>(base, index);
    }

    /**
     * Returns an array element of this object.
     * @param indices the indices of the array element
     * @return the array element
     */
    public ArrayElement<T> getArrayElement(List<Expression> indices) {
        //safe if T extends VhdlObject<T>
        @SuppressWarnings("unchecked")
        T base = (T) this;
        return new ArrayElement<T>(base, indices);
    }

    /**
     * Returns an array element of this object.
     * @param indices the indices of the array element
     * @return the array element
     */
    public ArrayElement<T> getArrayElement(Expression... indices) {
        //safe if T extends VhdlObject<T>
        @SuppressWarnings("unchecked")
        T base = (T) this;
        return new ArrayElement<T>(base, indices);
    }

    /**
     * Returns a record element of this object.
     * @param element the identifier of the record element
     * @return the record element
     */
    public RecordElement<T> getRecordElement(String element) {
        //safe if T extends VhdlObject<T>
        @SuppressWarnings("unchecked")
        T base = (T) this;
        return new RecordElement<T>(base, element);
    }

    /**
     * Returns a attribute expression of this object.
     * @param attribute the attribute
     * @return the record element
     */
    public AttributeExpression<T> getAttributeExpression(Attribute attribute) {
        //safe if T extends VhdlObject<T>
        @SuppressWarnings("unchecked")
        T base = (T) this;
        return new AttributeExpression<T>(base, attribute);
    }

    /**
     * Returns a attribute expression of this object.
     * @param attribute the attribute
     * @param parameter the parameter
     * @return the record element
     */
    public AttributeExpression<T> getAttributeExpression(Attribute attribute, Expression parameter) {
        //safe if T extends VhdlObject<T>
        @SuppressWarnings("unchecked")
        T base = (T) this;
        return new AttributeExpression<T>(base, attribute, parameter);
    }

    @Override
    void accept(ExpressionVisitor visitor) {
        visitor.visitName(this);
    }

    @Override
    public Name copy() {
        return this;
    }
}

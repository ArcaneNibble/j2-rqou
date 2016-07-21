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
import de.upb.hni.vmagic.expression.Name;
import de.upb.hni.vmagic.literal.DecimalLiteral;
import de.upb.hni.vmagic.type.SubtypeIndication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Array element of a VhdlObject.
 * @param <T> the object type
 */
//TODO: check if array element is a valid signal assignment or variable assignment target
public class ArrayElement<T extends Name> extends Name<T>
        implements SignalAssignmentTarget, VariableAssignmentTarget {

    private final T prefix;
    private final List<Expression> indices;

    /**
     * Creates an array element.
     * @param prefix the prefix of this array element
     * @param index the array index
     */
    public ArrayElement(T prefix, Expression index) {
        this.prefix = prefix;
        this.indices = Collections.singletonList(index);
    }

    /**
     * Creates an array element with an integer index.
     * @param prefix the prefix of this array element
     * @param index the array index
     */
    public ArrayElement(T prefix, int index) {
        this(prefix, new DecimalLiteral(index));
    }

    /**
     * Creates an array element.
     * @param prefix the prefix of this array element
     * @param indices the array indices
     */
    public ArrayElement(T prefix, List<Expression> indices) {
        this.prefix = prefix;
        this.indices = new ArrayList<Expression>(indices);
    }

    /**
     * Creates an array element.
     * @param prefix the prefix of this array element
     * @param indices the array indices
     */
    public ArrayElement(T prefix, Expression... indices) {
        this(prefix, Arrays.asList(indices));
    }

    /**
     * Returns the index.
     * @return the index
     */
    public List<Expression> getIndices() {
        return Collections.unmodifiableList(indices);
    }

    /**
     * Returns the prefix of this array element.
     * @return the prefix
     */
    public T getPrefix() {
        return prefix;
    }

    @Override
    public SubtypeIndication getType() {
        //TODO: implement corrently
        return prefix.getType();
    }
}

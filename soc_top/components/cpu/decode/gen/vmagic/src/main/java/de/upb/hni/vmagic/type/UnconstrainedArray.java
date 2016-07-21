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

package de.upb.hni.vmagic.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unconstrained array.
 */
public class UnconstrainedArray extends ArrayType {

    private final List<SubtypeIndication> indexSubtypes;

    /**
     * Creates an unconstrained array.
     * @param identifier the identifier
     * @param elementType the element type
     * @param indexSubtypes the index subtypes
     */
    public UnconstrainedArray(String identifier, SubtypeIndication elementType,
            SubtypeIndication... indexSubtypes) {
        this(identifier, elementType, Arrays.asList(indexSubtypes));
    }

    /**
     * Creates an unconstrained array.
     * @param identifier the identifier
     * @param elementType the element type
     * @param indexSubtypes the index subtypes
     */
    public UnconstrainedArray(String identifier, SubtypeIndication elementType,
            List<SubtypeIndication> indexSubtypes) {
        super(identifier, elementType);
        this.indexSubtypes = new ArrayList<SubtypeIndication>(indexSubtypes);
    }

    /**
     * Returns the index subtypes.
     * @return a modifiable list of subtype indications
     */
    public List<SubtypeIndication> getIndexSubtypes() {
        return indexSubtypes;
    }

    @Override
    void accept(TypeVisitor visitor) {
        visitor.visitUnconstrainedArray(this);
    }
}

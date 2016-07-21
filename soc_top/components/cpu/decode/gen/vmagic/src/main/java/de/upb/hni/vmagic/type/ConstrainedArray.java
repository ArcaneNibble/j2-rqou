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

import de.upb.hni.vmagic.DiscreteRange;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Constrained array.
 */
public class ConstrainedArray extends ArrayType {

    private final List<DiscreteRange> indexRanges;

    /**
     * Creates a constrained array.
     * @param identifier the identifier
     * @param elementType the type of the array elements
     * @param indexRanges the index ranges
     */
    public ConstrainedArray(String identifier, SubtypeIndication elementType, DiscreteRange... indexRanges) {
        this(identifier, elementType, Arrays.asList(indexRanges));
    }

    /**
     * Creates a constrained array.
     * @param identifier the identifier
     * @param elementType the type of the array elements
     * @param indexRanges the index ranges
     */
    public ConstrainedArray(String identifier, SubtypeIndication elementType, List<DiscreteRange> indexRanges) {
        super(identifier, elementType);
        this.indexRanges = new ArrayList<DiscreteRange>(indexRanges);
    }

    /**
     * Returns the index ranges.
     * @return a modifiable list of index ranges
     */
    public List<DiscreteRange> getIndexRanges() {
        return indexRanges;
    }

    @Override
    void accept(TypeVisitor visitor) {
        visitor.visitConstrainedArray(this);
    }
}

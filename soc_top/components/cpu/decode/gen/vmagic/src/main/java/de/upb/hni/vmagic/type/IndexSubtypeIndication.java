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
 * Index constraint subtype indication.
 */
public class IndexSubtypeIndication implements SubtypeIndication {

    private SubtypeIndication baseType;
    private final List<DiscreteRange> ranges;

    /**
     * Creates a index subtype indication.
     * @param baseType the base type
     * @param ranges the ranges
     */
    public IndexSubtypeIndication(SubtypeIndication baseType, List<DiscreteRange> ranges) {
        this.baseType = baseType;
        this.ranges = new ArrayList<DiscreteRange>(ranges);
    }

    /**
     * Creates a index subtype indication.
     * @param baseType the base type
     * @param ranges the ranges
     */
    public IndexSubtypeIndication(SubtypeIndication baseType, DiscreteRange... ranges) {
        this(baseType, Arrays.asList(ranges));
    }

    /**
     * Returns the base type.
     * @return the base type
     */
    public SubtypeIndication getBaseType() {
        return baseType;
    }

    /**
     * Sets the base type.
     * @param baseType the base type
     */
    public void setBaseType(SubtypeIndication baseType) {
        this.baseType = baseType;
    }

    /**
     * Returns the index ranges.
     * @return a modifiable list of ranges
     */
    public List<DiscreteRange> getRanges() {
        return ranges;
    }
}

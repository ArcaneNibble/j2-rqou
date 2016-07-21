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

/**
 * Wrapper to use a subtype indication as discrete range.
 */
public class SubtypeDiscreteRange extends DiscreteRange<SubtypeDiscreteRange> {

    private SubtypeIndication subtypeIndication;

    /**
     * Creates a discrete range subtype indication wrapper.
     * @param subtypeIndication the wrapped subtype indication
     */
    public SubtypeDiscreteRange(SubtypeIndication subtypeIndication) {
        this.subtypeIndication = subtypeIndication;
    }

    /**
     * Returns the wrapped subtype indication.
     * @return the subtype indication
     */
    public SubtypeIndication getSubtypeIndication() {
        return subtypeIndication;
    }

    /**
     * Sets the wrapped subtype indication.
     * @param subtypeIndication the subtype indication
     */
    public void setSubtypeIndication(SubtypeIndication subtypeIndication) {
        this.subtypeIndication = subtypeIndication;
    }

    @Override
    public SubtypeDiscreteRange copy() {
        return new SubtypeDiscreteRange(subtypeIndication);
    }
}

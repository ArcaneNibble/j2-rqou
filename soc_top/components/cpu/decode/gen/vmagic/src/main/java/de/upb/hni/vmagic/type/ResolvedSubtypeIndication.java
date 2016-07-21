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

/**
 * Subtype indication with a resolution function.
 */
public class ResolvedSubtypeIndication implements SubtypeIndication {

    //TODO: don't use string for resolution function
    private String resolutionFunction;
    private SubtypeIndication baseType;

    /**
     * Creates a resolved subtype indication.
     * @param resolutionFunction the resolution function
     * @param baseType the base type
     */
    public ResolvedSubtypeIndication(String resolutionFunction, SubtypeIndication baseType) {
        this.resolutionFunction = resolutionFunction;
        this.baseType = baseType;
    }

    /**
     * Returns the resolution function.
     * @return the function
     */
    public String getResolutionFunction() {
        return resolutionFunction;
    }

    /**
     * Sets the resolution function.
     * @param resolutionFunction the function
     */
    public void setResolutionFunction(String resolutionFunction) {
        this.resolutionFunction = resolutionFunction;
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
}

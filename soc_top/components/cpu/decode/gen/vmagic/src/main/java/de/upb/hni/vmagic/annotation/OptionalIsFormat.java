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

package de.upb.hni.vmagic.annotation;

/**
 * Optional <code>is</code> keyword format annotation.
 * This class is used to change the output of VHDL constructs with an optional
 * <code>is</code> keyword.
 */
public class OptionalIsFormat {

    private boolean useIs;

    /**
     * Creates a optional <code>is</code> format annotation.
     * @param useIs <code>true</code>,
     *        if the optional <code>is</code> should be added to the output
     */
    public OptionalIsFormat(boolean useIs) {
        this.useIs = useIs;
    }

    /**
     * Returns if the optional <code>is</code> keyword is added to the output.
     * @return <code>true</code>, if the optional <code>is</code> is added
     */
    public boolean isUseIs() {
        return useIs;
    }

    /**
     * Sets if the optional <code>is</code> keyword should be added to the output
     * @param useIs <code>true</code>, if the optional <code>is</code> should be added
     */
    public void setUseIs(boolean useIs) {
        this.useIs = useIs;
    }
}

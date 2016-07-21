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
 * Component instantiation format annotation.
 * The component instantiation format annotation is used to modify the VHDL output
 * of component instantiations.
 */
public class ComponentInstantiationFormat {

    private boolean useOptionalComponentKeyword;

    /**
     * Creates a component instantiation format annotation.
     * @param useOptionalComponentKeyword <code>true</code>,
     *        if the <code>component</code> keyword should be included in the output
     */
    public ComponentInstantiationFormat(boolean useOptionalComponentKeyword) {
        this.useOptionalComponentKeyword = useOptionalComponentKeyword;
    }

    /**
     * Returns if the <code>component</code> keyword is included in the VHDL output.
     * @return <code>true</code>, if the <code>component</code> keyword is used
     */
    public boolean isUseOptionalComponentKeyword() {
        return useOptionalComponentKeyword;
    }

    /**
     * Sets if the <code>component</code> keyword should be included in the VHDL output.
     * @param useOptionalComponentKeyword <code>true</code>,
     *        if the <code>component</code> keyword should be included in the output
     */
    public void setUseOptionalComponentKeyword(boolean useOptionalComponentKeyword) {
        this.useOptionalComponentKeyword = useOptionalComponentKeyword;
    }
}

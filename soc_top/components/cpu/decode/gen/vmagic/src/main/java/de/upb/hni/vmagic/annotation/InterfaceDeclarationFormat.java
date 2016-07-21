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
 * Interface declaration format annotation.
 * The interface declaration format annotation is used to customize the VHDL
 * output of interface declarations. The options only affect cases where the
 * mode and/or object class are optional. The mode and/or object class are
 * automatically added to the output if they are semantically necessary.
 */
public class InterfaceDeclarationFormat {

    private boolean useObjectClass;
    private boolean useMode;

    /**
     * Creates a interface declaration format annotation.
     * @param useObjectClass true, if the object class should be outputted
     * @param useMode true, if the mode should be included in the output
     */
    public InterfaceDeclarationFormat(boolean useObjectClass, boolean useMode) {
        this.useObjectClass = useObjectClass;
        this.useMode = useMode;
    }

    /**
     * Returns if the mode is always included in the output.
     * @return <code>true</code>, if the mode is always included in the output
     */
    public boolean isUseMode() {
        return useMode;
    }

    /**
     * Sets if the mode should always be included in the output.
     * @param useMode <code>true</code>, if the mode should always be included in the output
     */
    public void setUseMode(boolean useMode) {
        this.useMode = useMode;
    }

    /**
     * Returns if the object class is always included in the output.
     * @return <code>true</code>, if the object class is always included in the output
     */
    public boolean isUseObjectClass() {
        return useObjectClass;
    }

    /**
     * Sets if the object class should always be included in the outup.
     * @param useObjectClass <code>true</code>, if the object class should always be included
     */
    public void setUseObjectClass(boolean useObjectClass) {
        this.useObjectClass = useObjectClass;
    }
}

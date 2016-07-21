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

package de.upb.hni.vmagic.output;

/**
 * Customizable VHDL code format.
 */
public class CustomCodeFormat implements VhdlCodeFormat {

    private String lineSeparator;
    private String indentationString;
    private boolean align;
    private boolean repeatLabels;
    private boolean upperCaseKeywords;

    /**
     * Creates a custom code format.
     */
    public CustomCodeFormat() {
        lineSeparator = VhdlCodeFormat.DEFAULT.getLineSeparator();
        indentationString = VhdlCodeFormat.DEFAULT.getIndentationString();
        align = VhdlCodeFormat.DEFAULT.isAlign();
        repeatLabels = VhdlCodeFormat.DEFAULT.isRepeatLabels();
        upperCaseKeywords = VhdlCodeFormat.DEFAULT.isUpperCaseKeywords();
    }

    /**
     * Returns the line separator.
     * @return the line separator
     */
    public String getLineSeparator() {
        return lineSeparator;
    }

    /**
     * Sets the line separator.
     * @param lineSeparator the line separator
     */
    public void setLineSeparator(String lineSeparator) {
        this.lineSeparator = lineSeparator;
    }

    /**
     * Returns the indentation string.
     * @return the indentation string
     */
    public String getIndentationString() {
        return indentationString;
    }

    /**
     * Sets the indentation string.
     * @param indentationString the indentation string
     */
    public void setIndentationString(String indentationString) {
        this.indentationString = indentationString;
    }

    /**
     * Returns if the output should be aligned.
     * @return <code>true</code>, if the code should be aligned
     */
    public boolean isAlign() {
        return align;
    }

    /**
     * Sets if the output should be aligned.
     * @param align <code>true</code>, if the code should be aligned
     */
    public void setAlign(boolean align) {
        this.align = align;
    }

    /**
     * Returns if labels are repeated at the end of a block.
     * @return <code>true</code>, if labels should be repeated
     */
    public boolean isRepeatLabels() {
        return repeatLabels;
    }

    /**
     * Sets if labels should be repeated at the end of a block.
     * @param repeatLabels <code>true</code>, if labels should be repeated
     */
    public void setRepeatLabels(boolean repeatLabels) {
        this.repeatLabels = repeatLabels;
    }

    /**
     * Returns if upper case keywords are used.
     * @return <code>true</code>, if upper case keywords are used
     */
    public boolean isUpperCaseKeywords() {
        return upperCaseKeywords;
    }

    /**
     * Sets if upper case keywords should be used.
     * @param upperCaseKeywords <code>true</code>, if upper case keyword should be used
     */
    public void setUpperCaseKeywords(boolean upperCaseKeywords) {
        this.upperCaseKeywords = upperCaseKeywords;
    }
}

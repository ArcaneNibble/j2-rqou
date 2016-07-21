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
 * The VhdlCodeFormatOld defines the output format of the generated source code.
 */
public interface VhdlCodeFormat {

    /**
     * Returns the line separator.
     * @return the line separator
     */
    public String getLineSeparator();

    /**
     * Returns the indentation string.
     * @return the indentation string
     */
    public String getIndentationString();

    /**
     * Returns true if output elements should be aligned
     * (e.g. signals in a port).
     * @return true, if the output elements should be aligned
     */
    public boolean isAlign();

    /**
     * Returns true if labeles should be repeated at the end of a construct
     * (e.g. an entity).
     * @return true, if labeles should be repeated
     */
    public boolean isRepeatLabels();

    /**
     * Returns true if upper case keywords should be used.
     * @return true, if upper case keywords should be used
     */
    public boolean isUpperCaseKeywords();

    /**
     * The default code format.
     */
    public static final VhdlCodeFormat DEFAULT = new VhdlCodeFormat() {

        public String getLineSeparator() {
            return System.getProperty("line.separator");
        }

        public String getIndentationString() {
            return "    ";
        }

        public boolean isAlign() {
            return false;
        }

        public boolean isRepeatLabels() {
            return false;
        }

        public boolean isUpperCaseKeywords() {
            return false;
        }
    };
}

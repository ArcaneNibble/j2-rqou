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

import de.upb.hni.vmagic.VhdlElement;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Collection of functions to generate VHDL output.
 * This class provides methods to convert an instance of a meta class to VHDL code. The generated
 * VHDL code can be converted to a <code>String</code>, printed to <code>System.out</code> or
 * can be written directly to a file or a <code>Writer</code>.
 */
public class VhdlOutput {

    private static final int INITIAL_STRING_CAPACITY = 8192;

    /**
     * Prevent instantiation.
     */
    private VhdlOutput() {
    }

    /**
     * Converts a <code>VhdlElement</code> to a string using the default code format.
     * @param element the VHDL element
     * @return the converted string
     */
    public static String toVhdlString(VhdlElement element) {
        return toVhdlString(element, VhdlCodeFormat.DEFAULT);

    }

    /**
     * Converts a <code>VhdlElement</code> to a string using a custom code format.
     * @param element the VHDL element
     * @param format the custom code format
     * @return the converted string
     */
    public static String toVhdlString(VhdlElement element, VhdlCodeFormat format) {
        StringWriter stringWriter = new StringWriter(INITIAL_STRING_CAPACITY);

        try {
            toWriter(element, format, stringWriter);
        } catch (IOException ex) {
            //shouldn't happen for a string writer
            throw new IllegalStateException();
        }

        return stringWriter.toString();
    }

    /**
     * Writes the VHDL representation of a <code>VhdlElement</code> to a file.
     * The default code format is used to convert the <code>VhdlElement</code> to VHDL code.
     * @param element the VHDL element
     * @param fileName the name of the file
     * @throws IOException if an error occured while accessing the file
     */
    public static void toFile(VhdlElement element, String fileName) throws IOException {
        toFile(element, VhdlCodeFormat.DEFAULT, fileName);
    }

    /**
     * Writes the VHDL representation of a <code>VhdlElement</code> to a file using a custom
     * code format.
     * @param element the VHDL element
     * @param format the custom code format
     * @param fileName the name of the file
     * @throws IOException if an error occured while accessing the file
     */
    public static void toFile(VhdlElement element, VhdlCodeFormat format, String fileName)
            throws IOException {
        FileWriter writer = new FileWriter(fileName);
        toWriter(element, format, writer);
        writer.close();
    }

    /**
     * Outputs the VHDL representation of a <code>VhdlElement</code> to a <code>Writer</code>.
     * The default code format is used to generate the VHDL output.
     * @param element the VHDL element
     * @param writer the <code>Writer</code>
     * @throws IOException if an error occured during writing
     */
    public static void toWriter(VhdlElement element, Writer writer) throws IOException {
        toWriter(element, VhdlCodeFormat.DEFAULT, writer);
    }

    /**
     * Outputs the VHDL representation of a <code>VhdlElement</code> to a <code>Writer</code> using
     * a custom code format.
     * @param element the VHDL element
     * @param format the custom code format
     * @param writer the <code>Writer</code>
     * @throws IOException if an error occured during writing
     */
    public static void toWriter(VhdlElement element, VhdlCodeFormat format, Writer writer)
            throws IOException {
        VhdlWriter vhdlWriter = new VhdlWriter(writer, format);
        OutputModule output = new VhdlOutputModule(vhdlWriter);
        output.writeVhdlElement(element);
    }

    /**
     * Prints the VHDL representation of a <code>VhdlElement</code> to <code>System.out</code>.
     * The default code format is used to convert the <code>VhdlElement</code> to the string that
     * is printed.
     * @param element the VHDL element
     */
    public static void print(VhdlElement element) {
        System.out.println(toVhdlString(element));
    }

    /**
     * Prints the VHDL representation of a <code>VhdlElement</code> to <code>System.out</code> using
     * a custom code format.
     * @param element the VHDL element
     * @param format the custom code format
     */
    public static void print(VhdlElement element, VhdlCodeFormat format) {
        System.out.println(toVhdlString(element, format));
    }
}

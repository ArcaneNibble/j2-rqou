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

package de.upb.hni.vmagic.builtin;

import de.upb.hni.vmagic.declaration.FileDeclaration;
import de.upb.hni.vmagic.declaration.FunctionDeclaration;
import de.upb.hni.vmagic.declaration.Subtype;
import de.upb.hni.vmagic.libraryunit.PackageDeclaration;
import de.upb.hni.vmagic.literal.StringLiteral;
import de.upb.hni.vmagic.object.FileObject;
import de.upb.hni.vmagic.type.AccessType;
import de.upb.hni.vmagic.type.EnumerationType;
import de.upb.hni.vmagic.type.FileType;

/**
 * TEXTIO package wrapper.
 */
public class TextIO {

    /** LINE type. */
    public static final AccessType LINE = new AccessType("LINE", Standard.STRING);
    /** TEXT type. */
    public static final FileType TEXT = new FileType("TEXT", Standard.STRING);
    /** SIDE type. */
    public static final EnumerationType SIDE = new EnumerationType("SIDE", "RIGHT", "LEFT");
    /** WIDTH subtype. */
    public static final Subtype WIDTH = new Subtype("WIDTH", Standard.NATURAL);
    /** INPUT file. */
    public static final FileObject INPUT = new FileObject("INPUT", TEXT, Standard.FILE_OPEN_KIND_READ_MODE, new StringLiteral("STD_INPUT"));
    /** OUTPUT file. */
    public static final FileObject OUTPUT = new FileObject("OUTPUT", TEXT, Standard.FILE_OPEN_KIND_WRITE_MODE, new StringLiteral("STD_OUTPUT"));
    /** ENDFILE function. */
    public static final FunctionDeclaration ENDFILE = new FunctionDeclaration("ENDFILE", Standard.BOOLEAN, new FileObject("F", TEXT));

    /** TEXTIO package. */
    public static final PackageDeclaration PACKAGE = new PackageDeclaration("textio");

    static {
        PACKAGE.getDeclarations().add(LINE);
        PACKAGE.getDeclarations().add(TEXT);
        PACKAGE.getDeclarations().add(SIDE);
        PACKAGE.getDeclarations().add(WIDTH);
        PACKAGE.getDeclarations().add(new FileDeclaration(INPUT));
        PACKAGE.getDeclarations().add(new FileDeclaration(OUTPUT));
        PACKAGE.getDeclarations().add(ENDFILE);
    }

    //Prevent instantiation
    private TextIO() {
    }
}

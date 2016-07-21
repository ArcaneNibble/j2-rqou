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

import de.upb.hni.vmagic.LibraryDeclarativeRegion;
import de.upb.hni.vmagic.VhdlFile;

/**
 * Builtin library wrapper.
 */
public class Libraries {

    /** IEEE library. */
    public static final LibraryDeclarativeRegion IEEE = new LibraryDeclarativeRegion("ieee");
    /** STD library. */
    public static final LibraryDeclarativeRegion STD = new LibraryDeclarativeRegion("std");

    static {
        VhdlFile ieeeFile = new VhdlFile();
        ieeeFile.getElements().add(StdLogic1164.PACKAGE);
        ieeeFile.getElements().add(StdLogicArith.PACKAGE);
        ieeeFile.getElements().add(StdLogicSigned.PACKAGE);
        ieeeFile.getElements().add(StdLogicUnsigned.PACKAGE);
        ieeeFile.getElements().add(NumericStd.PACKAGE);
        IEEE.getFiles().add(ieeeFile);

        VhdlFile stdFile = new VhdlFile();
        stdFile.getElements().add(Standard.PACKAGE);
        stdFile.getElements().add(TextIO.PACKAGE);
        STD.getFiles().add(stdFile);
    }

    //Prevent instantiation.
    private Libraries() {
    }
}

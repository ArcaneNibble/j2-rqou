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

import de.upb.hni.vmagic.libraryunit.UseClause;
import de.upb.hni.vmagic.declaration.FunctionDeclaration;
import de.upb.hni.vmagic.libraryunit.PackageDeclaration;
import de.upb.hni.vmagic.object.Constant;

/**
 * STD_LOGIC_SIGNED package wrapper.
 */
public class StdLogicSigned {

    /** Use clause for all declarations in this package. */
    public static final UseClause USE_CLAUSE =
            new UseClause("ieee.std_logic_signed.all");
    //public static final FunctionDeclaration SHL_SLV_SLV_SLV = new FunctionDeclaration("SHL", StdLogic1164.STD_LOGIC_VECTOR, new Constant("ARG", StdLogic1164.STD_LOGIC_VECTOR), new Constant("COUNT", StdLogic1164.STD_LOGIC_VECTOR));
    //public static final FunctionDeclaration SHR_SLV_SLV_SLV = new FunctionDeclaration("SHR", StdLogic1164.STD_LOGIC_VECTOR, new Constant("ARG", StdLogic1164.STD_LOGIC_VECTOR), new Constant("COUNT", StdLogic1164.STD_LOGIC_VECTOR));
    
    /** CONV_INTEGER function. */
    public static final FunctionDeclaration CONV_INTEGER_SLV_INTEGER = new FunctionDeclaration("CONV_INTEGER", Standard.INTEGER, new Constant("ARG", StdLogic1164.STD_LOGIC_VECTOR));
    
    /** STD_LOGIC_SIGNED package. */
    public static final PackageDeclaration PACKAGE = new PackageDeclaration("std_logic_signed");

    static {
        //PACKAGE.getDeclarations().add(SHL_SLV_SLV_SLV);
        //PACKAGE.getDeclarations().add(SHR_SLV_SLV_SLV);
        PACKAGE.getDeclarations().add(CONV_INTEGER_SLV_INTEGER);
    }

    //Prevent instantiation.
    private StdLogicSigned() {
    }
}

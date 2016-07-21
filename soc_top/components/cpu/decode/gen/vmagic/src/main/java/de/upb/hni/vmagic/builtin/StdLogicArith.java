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

import de.upb.hni.vmagic.Range;
import de.upb.hni.vmagic.libraryunit.UseClause;
import de.upb.hni.vmagic.declaration.FunctionDeclaration;
import de.upb.hni.vmagic.declaration.Subtype;
import de.upb.hni.vmagic.libraryunit.PackageDeclaration;
import de.upb.hni.vmagic.object.Constant;
import de.upb.hni.vmagic.type.RangeSubtypeIndication;
import de.upb.hni.vmagic.type.UnconstrainedArray;

/**
 * STD_LOGIC_ARITH package wrapper.
 */
public class StdLogicArith {

    /** Use clause for all declarations in this package. */
    public static final UseClause USE_CLAUSE =
            new UseClause("ieee.std_logic_arith.all");

    /** UNSIGNED type. */
    public static final UnconstrainedArray UNSIGNED =
            new UnconstrainedArray("unsigned", StdLogic1164.STD_LOGIC, Standard.NATURAL);
    /** SIGNED type. */
    public static final UnconstrainedArray SIGNED =
            new UnconstrainedArray("signed", StdLogic1164.STD_LOGIC, Standard.NATURAL);    
    /** SMALL_INT subtype. */
    public static final Subtype SMALL_INT = new Subtype("small_int", new RangeSubtypeIndication(Standard.INTEGER, new Range(0, Range.Direction.TO, 1)));

//    public static final FunctionDeclaration Plus_UNSIGNED_UNSIGNED_UNSIGNED = new FunctionDeclaration("\"+\"", UNSIGNED, new Constant("L", UNSIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Plus_SIGNED_SIGNED_SIGNED = new FunctionDeclaration("\"+\"", SIGNED, new Constant("L", SIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Plus_UNSIGNED_SIGNED_SIGNED = new FunctionDeclaration("\"+\"", SIGNED, new Constant("L", UNSIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Plus_SIGNED_UNSIGNED_SIGNED = new FunctionDeclaration("\"+\"", SIGNED, new Constant("L", SIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Plus_UNSIGNED_INTEGER_UNSIGNED = new FunctionDeclaration("\"+\"", UNSIGNED, new Constant("L", UNSIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration Plus_INTEGER_UNSIGNED_UNSIGNED = new FunctionDeclaration("\"+\"", UNSIGNED, new Constant("L", Standard.INTEGER), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Plus_SIGNED_INTEGER_SIGNED = new FunctionDeclaration("\"+\"", SIGNED, new Constant("L", SIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration Plus_INTEGER_SIGNED_SIGNED = new FunctionDeclaration("\"+\"", SIGNED, new Constant("L", Standard.INTEGER), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Plus_UNSIGNED_STD_ULOGIC_UNSIGNED = new FunctionDeclaration("\"+\"", UNSIGNED, new Constant("L", UNSIGNED), new Constant("R", StdLogic1164.STD_ULOGIC));
//    public static final FunctionDeclaration Plus_STD_ULOGIC_UNSIGNED_UNSIGNED = new FunctionDeclaration("\"+\"", UNSIGNED, new Constant("L", StdLogic1164.STD_ULOGIC), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Plus_SIGNED_STD_ULOGIC_SIGNED = new FunctionDeclaration("\"+\"", SIGNED, new Constant("L", SIGNED), new Constant("R", StdLogic1164.STD_ULOGIC));
//    public static final FunctionDeclaration Plus_STD_ULOGIC_SIGNED_SIGNED = new FunctionDeclaration("\"+\"", SIGNED, new Constant("L", StdLogic1164.STD_ULOGIC), new Constant("R", SIGNED));
//
//    public static final FunctionDeclaration Plus_UNSIGNED_UNSIGNED_SLV = new FunctionDeclaration("\"+\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", UNSIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Plus_SIGNED_SIGNED_SLV = new FunctionDeclaration("\"+\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", SIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Plus_UNSIGNED_SIGNED_SLV = new FunctionDeclaration("\"+\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", UNSIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Plus_SIGNED_UNSIGNED_SLV = new FunctionDeclaration("\"+\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", SIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Plus_UNSIGNED_INTEGER_SLV = new FunctionDeclaration("\"+\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", UNSIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration Plus_INTEGER_UNSIGNED_SLV = new FunctionDeclaration("\"+\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", Standard.INTEGER), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Plus_SIGNED_INTEGER_SLV = new FunctionDeclaration("\"+\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", SIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration Plus_INTEGER_SIGNED_SLV = new FunctionDeclaration("\"+\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", Standard.INTEGER), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Plus_UNSIGNED_STD_ULOGIC_SLV = new FunctionDeclaration("\"+\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", UNSIGNED), new Constant("R", StdLogic1164.STD_ULOGIC));
//    public static final FunctionDeclaration Plus_STD_ULOGIC_UNSIGNED_SLV = new FunctionDeclaration("\"+\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", StdLogic1164.STD_ULOGIC), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Plus_SIGNED_STD_ULOGIC_SLV = new FunctionDeclaration("\"+\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", SIGNED), new Constant("R", StdLogic1164.STD_ULOGIC));
//    public static final FunctionDeclaration Plus_STD_ULOGIC_SIGNED_SLV = new FunctionDeclaration("\"+\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", StdLogic1164.STD_ULOGIC), new Constant("R", SIGNED));
//
//
//    public static final FunctionDeclaration Minus_UNSIGNED_UNSIGNED_UNSIGNED = new FunctionDeclaration("\"-\"", UNSIGNED, new Constant("L", UNSIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Minus_SIGNED_SIGNED_SIGNED = new FunctionDeclaration("\"-\"", SIGNED, new Constant("L", SIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Minus_UNSIGNED_SIGNED_SIGNED = new FunctionDeclaration("\"-\"", SIGNED, new Constant("L", UNSIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Minus_SIGNED_UNSIGNED_SIGNED = new FunctionDeclaration("\"-\"", SIGNED, new Constant("L", SIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Minus_UNSIGNED_INTEGER_UNSIGNED = new FunctionDeclaration("\"-\"", UNSIGNED, new Constant("L", UNSIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration Minus_INTEGER_UNSIGNED_UNSIGNED = new FunctionDeclaration("\"-\"", UNSIGNED, new Constant("L", Standard.INTEGER), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Minus_SIGNED_INTEGER_SIGNED = new FunctionDeclaration("\"-\"", SIGNED, new Constant("L", SIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration Minus_INTEGER_SIGNED_SIGNED = new FunctionDeclaration("\"-\"", SIGNED, new Constant("L", Standard.INTEGER), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Minus_UNSIGNED_STD_ULOGIC_UNSIGNED = new FunctionDeclaration("\"-\"", UNSIGNED, new Constant("L", UNSIGNED), new Constant("R", StdLogic1164.STD_ULOGIC));
//    public static final FunctionDeclaration Minus_STD_ULOGIC_UNSIGNED_UNSIGNED = new FunctionDeclaration("\"-\"", UNSIGNED, new Constant("L", StdLogic1164.STD_ULOGIC), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Minus_SIGNED_STD_ULOGIC_SIGNED = new FunctionDeclaration("\"-\"", SIGNED, new Constant("L", SIGNED), new Constant("R", StdLogic1164.STD_ULOGIC));
//    public static final FunctionDeclaration Minus_STD_ULOGIC_SIGNED_SIGNED = new FunctionDeclaration("\"-\"", SIGNED, new Constant("L", StdLogic1164.STD_ULOGIC), new Constant("R", SIGNED));
//
//    public static final FunctionDeclaration Minus_UNSIGNED_UNSIGNED_SLV = new FunctionDeclaration("\"-\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", UNSIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Minus_SIGNED_SIGNED_SLV = new FunctionDeclaration("\"-\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", SIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Minus_UNSIGNED_SIGNED_SLV = new FunctionDeclaration("\"-\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", UNSIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Minus_SIGNED_UNSIGNED_SLV = new FunctionDeclaration("\"-\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", SIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Minus_UNSIGNED_INTEGER_SLV = new FunctionDeclaration("\"-\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", UNSIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration Minus_INTEGER_UNSIGNED_SLV = new FunctionDeclaration("\"-\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", Standard.INTEGER), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Minus_SIGNED_INTEGER_SLV = new FunctionDeclaration("\"-\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", SIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration Minus_INTEGER_SIGNED_SLV = new FunctionDeclaration("\"-\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", Standard.INTEGER), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Minus_UNSIGNED_STD_ULOGIC_SLV = new FunctionDeclaration("\"-\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", UNSIGNED), new Constant("R", StdLogic1164.STD_ULOGIC));
//    public static final FunctionDeclaration Minus_STD_ULOGIC_UNSIGNED_SLV = new FunctionDeclaration("\"-\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", StdLogic1164.STD_ULOGIC), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Minus_SIGNED_STD_ULOGIC_SLV = new FunctionDeclaration("\"-\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", SIGNED), new Constant("R", StdLogic1164.STD_ULOGIC));
//    public static final FunctionDeclaration Minus_STD_ULOGIC_SIGNED_SLV = new FunctionDeclaration("\"-\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", StdLogic1164.STD_ULOGIC), new Constant("R", SIGNED));
//
//    public static final FunctionDeclaration Plus_UNSIGNED_UNSIGNED = new FunctionDeclaration("\"+\"", UNSIGNED, new Constant("L", UNSIGNED));
//    public static final FunctionDeclaration Plus_SIGNED_SIGNED = new FunctionDeclaration("\"+\"", SIGNED, new Constant("L", SIGNED));
//    public static final FunctionDeclaration Minus_UNSIGNED_UNSIGNED = new FunctionDeclaration("\"-\"", SIGNED, new Constant("L", SIGNED));
//    public static final FunctionDeclaration ABS_UNSIGNED_UNSIGNED = new FunctionDeclaration("\"+\"", SIGNED, new Constant("L", SIGNED));
//
//    public static final FunctionDeclaration Plus_UNSIGNED_SLV = new FunctionDeclaration("\"+\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", UNSIGNED));
//    public static final FunctionDeclaration Plus_SIGNED_SLV = new FunctionDeclaration("\"+\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", SIGNED));
//    public static final FunctionDeclaration Minus_UNSIGNED_SLV = new FunctionDeclaration("\"-\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", SIGNED));
//    public static final FunctionDeclaration ABS_UNSIGNED_SLV = new FunctionDeclaration("\"abs\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", SIGNED));
//
//    public static final FunctionDeclaration Mult_UNSIGNED_UNSIGNED_UNSIGNED = new FunctionDeclaration("\"*\"", UNSIGNED, new Constant("L", UNSIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Mult_SIGNED_SIGNED_SIGNED = new FunctionDeclaration("\"*\"", SIGNED, new Constant("L", SIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Mult_SIGNED_UNSIGNED_SIGNED = new FunctionDeclaration("\"*\"", SIGNED, new Constant("L", SIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Mult_UNSIGNED_SIGNED_SIGNED = new FunctionDeclaration("\"abs\"", SIGNED, new Constant("L", UNSIGNED), new Constant("R", SIGNED));
//
//    public static final FunctionDeclaration Mult_UNSIGNED_UNSIGNED_SLV = new FunctionDeclaration("\"*\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", UNSIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Mult_SIGNED_SIGNED_SLV = new FunctionDeclaration("\"*\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", SIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Mult_SIGNED_UNSIGNED_SLV = new FunctionDeclaration("\"*\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", SIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Mult_UNSIGNED_SIGNED_SLV = new FunctionDeclaration("\"*\"", StdLogic1164.STD_LOGIC_VECTOR, new Constant("L", UNSIGNED), new Constant("R", SIGNED));
//
//    public static final FunctionDeclaration Less_UNSIGNED_UNSIGNED_BOOLEAN = new FunctionDeclaration("\"<\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Less_SIGNED_SIGNED_BOOLEAN = new FunctionDeclaration("\"<\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Less_UNSIGNED_SIGNED_BOOLEAN = new FunctionDeclaration("\"<\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Less_SIGNED_UNSIGNED_BOOLEAN = new FunctionDeclaration("\"<\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Less_UNSIGNED_INTEGER_BOOLEAN = new FunctionDeclaration("\"<\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration Less_INTEGER_UNSIGNED_BOOLEAN = new FunctionDeclaration("\"<\"", Standard.BOOLEAN, new Constant("L", Standard.INTEGER), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Less_SIGNED_INTEGER_BOOLEAN = new FunctionDeclaration("\"<\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration Less_INTEGER_SIGNED_BOOLEAN = new FunctionDeclaration("\"<\"", Standard.BOOLEAN, new Constant("L", Standard.INTEGER), new Constant("R", SIGNED));
//
//    public static final FunctionDeclaration LessOrEqual_UNSIGNED_UNSIGNED_BOOLEAN = new FunctionDeclaration("\"<=\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration LessOrEqual_SIGNED_SIGNED_BOOLEAN = new FunctionDeclaration("\"<=\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration LessOrEqual_UNSIGNED_SIGNED_BOOLEAN = new FunctionDeclaration("\"<=\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration LessOrEqual_SIGNED_UNSIGNED_BOOLEAN = new FunctionDeclaration("\"<=\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration LessOrEqual_UNSIGNED_INTEGER_BOOLEAN = new FunctionDeclaration("\"<=\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration LessOrEqual_INTEGER_UNSIGNED_BOOLEAN = new FunctionDeclaration("\"<=\"", Standard.BOOLEAN, new Constant("L", Standard.INTEGER), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration LessOrEqual_SIGNED_INTEGER_BOOLEAN = new FunctionDeclaration("\"<=\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration LessOrEqual_INTEGER_SIGNED_BOOLEAN = new FunctionDeclaration("\"<=\"", Standard.BOOLEAN, new Constant("L", Standard.INTEGER), new Constant("R", SIGNED));
//
//    public static final FunctionDeclaration Greater_UNSIGNED_UNSIGNED_BOOLEAN = new FunctionDeclaration("\">\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Greater_SIGNED_SIGNED_BOOLEAN = new FunctionDeclaration("\">\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Greater_UNSIGNED_SIGNED_BOOLEAN = new FunctionDeclaration("\">\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Greater_SIGNED_UNSIGNED_BOOLEAN = new FunctionDeclaration("\">\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Greater_UNSIGNED_INTEGER_BOOLEAN = new FunctionDeclaration("\">\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration Greater_INTEGER_UNSIGNED_BOOLEAN = new FunctionDeclaration("\">\"", Standard.BOOLEAN, new Constant("L", Standard.INTEGER), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Greater_SIGNED_INTEGER_BOOLEAN = new FunctionDeclaration("\">\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration Greater_INTEGER_SIGNED_BOOLEAN = new FunctionDeclaration("\">\"", Standard.BOOLEAN, new Constant("L", Standard.INTEGER), new Constant("R", SIGNED));
//
//    public static final FunctionDeclaration GreaterOrEqual_UNSIGNED_UNSIGNED_BOOLEAN = new FunctionDeclaration("\">=\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration GreaterOrEqual_SIGNED_SIGNED_BOOLEAN = new FunctionDeclaration("\">=\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration GreaterOrEqual_UNSIGNED_SIGNED_BOOLEAN = new FunctionDeclaration("\">=\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration GreaterOrEqual_SIGNED_UNSIGNED_BOOLEAN = new FunctionDeclaration("\">=\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration GreaterOrEqual_UNSIGNED_INTEGER_BOOLEAN = new FunctionDeclaration("\">=\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration GreaterOrEqual_INTEGER_UNSIGNED_BOOLEAN = new FunctionDeclaration("\">=\"", Standard.BOOLEAN, new Constant("L", Standard.INTEGER), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration GreaterOrEqual_SIGNED_INTEGER_BOOLEAN = new FunctionDeclaration("\">=\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration GreaterOrEqual_INTEGER_SIGNED_BOOLEAN = new FunctionDeclaration("\">=\"", Standard.BOOLEAN, new Constant("L", Standard.INTEGER), new Constant("R", SIGNED));
//
//    public static final FunctionDeclaration Equal_UNSIGNED_UNSIGNED_BOOLEAN = new FunctionDeclaration("\"=\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Equal_SIGNED_SIGNED_BOOLEAN = new FunctionDeclaration("\"=\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Equal_UNSIGNED_SIGNED_BOOLEAN = new FunctionDeclaration("\"=\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration Equal_SIGNED_UNSIGNED_BOOLEAN = new FunctionDeclaration("\"=\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Equal_UNSIGNED_INTEGER_BOOLEAN = new FunctionDeclaration("\"=\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration Equal_INTEGER_UNSIGNED_BOOLEAN = new FunctionDeclaration("\"=\"", Standard.BOOLEAN, new Constant("L", Standard.INTEGER), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration Equal_SIGNED_INTEGER_BOOLEAN = new FunctionDeclaration("\"=\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration Equal_INTEGER_SIGNED_BOOLEAN = new FunctionDeclaration("\"=\"", Standard.BOOLEAN, new Constant("L", Standard.INTEGER), new Constant("R", SIGNED));
//
//
//    public static final FunctionDeclaration UnEqual_UNSIGNED_UNSIGNED_BOOLEAN = new FunctionDeclaration("\"/=\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration UnEqual_SIGNED_SIGNED_BOOLEAN = new FunctionDeclaration("\"/=\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration UnEqual_UNSIGNED_SIGNED_BOOLEAN = new FunctionDeclaration("\"/=\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", SIGNED));
//    public static final FunctionDeclaration UnEqual_SIGNED_UNSIGNED_BOOLEAN = new FunctionDeclaration("\"/=\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration UnEqual_UNSIGNED_INTEGER_BOOLEAN = new FunctionDeclaration("\"/=\"", Standard.BOOLEAN, new Constant("L", UNSIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration UnEqual_INTEGER_UNSIGNED_BOOLEAN = new FunctionDeclaration("\"/=\"", Standard.BOOLEAN, new Constant("L", Standard.INTEGER), new Constant("R", UNSIGNED));
//    public static final FunctionDeclaration UnEqual_SIGNED_INTEGER_BOOLEAN = new FunctionDeclaration("\"/=\"", Standard.BOOLEAN, new Constant("L", SIGNED), new Constant("R", Standard.INTEGER));
//    public static final FunctionDeclaration UnEqual_INTEGER_SIGNED_BOOLEAN = new FunctionDeclaration("\"/=\"", Standard.BOOLEAN, new Constant("L", Standard.INTEGER), new Constant("R", SIGNED));

//    public static final FunctionDeclaration ShiftLeft_UNSIGNED_UNSIGNED_UNSIGNED = new FunctionDeclaration("SHL", UNSIGNED, new Constant("ARG", UNSIGNED), new Constant("Count", UNSIGNED));
//    public static final FunctionDeclaration ShiftLeft_SIGNED_UNSIGNED_SIGNED = new FunctionDeclaration("SHL", SIGNED, new Constant("ARG", SIGNED), new Constant("Count", UNSIGNED));
//    public static final FunctionDeclaration ShiftRight_UNSIGNED_UNSIGNED_UNSIGNED = new FunctionDeclaration("SHR", UNSIGNED, new Constant("ARG", UNSIGNED), new Constant("Count", UNSIGNED));
//    public static final FunctionDeclaration ShiftRight_SIGNED_UNSIGNED_SIGNED = new FunctionDeclaration("SHR", SIGNED, new Constant("ARG", SIGNED), new Constant("Count", UNSIGNED));

    /** CONV_INTEGER function. */
    public static final FunctionDeclaration CONV_INTEGER_INTEGER_INTEGER = new FunctionDeclaration("CONV_INTEGER", Standard.INTEGER, new Constant("ARG", Standard.INTEGER));
    /** CONV_INTEGER function. */
    public static final FunctionDeclaration CONV_INTEGER_UNSIGNED_INTEGER = new FunctionDeclaration("CONV_INTEGER", Standard.INTEGER, new Constant("ARG", UNSIGNED));
    /** CONV_INTEGER function. */
    public static final FunctionDeclaration CONV_INTEGER_SIGNED_INTEGER = new FunctionDeclaration("CONV_INTEGER", Standard.INTEGER, new Constant("ARG", SIGNED));
    /** CONV_INTEGER function. */
    public static final FunctionDeclaration CONV_INTEGER_STD_ULOGIC_SMALL_INT = new FunctionDeclaration("CONV_INTEGER", SMALL_INT, new Constant("ARG", StdLogic1164.STD_ULOGIC));

    /** CONV_UNSIGNED function. */
    public static final FunctionDeclaration CONV_UNSIGNED_INTEGER_INTEGER_UNSIGNED = new FunctionDeclaration("CONV_UNSIGNED", UNSIGNED, new Constant("ARG", Standard.INTEGER), new Constant("SIZE", Standard.INTEGER));
    /** CONV_UNSIGNED function. */
    public static final FunctionDeclaration CONV_UNSIGNED_UNSIGNED_INTEGER_UNSIGNED = new FunctionDeclaration("CONV_UNSIGNED", UNSIGNED, new Constant("ARG", UNSIGNED), new Constant("SIZE", Standard.INTEGER));
    /** CONV_UNSIGNED function. */
    public static final FunctionDeclaration CONV_UNSIGNED_SIGNED_INTEGER_UNSIGNED = new FunctionDeclaration("CONV_UNSIGNED", UNSIGNED, new Constant("ARG", SIGNED), new Constant("SIZE", Standard.INTEGER));
    /** CONV_UNSIGNED function. */
    public static final FunctionDeclaration CONV_UNSIGNED_STD_ULOGIC_INTEGER_UNSIGNED = new FunctionDeclaration("CONV_UNSIGNED", UNSIGNED, new Constant("ARG", StdLogic1164.STD_ULOGIC), new Constant("SIZE", Standard.INTEGER));

    /** CONV_SIGNED function. */
    public static final FunctionDeclaration CONV_SIGNED_INTEGER_INTEGER_UNSIGNED = new FunctionDeclaration("CONV_SIGNED", SIGNED, new Constant("ARG", Standard.INTEGER), new Constant("SIZE", Standard.INTEGER));
    /** CONV_SIGNED function. */
    public static final FunctionDeclaration CONV_SIGNED_UNSIGNED_INTEGER_UNSIGNED = new FunctionDeclaration("CONV_SIGNED", SIGNED, new Constant("ARG", UNSIGNED), new Constant("SIZE", Standard.INTEGER));
    /** CONV_SIGNED function. */
    public static final FunctionDeclaration CONV_SIGNED_SIGNED_INTEGER_UNSIGNED = new FunctionDeclaration("CONV_SIGNED", SIGNED, new Constant("ARG", SIGNED), new Constant("SIZE", Standard.INTEGER));
    /** CONV_SIGNED function. */
    public static final FunctionDeclaration CONV_SIGNED_STD_ULOGIC_INTEGER_UNSIGNED = new FunctionDeclaration("CONV_SIGNED", SIGNED, new Constant("ARG", StdLogic1164.STD_ULOGIC), new Constant("SIZE", Standard.INTEGER));

    /** CONV_STD_LOGIC_VECTOR function. */
    public static final FunctionDeclaration CONV_SIGNED_INTEGER_INTEGER_SLV = new FunctionDeclaration("CONV_STD_LOGIC_VECTOR", StdLogic1164.STD_LOGIC_VECTOR, new Constant("ARG", Standard.INTEGER), new Constant("SIZE", Standard.INTEGER));
    /** CONV_STD_LOGIC_VECTOR function. */
    public static final FunctionDeclaration CONV_SIGNED_UNSIGNED_INTEGER_SLV = new FunctionDeclaration("CONV_STD_LOGIC_VECTOR", StdLogic1164.STD_LOGIC_VECTOR, new Constant("ARG", UNSIGNED), new Constant("SIZE", Standard.INTEGER));
    /** CONV_STD_LOGIC_VECTOR function. */
    public static final FunctionDeclaration CONV_SIGNED_SIGNED_INTEGER_SLV = new FunctionDeclaration("CONV_STD_LOGIC_VECTOR", StdLogic1164.STD_LOGIC_VECTOR, new Constant("ARG", SIGNED), new Constant("SIZE", Standard.INTEGER));
    /** CONV_STD_LOGIC_VECTOR function. */
    public static final FunctionDeclaration CONV_SIGNED_STD_ULOGIC_INTEGER_SLV = new FunctionDeclaration("CONV_STD_LOGIC_VECTOR", StdLogic1164.STD_LOGIC_VECTOR, new Constant("ARG", StdLogic1164.STD_ULOGIC), new Constant("SIZE", Standard.INTEGER));

    /** EXT function. */
    public static final FunctionDeclaration EXT_SLV_INTEGER_SLV = new FunctionDeclaration("EXT", StdLogic1164.STD_LOGIC_VECTOR, new Constant("ARG", StdLogic1164.STD_LOGIC_VECTOR), new Constant("SIZE", Standard.INTEGER));
    /** SXT function. */
    public static final FunctionDeclaration SXT_SLV_INTEGER_SLV = new FunctionDeclaration("SXT", StdLogic1164.STD_LOGIC_VECTOR, new Constant("ARG", StdLogic1164.STD_LOGIC_VECTOR), new Constant("SIZE", Standard.INTEGER));

    /** STD_LOGIC_ARITH package. */
    public static final PackageDeclaration PACKAGE = new PackageDeclaration("std_logic_arith");

    static {

        PACKAGE.getDeclarations().add(UNSIGNED);
        PACKAGE.getDeclarations().add(SIGNED);
        PACKAGE.getDeclarations().add(SMALL_INT);

//        PACKAGE.getDeclarations().add(Plus_UNSIGNED_UNSIGNED_UNSIGNED);
//        PACKAGE.getDeclarations().add(Plus_SIGNED_SIGNED_SIGNED);
//        PACKAGE.getDeclarations().add(Plus_UNSIGNED_SIGNED_SIGNED);
//        PACKAGE.getDeclarations().add(Plus_SIGNED_UNSIGNED_SIGNED);
//        PACKAGE.getDeclarations().add(Plus_UNSIGNED_INTEGER_UNSIGNED);
//        PACKAGE.getDeclarations().add(Plus_INTEGER_UNSIGNED_UNSIGNED);
//        PACKAGE.getDeclarations().add(Plus_SIGNED_INTEGER_SIGNED);
//        PACKAGE.getDeclarations().add(Plus_INTEGER_SIGNED_SIGNED);
//        PACKAGE.getDeclarations().add(Plus_UNSIGNED_STD_ULOGIC_UNSIGNED);
//        PACKAGE.getDeclarations().add(Plus_STD_ULOGIC_UNSIGNED_UNSIGNED);
//        PACKAGE.getDeclarations().add(Plus_SIGNED_STD_ULOGIC_SIGNED);
//        PACKAGE.getDeclarations().add(Plus_STD_ULOGIC_SIGNED_SIGNED);
//
//        PACKAGE.getDeclarations().add(Plus_UNSIGNED_UNSIGNED_SLV);
//        PACKAGE.getDeclarations().add(Plus_SIGNED_SIGNED_SLV);
//        PACKAGE.getDeclarations().add(Plus_UNSIGNED_SIGNED_SLV);
//        PACKAGE.getDeclarations().add(Plus_SIGNED_UNSIGNED_SLV);
//        PACKAGE.getDeclarations().add(Plus_UNSIGNED_INTEGER_SLV);
//        PACKAGE.getDeclarations().add(Plus_INTEGER_UNSIGNED_SLV);
//        PACKAGE.getDeclarations().add(Plus_SIGNED_INTEGER_SLV);
//        PACKAGE.getDeclarations().add(Plus_INTEGER_SIGNED_SLV);
//        PACKAGE.getDeclarations().add(Plus_UNSIGNED_STD_ULOGIC_SLV);
//        PACKAGE.getDeclarations().add(Plus_STD_ULOGIC_UNSIGNED_SLV);
//        PACKAGE.getDeclarations().add(Plus_SIGNED_STD_ULOGIC_SLV);
//        PACKAGE.getDeclarations().add(Plus_STD_ULOGIC_SIGNED_SLV);
//
//        PACKAGE.getDeclarations().add(Minus_UNSIGNED_UNSIGNED_UNSIGNED);
//        PACKAGE.getDeclarations().add(Minus_SIGNED_SIGNED_SIGNED);
//        PACKAGE.getDeclarations().add(Minus_UNSIGNED_SIGNED_SIGNED);
//        PACKAGE.getDeclarations().add(Minus_SIGNED_UNSIGNED_SIGNED);
//        PACKAGE.getDeclarations().add(Minus_UNSIGNED_INTEGER_UNSIGNED);
//        PACKAGE.getDeclarations().add(Minus_INTEGER_UNSIGNED_UNSIGNED);
//        PACKAGE.getDeclarations().add(Minus_SIGNED_INTEGER_SIGNED);
//        PACKAGE.getDeclarations().add(Minus_INTEGER_SIGNED_SIGNED);
//        PACKAGE.getDeclarations().add(Minus_UNSIGNED_STD_ULOGIC_UNSIGNED);
//        PACKAGE.getDeclarations().add(Minus_STD_ULOGIC_UNSIGNED_UNSIGNED);
//        PACKAGE.getDeclarations().add(Minus_SIGNED_STD_ULOGIC_SIGNED);
//        PACKAGE.getDeclarations().add(Minus_STD_ULOGIC_SIGNED_SIGNED);
//
//        PACKAGE.getDeclarations().add(Minus_UNSIGNED_UNSIGNED_SLV);
//        PACKAGE.getDeclarations().add(Minus_SIGNED_SIGNED_SLV);
//        PACKAGE.getDeclarations().add(Minus_UNSIGNED_SIGNED_SLV);
//        PACKAGE.getDeclarations().add(Minus_SIGNED_UNSIGNED_SLV);
//        PACKAGE.getDeclarations().add(Minus_UNSIGNED_INTEGER_SLV);
//        PACKAGE.getDeclarations().add(Minus_INTEGER_UNSIGNED_SLV);
//        PACKAGE.getDeclarations().add(Minus_SIGNED_INTEGER_SLV);
//        PACKAGE.getDeclarations().add(Minus_INTEGER_SIGNED_SLV);
//        PACKAGE.getDeclarations().add(Minus_UNSIGNED_STD_ULOGIC_SLV);
//        PACKAGE.getDeclarations().add(Minus_STD_ULOGIC_UNSIGNED_SLV);
//        PACKAGE.getDeclarations().add(Minus_SIGNED_STD_ULOGIC_SLV);
//        PACKAGE.getDeclarations().add(Minus_STD_ULOGIC_SIGNED_SLV);
//
//        PACKAGE.getDeclarations().add(Plus_UNSIGNED_UNSIGNED);
//        PACKAGE.getDeclarations().add(Plus_SIGNED_SIGNED);
//        PACKAGE.getDeclarations().add(Minus_UNSIGNED_UNSIGNED);
//        PACKAGE.getDeclarations().add(ABS_UNSIGNED_UNSIGNED);
//
//        PACKAGE.getDeclarations().add(Plus_UNSIGNED_SLV);
//        PACKAGE.getDeclarations().add(Plus_SIGNED_SLV);
//        PACKAGE.getDeclarations().add(Minus_UNSIGNED_SLV);
//        PACKAGE.getDeclarations().add(ABS_UNSIGNED_SLV);
//
//        PACKAGE.getDeclarations().add(Mult_UNSIGNED_UNSIGNED_UNSIGNED);
//        PACKAGE.getDeclarations().add(Mult_SIGNED_SIGNED_SIGNED);
//        PACKAGE.getDeclarations().add(Mult_SIGNED_UNSIGNED_SIGNED);
//        PACKAGE.getDeclarations().add(Mult_UNSIGNED_SIGNED_SIGNED);
//
//        PACKAGE.getDeclarations().add(Mult_UNSIGNED_UNSIGNED_SLV);
//        PACKAGE.getDeclarations().add(Mult_SIGNED_SIGNED_SLV);
//        PACKAGE.getDeclarations().add(Mult_SIGNED_UNSIGNED_SLV);
//        PACKAGE.getDeclarations().add(Mult_UNSIGNED_SIGNED_SLV);
//
//        PACKAGE.getDeclarations().add(Less_UNSIGNED_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(Less_SIGNED_SIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(Less_UNSIGNED_SIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(Less_SIGNED_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(Less_UNSIGNED_INTEGER_BOOLEAN);
//        PACKAGE.getDeclarations().add(Less_INTEGER_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(Less_SIGNED_INTEGER_BOOLEAN);
//        PACKAGE.getDeclarations().add(Less_INTEGER_SIGNED_BOOLEAN);
//
//        PACKAGE.getDeclarations().add(LessOrEqual_UNSIGNED_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(LessOrEqual_SIGNED_SIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(LessOrEqual_UNSIGNED_SIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(LessOrEqual_SIGNED_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(LessOrEqual_UNSIGNED_INTEGER_BOOLEAN);
//        PACKAGE.getDeclarations().add(LessOrEqual_INTEGER_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(LessOrEqual_SIGNED_INTEGER_BOOLEAN);
//        PACKAGE.getDeclarations().add(LessOrEqual_INTEGER_SIGNED_BOOLEAN);
//
//        PACKAGE.getDeclarations().add(Greater_UNSIGNED_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(Greater_SIGNED_SIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(Greater_UNSIGNED_SIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(Greater_SIGNED_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(Greater_UNSIGNED_INTEGER_BOOLEAN);
//        PACKAGE.getDeclarations().add(Greater_INTEGER_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(Greater_SIGNED_INTEGER_BOOLEAN);
//        PACKAGE.getDeclarations().add(Greater_INTEGER_SIGNED_BOOLEAN);
//
//        PACKAGE.getDeclarations().add(GreaterOrEqual_UNSIGNED_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(GreaterOrEqual_SIGNED_SIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(GreaterOrEqual_UNSIGNED_SIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(GreaterOrEqual_SIGNED_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(GreaterOrEqual_UNSIGNED_INTEGER_BOOLEAN);
//        PACKAGE.getDeclarations().add(GreaterOrEqual_INTEGER_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(GreaterOrEqual_SIGNED_INTEGER_BOOLEAN);
//        PACKAGE.getDeclarations().add(GreaterOrEqual_INTEGER_SIGNED_BOOLEAN);
//
//        PACKAGE.getDeclarations().add(Equal_UNSIGNED_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(Equal_SIGNED_SIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(Equal_UNSIGNED_SIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(Equal_SIGNED_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(Equal_UNSIGNED_INTEGER_BOOLEAN);
//        PACKAGE.getDeclarations().add(Equal_INTEGER_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(Equal_SIGNED_INTEGER_BOOLEAN);
//        PACKAGE.getDeclarations().add(Equal_INTEGER_SIGNED_BOOLEAN);
//
//        PACKAGE.getDeclarations().add(UnEqual_UNSIGNED_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(UnEqual_SIGNED_SIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(UnEqual_UNSIGNED_SIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(UnEqual_SIGNED_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(UnEqual_UNSIGNED_INTEGER_BOOLEAN);
//        PACKAGE.getDeclarations().add(UnEqual_INTEGER_UNSIGNED_BOOLEAN);
//        PACKAGE.getDeclarations().add(UnEqual_SIGNED_INTEGER_BOOLEAN);
//        PACKAGE.getDeclarations().add(UnEqual_INTEGER_SIGNED_BOOLEAN);
//
//        PACKAGE.getDeclarations().add(ShiftLeft_UNSIGNED_UNSIGNED_UNSIGNED);
//        PACKAGE.getDeclarations().add(ShiftLeft_SIGNED_UNSIGNED_SIGNED);
//        PACKAGE.getDeclarations().add(ShiftRight_UNSIGNED_UNSIGNED_UNSIGNED);
//        PACKAGE.getDeclarations().add(ShiftRight_SIGNED_UNSIGNED_SIGNED);

        PACKAGE.getDeclarations().add(CONV_INTEGER_INTEGER_INTEGER);
        PACKAGE.getDeclarations().add(CONV_INTEGER_UNSIGNED_INTEGER);
        PACKAGE.getDeclarations().add(CONV_INTEGER_SIGNED_INTEGER);
        PACKAGE.getDeclarations().add(CONV_INTEGER_STD_ULOGIC_SMALL_INT);

        PACKAGE.getDeclarations().add(CONV_UNSIGNED_INTEGER_INTEGER_UNSIGNED);
        PACKAGE.getDeclarations().add(CONV_UNSIGNED_UNSIGNED_INTEGER_UNSIGNED);
        PACKAGE.getDeclarations().add(CONV_UNSIGNED_SIGNED_INTEGER_UNSIGNED);
        PACKAGE.getDeclarations().add(CONV_UNSIGNED_STD_ULOGIC_INTEGER_UNSIGNED);

        PACKAGE.getDeclarations().add(CONV_SIGNED_INTEGER_INTEGER_UNSIGNED);
        PACKAGE.getDeclarations().add(CONV_SIGNED_UNSIGNED_INTEGER_UNSIGNED);
        PACKAGE.getDeclarations().add(CONV_SIGNED_SIGNED_INTEGER_UNSIGNED);
        PACKAGE.getDeclarations().add(CONV_SIGNED_STD_ULOGIC_INTEGER_UNSIGNED);

        PACKAGE.getDeclarations().add(CONV_SIGNED_INTEGER_INTEGER_SLV);
        PACKAGE.getDeclarations().add(CONV_SIGNED_UNSIGNED_INTEGER_SLV);
        PACKAGE.getDeclarations().add(CONV_SIGNED_SIGNED_INTEGER_SLV);
        PACKAGE.getDeclarations().add(CONV_SIGNED_STD_ULOGIC_INTEGER_SLV);

        PACKAGE.getDeclarations().add(EXT_SLV_INTEGER_SLV);
        PACKAGE.getDeclarations().add(SXT_SLV_INTEGER_SLV);
    }

    //Prevent instantiation.
    private StdLogicArith() {
    }
}

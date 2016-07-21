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
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.expression.Subtract;
import de.upb.hni.vmagic.libraryunit.PackageDeclaration;
import de.upb.hni.vmagic.literal.DecimalLiteral;
import de.upb.hni.vmagic.literal.EnumerationLiteral;
import de.upb.hni.vmagic.object.Constant;
import de.upb.hni.vmagic.object.Signal;
import de.upb.hni.vmagic.type.EnumerationType;
import de.upb.hni.vmagic.type.IndexSubtypeIndication;
import de.upb.hni.vmagic.type.RangeSubtypeIndication;
import de.upb.hni.vmagic.type.SubtypeIndication;
import de.upb.hni.vmagic.type.UnconstrainedArray;

/**
 * STDLOGIC1164 package wrapper.
 */
public class StdLogic1164 {

    /** Use clause for all declarations in this package. */
    public static final UseClause USE_CLAUSE =
            new UseClause("ieee.std_logic_1164.all");

    /** STD_ULOGIC type. */
    public static final EnumerationType STD_ULOGIC =
            new EnumerationType("std_ulogic", 'U', 'X', '0', '1', 'Z', 'W', 'L', 'H', '-');
    /** 'U' literal. */
    public static final EnumerationLiteral STD_ULOGIC_U = STD_ULOGIC.getLiterals().get(0);
    /** 'X' literal. */
    public static final EnumerationLiteral STD_ULOGIC_X = STD_ULOGIC.getLiterals().get(1);
    /** '0' literal. */
    public static final EnumerationLiteral STD_ULOGIC_0 = STD_ULOGIC.getLiterals().get(2);
    /** '1' literal. */
    public static final EnumerationLiteral STD_ULOGIC_1 = STD_ULOGIC.getLiterals().get(3);
    /** 'Z' literal. */
    public static final EnumerationLiteral STD_ULOGIC_Z = STD_ULOGIC.getLiterals().get(4);
    /** 'W' literal. */
    public static final EnumerationLiteral STD_ULOGIC_W = STD_ULOGIC.getLiterals().get(5);
    /** 'L' literal. */
    public static final EnumerationLiteral STD_ULOGIC_L = STD_ULOGIC.getLiterals().get(6);
    /** 'H' literal. */
    public static final EnumerationLiteral STD_ULOGIC_H = STD_ULOGIC.getLiterals().get(7);
    /** '-' literal. */
    public static final EnumerationLiteral STD_ULOGIC_DONT_CARE = STD_ULOGIC.getLiterals().get(8);
    /** STD_ULOGIC_VECTOR type. */
    public static final UnconstrainedArray STD_ULOGIC_VECTOR =
            new UnconstrainedArray("std_ulogic_vector", Standard.NATURAL, STD_ULOGIC);
    /**
     * Creates a STD_ULOGIC_VECTOR(width - 1 DOWNTO 0) subtype indication.
     * @param width the width
     * @return the subtype indication
     */
    public static SubtypeIndication STD_ULOGIC_VECTOR(int width) {
        return STD_ULOGIC_VECTOR(new Range(width - 1, Range.Direction.DOWNTO, 0));
    }
    /**
     * Creates a STD_ULOGIC_VECTOR(width - 1 DOWNTO 0) subtype indication.
     * @param width the width
     * @return the subtype indication
     */
    public static SubtypeIndication STD_ULOGIC_VECTOR(Expression width) {
        Expression from = new Subtract(width, new DecimalLiteral(1));
        Expression to = new DecimalLiteral(0);
        return STD_ULOGIC_VECTOR(new Range(from, Range.Direction.DOWNTO, to));
    }
    /**
     * Creates a STD_ULOGIC_VECTOR(range) subtype indication.
     * @param range the range
     * @return the subtype indication
     */
    public static SubtypeIndication STD_ULOGIC_VECTOR(Range range) {
        return new IndexSubtypeIndication(STD_ULOGIC_VECTOR, range);
    }

    //TODO: add resolve function
    /** STD_LOGIC type. */
    public static final Subtype STD_LOGIC =
            new Subtype("std_logic", STD_ULOGIC);
    /** 'U' literal. */
    public static final EnumerationLiteral STD_LOGIC_U = STD_ULOGIC_U;
    /** 'X' literal. */
    public static final EnumerationLiteral STD_LOGIC_X = STD_ULOGIC_X;
    /** '0' literal. */
    public static final EnumerationLiteral STD_LOGIC_0 = STD_ULOGIC_0;
    /** '1' literal. */
    public static final EnumerationLiteral STD_LOGIC_1 = STD_ULOGIC_1;
    /** 'Z' literal. */
    public static final EnumerationLiteral STD_LOGIC_Z = STD_ULOGIC_Z;
    /** 'W' literal. */
    public static final EnumerationLiteral STD_LOGIC_W = STD_ULOGIC_W;
    /** 'L' literal. */
    public static final EnumerationLiteral STD_LOGIC_L = STD_ULOGIC_L;
    /** 'H' literal. */
    public static final EnumerationLiteral STD_LOGIC_H = STD_ULOGIC_H;
    /** '-' literal. */
    public static final EnumerationLiteral STD_LOGIC_DONT_CARE = STD_ULOGIC_DONT_CARE;
    /** STD_LOGIC_VECTOR type. */
    public static final UnconstrainedArray STD_LOGIC_VECTOR =
            new UnconstrainedArray("std_logic_vector", Standard.NATURAL, STD_LOGIC);
    /**
     * Creates an STD_LOGIC_VECTOR(width - 1 DOWNTO 0) subtype indication.
     * @param width the width
     * @return the subtype indication
     */
    public static SubtypeIndication STD_LOGIC_VECTOR(int width) {
        return STD_LOGIC_VECTOR(new Range(width - 1, Range.Direction.DOWNTO, 0));
    }
    /**
     * Creates an STD_LOGIC_VECTOR(width - 1 DOWNTO 0) subtype indication.
     * @param width the width
     * @return the subtype indication
     */
    public static SubtypeIndication STD_LOGIC_VECTOR(Expression width) {
        Expression from = new Subtract(width, new DecimalLiteral(1));
        Expression to = new DecimalLiteral(0);
        return STD_LOGIC_VECTOR(new Range(from, Range.Direction.DOWNTO, to));
    }
    /**
     * Creates an STD_LOGIC_VECTOR(range) subtype indication.
     * @param range the range
     * @return the subtype indication
     */
    public static SubtypeIndication STD_LOGIC_VECTOR(Range range) {
        return new IndexSubtypeIndication(STD_LOGIC_VECTOR, range);
    }

    /** X01 subtype. */
    //TODO: add resolve function
    public static final Subtype X01 = new Subtype("X01",
            new RangeSubtypeIndication(STD_ULOGIC,
            new Range(STD_ULOGIC_X, Range.Direction.TO, STD_LOGIC_1)));

    /** X01Z subtype. */
    //TODO: add resolve function
    public static final Subtype X01Z = new Subtype("X01Z",
            new RangeSubtypeIndication(STD_ULOGIC,
            new Range(STD_ULOGIC_X, Range.Direction.TO, STD_LOGIC_Z)));

    /** UX01 subtype. */
    //TODO: add resolve function
    public static final Subtype UX01 = new Subtype("UX01",
            new RangeSubtypeIndication(STD_ULOGIC,
            new Range(STD_ULOGIC_U, Range.Direction.TO, STD_LOGIC_1)));

    /** UX01Z subtype. */
    //TODO: add resolve function
    public static final Subtype UX01Z = new Subtype("UX01Z",
            new RangeSubtypeIndication(STD_ULOGIC,
            new Range(STD_ULOGIC_U, Range.Direction.TO, STD_LOGIC_Z)));

    /** TO_BIT function. */
    //TODO: add overloaded versions
    public static final FunctionDeclaration TO_BIT = new FunctionDeclaration("TO_BIT", Standard.BIT,
            new Constant("s", STD_ULOGIC), new Constant("xmap", Standard.BIT, Standard.BIT_0));
    /** TO_BITVECTOR function. */
    public static final FunctionDeclaration TO_BITVECTOR = new FunctionDeclaration("TO_BITVECTOR", Standard.BIT_VECTOR,
            new Constant("s", STD_ULOGIC_VECTOR), new Constant("xmap", Standard.BIT, Standard.BIT_0));
    /** TO_STDULOGIC function. */
    public static final FunctionDeclaration TO_STDULOGIC = new FunctionDeclaration("TO_STDULOGIC", STD_ULOGIC,
            new Constant("b", Standard.BIT));
    /** TO_STDULOGICVECTOR function. */
    public static final FunctionDeclaration TO_STDLOGICVECTOR = new FunctionDeclaration("TO_STDLOGICVECTOR", STD_LOGIC_VECTOR,
            new Constant("s", STD_ULOGIC_VECTOR));
    /** TO_X01 function. */
    public static final FunctionDeclaration TO_X01 = new FunctionDeclaration("TO_X01", X01,
            new Constant("s", STD_ULOGIC));
    /** TO_X01Z function. */
    public static final FunctionDeclaration TO_X01Z = new FunctionDeclaration("TO_X01Z", X01Z,
            new Constant("s", STD_ULOGIC));
    /** TO_UX01 function. */
    public static final FunctionDeclaration TO_UX01 = new FunctionDeclaration("TO_UX01", UX01,
            new Constant("s", STD_ULOGIC));
    /** RISING_EDGE function. */
    public static final FunctionDeclaration RISING_EDGE = new FunctionDeclaration("RISING_EDGE", Standard.BOOLEAN,
            new Signal("s", STD_ULOGIC));
    /** FALLING_EDGE function. */
    public static final FunctionDeclaration FALLING_EDGE = new FunctionDeclaration("FALLING_EDGE", Standard.BOOLEAN,
            new Signal("s", STD_ULOGIC));
    /** IS_X function. */
    public static final FunctionDeclaration IS_X = new FunctionDeclaration("IS_X", Standard.BOOLEAN,
            new Constant("s", STD_ULOGIC));

    /** STD_LOGIC_1164 package. */
    public static final PackageDeclaration PACKAGE = new PackageDeclaration("std_logic_1164");

    static {
        PACKAGE.getDeclarations().add(STD_ULOGIC);
        PACKAGE.getDeclarations().add(STD_ULOGIC_VECTOR);
        PACKAGE.getDeclarations().add(STD_LOGIC);
        PACKAGE.getDeclarations().add(STD_LOGIC_VECTOR);
        PACKAGE.getDeclarations().add(X01);
        PACKAGE.getDeclarations().add(X01Z);
        PACKAGE.getDeclarations().add(UX01);
        PACKAGE.getDeclarations().add(UX01Z);
        PACKAGE.getDeclarations().add(TO_BIT);
        PACKAGE.getDeclarations().add(TO_BITVECTOR);
        PACKAGE.getDeclarations().add(TO_STDLOGICVECTOR);
        PACKAGE.getDeclarations().add(TO_STDULOGIC);
        PACKAGE.getDeclarations().add(TO_UX01);
        PACKAGE.getDeclarations().add(TO_X01);
        PACKAGE.getDeclarations().add(TO_X01Z);
        PACKAGE.getDeclarations().add(RISING_EDGE);
        PACKAGE.getDeclarations().add(FALLING_EDGE);
        PACKAGE.getDeclarations().add(IS_X);
    }

    //Prevent instantiation.
    private StdLogic1164() {
    }
}

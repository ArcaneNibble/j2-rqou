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
import de.upb.hni.vmagic.declaration.FunctionDeclaration;
import de.upb.hni.vmagic.libraryunit.UseClause;
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.expression.Subtract;
import de.upb.hni.vmagic.libraryunit.PackageDeclaration;
import de.upb.hni.vmagic.literal.DecimalLiteral;
import de.upb.hni.vmagic.object.Constant;
import de.upb.hni.vmagic.type.IndexSubtypeIndication;
import de.upb.hni.vmagic.type.SubtypeIndication;
import de.upb.hni.vmagic.type.UnconstrainedArray;

/**
 * NUMERIC_STD wrapper class.
 */
public class NumericStd {

    /** Use clause for all declarations in this package. */
    public static final UseClause USE_CLAUSE =
            new UseClause("ieee.numeric_std.all");
    /** UNSIGNED type. */
    public static final UnconstrainedArray UNSIGNED =
            new UnconstrainedArray("unsigned", Standard.NATURAL, StdLogic1164.STD_LOGIC);

    /**
     * Creates an UNSIGNED(width - 1 DOWNTO 0) subtype indication.
     * @param width the width
     * @return the subtype indication
     */
    public static SubtypeIndication UNSIGNED(int width) {
        return UNSIGNED(new Range(width - 1, Range.Direction.DOWNTO, 0));
    }

    /**
     * Creates an UNSIGNED(width - 1 DOWNTO 0) subtype indication.
     * @param width the width
     * @return the subtype indication
     */
    public static SubtypeIndication UNSIGNED(Expression width) {
        Expression from = new Subtract(width, new DecimalLiteral(1));
        Expression to = new DecimalLiteral(0);
        return UNSIGNED(new Range(from, Range.Direction.DOWNTO, to));
    }

    /**
     * Creates an UNSIGNED(range) subtype indication.
     * @param range the range
     * @return the subtype indication
     */
    public static SubtypeIndication UNSIGNED(Range range) {
        return new IndexSubtypeIndication(UNSIGNED, range);
    }
    /** SIGNED type. */
    public static final UnconstrainedArray SIGNED =
            new UnconstrainedArray("signed", Standard.NATURAL, StdLogic1164.STD_LOGIC);

    /**
     * Creates a SIGNED(width - 1 DOWNTO 0) subtype indication.
     * @param width the width
     * @return the subtype indication
     */
    public static SubtypeIndication SIGNED(int width) {
        return SIGNED(new Range(width - 1, Range.Direction.DOWNTO, 0));
    }

    /**
     * Creates a SIGNED(width - 1 DOWNTO 0) subtype indication.
     * @param width the width
     * @return the subtype indication
     */
    public static SubtypeIndication SIGNED(Expression width) {
        Expression from = new Subtract(width, new DecimalLiteral(1));
        Expression to = new DecimalLiteral(0);
        return SIGNED(new Range(from, Range.Direction.DOWNTO, to));
    }

    /**
     * Creates a SIGNED(range) subtype indication.
     * @param range the range
     * @return the subtype indication
     */
    public static SubtypeIndication SIGNED(Range range) {
        return new IndexSubtypeIndication(SIGNED, range);
    }
    /**
     * SHIFT_LEFT function.
     */
    //TODO: add overloaded versions
    public static final FunctionDeclaration SHIFT_LEFT = new FunctionDeclaration("SHIFT_LEFT", UNSIGNED, new Constant("ARG", UNSIGNED), new Constant("COUNT", Standard.NATURAL));
    /**
     * SHIFT_RIGHT function.
     */
    //TODO: add overloaded versions
    public static final FunctionDeclaration SHIFT_RIGHT = new FunctionDeclaration("SHIFT_RIGHT", UNSIGNED, new Constant("ARG", UNSIGNED), new Constant("COUNT", Standard.NATURAL));
    /**
     * ROTATE_LEFT function.
     */
    //TODO: add overloaded versions
    public static final FunctionDeclaration ROTATE_LEFT = new FunctionDeclaration("ROTATE_LEFT", UNSIGNED, new Constant("ARG", UNSIGNED), new Constant("COUNT", Standard.NATURAL));
    /**
     * ROTATE_RIGHT function.
     */
    //TODO: add overloaded versions
    public static final FunctionDeclaration ROTATE_RIGHT = new FunctionDeclaration("ROTATE_RIGHT", UNSIGNED, new Constant("ARG", UNSIGNED), new Constant("COUNT", Standard.NATURAL));
    /**
     * RESIZE function.
     */
    //TODO: add overloaded version
    public static final FunctionDeclaration RESIZE = new FunctionDeclaration("RESIZE", UNSIGNED, new Constant("ARG", UNSIGNED), new Constant("NEW_SIZE", Standard.NATURAL));
    /**
     * TO_INTEGER function.
     */
    //TODO: add overloaded version
    public static final FunctionDeclaration TO_INTEGER = new FunctionDeclaration("TO_INTEGER", Standard.NATURAL, new Constant("ARG", UNSIGNED));
    /**
     * TO_UNSIGNED function.
     */
    //TODO: add overloaded version
    public static final FunctionDeclaration TO_UNSIGNED = new FunctionDeclaration("TO_UNSIGNED", UNSIGNED, new Constant("ARG", Standard.NATURAL), new Constant("SIZE", Standard.NATURAL));
    /**
     * TO_SIGNED function.
     */
    //TODO: add overloaded version
    public static final FunctionDeclaration TO_SIGNED = new FunctionDeclaration("TO_SIGNED", SIGNED, new Constant("ARG", Standard.INTEGER), new Constant("SIZE", Standard.NATURAL));
    /** NUMERIC_STD package. */
    public static final PackageDeclaration PACKAGE = new PackageDeclaration("numeric_std");

    static {
        PACKAGE.getDeclarations().add(UNSIGNED);
        PACKAGE.getDeclarations().add(SIGNED);
        PACKAGE.getDeclarations().add(SHIFT_LEFT);
        PACKAGE.getDeclarations().add(SHIFT_RIGHT);
        PACKAGE.getDeclarations().add(ROTATE_LEFT);
        PACKAGE.getDeclarations().add(ROTATE_RIGHT);
        PACKAGE.getDeclarations().add(RESIZE);
        PACKAGE.getDeclarations().add(TO_INTEGER);
        PACKAGE.getDeclarations().add(TO_SIGNED);
        PACKAGE.getDeclarations().add(TO_UNSIGNED);
    }

    /**
     * Prevent instantiation.
     */
    private NumericStd() {
    }
}

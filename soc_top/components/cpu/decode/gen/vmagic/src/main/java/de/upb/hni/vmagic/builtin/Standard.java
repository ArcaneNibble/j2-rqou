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
import de.upb.hni.vmagic.declaration.Subtype;
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.expression.Subtract;
import de.upb.hni.vmagic.libraryunit.PackageDeclaration;
import de.upb.hni.vmagic.literal.DecimalLiteral;
import de.upb.hni.vmagic.literal.EnumerationLiteral;
import de.upb.hni.vmagic.type.EnumerationType;
import de.upb.hni.vmagic.type.IndexSubtypeIndication;
import de.upb.hni.vmagic.type.IntegerType;
import de.upb.hni.vmagic.type.PhysicalType;
import de.upb.hni.vmagic.type.RangeSubtypeIndication;
import de.upb.hni.vmagic.type.SubtypeIndication;
import de.upb.hni.vmagic.type.UnconstrainedArray;

/**
 * STANDARD package wrapper class.
 */
public class Standard {

    /** BOOLEAN type. */
    public static final EnumerationType BOOLEAN =
            new EnumerationType("BOOLEAN", "FALSE", "TRUE");
    /** FALSE literal. */
    public static final EnumerationLiteral BOOLEAN_FALSE = BOOLEAN.getLiterals().get(0);
    /** TRUE literal. */
    public static final EnumerationLiteral BOOLEAN_TRUE = BOOLEAN.getLiterals().get(1);
    /** BIT type. */
    public static final EnumerationType BIT =
            new EnumerationType("BIT", '0', '1');
    /** '0' literal. */
    public static final EnumerationLiteral BIT_0 = BIT.getLiterals().get(0);
    /** '1' literal. */
    public static final EnumerationLiteral BIT_1 = BIT.getLiterals().get(1);
    /** CHARACTER type. */
    //TODO: add missing enumeration literals
    public static final EnumerationType CHARACTER =
            new EnumerationType("CHARACTER",
            "NUL", "SOH", "STX", "ETX", "EOT", "ENQ", "ACK", "BEL",
            "BS", "HT", "LF", "VT", "FF", "CR", "SO", "SI",
            "DLE", "DC1", "DC2", "DC3", "DC4", "NAK", "SYN", "ETB",
            "CAN", "EM", "SUB", "ESC", "FSP", "GSP", "RSP", "USP",
            //TODO: add characters
            "DEL",
            "C128", "C129", "C130", "C131", "C132", "C133", "C134", "C135",
            "C136", "C137", "C138", "C139", "C140", "C141", "C142", "C143",
            "C144", "C145", "C146", "C147", "C148", "C149", "C150", "C151",
            "C152", "C153", "C154", "C155", "C156", "C157", "C158", "C159" //TODO: add characters
            );
    /** SEVERITY_LEVEL type. */
    public static final EnumerationType SEVERITY_LEVEL =
            new EnumerationType("SEVERITY_LEVEL", "NOTE", "WARNING", "ERROR", "FAILURE");
    /** NOTE literal. */
    public static final EnumerationLiteral SEVERITY_LEVEL_NOTE = SEVERITY_LEVEL.getLiterals().get(0);
    /** WARNING literal. */
    public static final EnumerationLiteral SEVERITY_LEVEL_WARNING = SEVERITY_LEVEL.getLiterals().get(1);
    /** ERROR literal. */
    public static final EnumerationLiteral SEVERITY_LEVEL_ERROR = SEVERITY_LEVEL.getLiterals().get(2);
    /** FAILURE literal. */
    public static final EnumerationLiteral SEVERITY_LEVEL_FAILURE = SEVERITY_LEVEL.getLiterals().get(3);
    private static final Range INTEGER_RANGE =
            new Range(Integer.MIN_VALUE, Range.Direction.TO, Integer.MAX_VALUE);
    /** INTEGER type. */
    public static final IntegerType INTEGER =
            new IntegerType("INTEGER", INTEGER_RANGE);
    /** REAL type. */
    //TODO: fix range and type
    public static final IntegerType REAL = new IntegerType("REAL", INTEGER_RANGE);
    /** TIME type. */
    //TODO: replace with correct type
    public static final PhysicalType TIME = new PhysicalType("TIME", INTEGER_RANGE, "fs");

    static {
        TIME.createUnit("ps", 1000, "fs");
        TIME.createUnit("ns", 1000, "ps");
        TIME.createUnit("us", 1000, "ns");
        TIME.createUnit("ms", 1000, "us");
        TIME.createUnit("sec", 1000, "ms");
        TIME.createUnit("min", 60, "sec");
        TIME.createUnit("hr", 60, "min");
    }
    /** DELAY_LENGTH type. */
    //TODO: use correct range
    public static final Subtype DELAY_LENGTH = new Subtype("DELAY_LENGTH",
            new RangeSubtypeIndication(TIME, new Range(0, Range.Direction.TO, 1000)));
    /** NOW function. */
    //TODO: set pure
    public static final FunctionDeclaration NOW = new FunctionDeclaration("NOW", DELAY_LENGTH);
    //TODO: replace Integer.MAX_VALUE by "INTEGER'HIGH"
    private static final Range NATURAL_RANGE = new Range(0, Range.Direction.TO, Integer.MAX_VALUE);
    /** NATURAL type. */
    public static final Subtype NATURAL =
            new Subtype("NATURAL", new RangeSubtypeIndication(INTEGER, NATURAL_RANGE));
    //TODO: replace Integer.MAX_VALUE by "INTEGER'HIGH"
    private static final Range POSITIVE_RANGE = new Range(1, Range.Direction.TO, Integer.MAX_VALUE);
    /** POSITIVE type. */
    public static final Subtype POSITIVE =
            new Subtype("POSITIVE", new RangeSubtypeIndication(INTEGER, POSITIVE_RANGE));
    /** STRING type. */
    public static final UnconstrainedArray STRING =
            new UnconstrainedArray("STRING", CHARACTER, POSITIVE);
    /** BIT_VECTOR type. */
    public static final UnconstrainedArray BIT_VECTOR =
            new UnconstrainedArray("BIT_VECTOR", BIT, NATURAL);

    /**
     * Creates a BIT_VECTOR(width -1 DOWNTO 0) subtype indication.
     * @param width the width
     * @return the subtype indication
     */
    public static SubtypeIndication BIT_VECTOR(int width) {
        return BIT_VECTOR(new Range(width - 1, Range.Direction.DOWNTO, 0));
    }

    /**
     * Creates a BIT_VECTOR(width -1 DOWNTO 0) subtype indication.
     * @param width the width
     * @return the subtype indication
     */
    public static SubtypeIndication BIT_VECTOR(Expression width) {
        Expression from = new Subtract(width, new DecimalLiteral(1));
        Expression to = new DecimalLiteral(0);
        return BIT_VECTOR(new Range(from, Range.Direction.DOWNTO, to));
    }

    /**
     * Creates a BIT_VECTOR(range) subtype indication.
     * @param range the range
     * @return the subtype indication
     */
    public static SubtypeIndication BIT_VECTOR(Range range) {
        return new IndexSubtypeIndication(BIT_VECTOR, range);
    }
    /** FILE_OPEN_KIND type. */
    public static final EnumerationType FILE_OPEN_KIND =
            new EnumerationType("FILE_OPEN_KIND", "READ_MODE", "WRITE_MODE", "APPEND_MODE");
    /** READ_MODE literal. */
    public static final EnumerationLiteral FILE_OPEN_KIND_READ_MODE = FILE_OPEN_KIND.getLiterals().get(0);
    /** WRITE_MODE literal. */
    public static final EnumerationLiteral FILE_OPEN_KIND_WRITE_MODE = FILE_OPEN_KIND.getLiterals().get(1);
    /** APPEND_MODE literal. */
    public static final EnumerationLiteral FILE_OPEN_KIND_APPEND_MODE = FILE_OPEN_KIND.getLiterals().get(2);
    /** FILE_OPEN_STATUS type. */
    public static final EnumerationType FILE_OPEN_STATUS =
            new EnumerationType("FILE_OPEN_STATUS", "OPEN_OK", "STATUS_ERROR", "NAME_ERROR", "MODE_ERROR");
    /** OPEN_OK literal. */
    public static final EnumerationLiteral FILE_OPEN_STATUS_OPEN_OK = FILE_OPEN_STATUS.getLiterals().get(0);
    /** STATUS_ERROR literal. */
    public static final EnumerationLiteral FILE_OPEN_STATUS_STATUS_ERROR = FILE_OPEN_STATUS.getLiterals().get(1);
    /** NAME_ERROR literal. */
    public static final EnumerationLiteral FILE_OPEN_STATUS_NAME_ERROR = FILE_OPEN_STATUS.getLiterals().get(2);
    /** MODE_ERROR literal. */
    public static final EnumerationLiteral FILE_OPEN_STATUS_MODE_ERROR = FILE_OPEN_STATUS.getLiterals().get(3);
    //TODO: add FOREIGN attribute
    /** STANDARD package. */
    public static final PackageDeclaration PACKAGE = new PackageDeclaration("standard");

    static {
        PACKAGE.getDeclarations().add(BOOLEAN);
        PACKAGE.getDeclarations().add(BIT);
        PACKAGE.getDeclarations().add(CHARACTER);
        PACKAGE.getDeclarations().add(SEVERITY_LEVEL);
        PACKAGE.getDeclarations().add(INTEGER);
        PACKAGE.getDeclarations().add(REAL);
        PACKAGE.getDeclarations().add(TIME);
        PACKAGE.getDeclarations().add(DELAY_LENGTH);
        PACKAGE.getDeclarations().add(NOW);
        PACKAGE.getDeclarations().add(NATURAL);
        PACKAGE.getDeclarations().add(POSITIVE);
        PACKAGE.getDeclarations().add(STRING);
        PACKAGE.getDeclarations().add(BIT_VECTOR);
        PACKAGE.getDeclarations().add(FILE_OPEN_KIND);
        PACKAGE.getDeclarations().add(FILE_OPEN_STATUS);
    }

    //Prevent instantiation.
    private Standard() {
    }
}

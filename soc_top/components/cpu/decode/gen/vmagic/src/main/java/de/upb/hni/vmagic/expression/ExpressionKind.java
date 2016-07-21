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

package de.upb.hni.vmagic.expression;

import de.upb.hni.vmagic.output.OutputEnum;

/**
 * Expression kind.
 */
//TODO: make package private?
public enum ExpressionKind implements OutputEnum {
    ABS("abs"),
    AND("and"),
    MOD("mod"),
    NAND("nand"),
    NOR("nor"),
    NOT("not"),
    OR("or"),
    REM("rem"),
    ROL("rol"),
    ROR("ror"),
    SLA("sla"),
    SLL("sll"),
    SRA("sra"),
    SRL("srl"),
    XNOR("xnor"),
    XOR("xor"),
    EQUALS("="),
    NOT_EQUALS("/="),
    LESS_THAN("<"),
    LESS_EQUALS("<="),
    GREATER_THAN(">"),
    GREATER_EQUALS(">="),
    PLUS("+"),
    MINUS("-"),
    CONCATENATE("&"),
    MULTIPLY("*"),
    DIVIDE("/"),
    POW("**");
    private final String opLower;
    private final String opUpper;

    ExpressionKind(String op) {
        this.opLower = op;
        this.opUpper = op.toUpperCase();
    }

    public String getLowerCase() {
        return opLower;
    }

    public String getUpperCase() {
        return opUpper;
    }
}

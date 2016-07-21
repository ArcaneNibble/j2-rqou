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

package de.upb.hni.vmagic.expression.parser;

import de.upb.hni.vmagic.expression.Abs;
import de.upb.hni.vmagic.expression.Add;
import de.upb.hni.vmagic.expression.And;
import de.upb.hni.vmagic.expression.Concatenate;
import de.upb.hni.vmagic.expression.Divide;
import de.upb.hni.vmagic.expression.Equals;
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.expression.GreaterEquals;
import de.upb.hni.vmagic.expression.GreaterThan;
import de.upb.hni.vmagic.expression.LessEquals;
import de.upb.hni.vmagic.expression.LessThan;
import de.upb.hni.vmagic.expression.Mod;
import de.upb.hni.vmagic.expression.Multiply;
import de.upb.hni.vmagic.expression.Nand;
import de.upb.hni.vmagic.expression.Nor;
import de.upb.hni.vmagic.expression.Not;
import de.upb.hni.vmagic.expression.NotEquals;
import de.upb.hni.vmagic.expression.Or;
import de.upb.hni.vmagic.expression.Pow;
import de.upb.hni.vmagic.expression.Rem;
import de.upb.hni.vmagic.expression.Rol;
import de.upb.hni.vmagic.expression.Ror;
import de.upb.hni.vmagic.expression.Sla;
import de.upb.hni.vmagic.expression.Sll;
import de.upb.hni.vmagic.expression.Sra;
import de.upb.hni.vmagic.expression.Subtract;
import de.upb.hni.vmagic.expression.Xnor;
import de.upb.hni.vmagic.expression.Xor;

/**
 * Expression parser token.
 */
enum Token {

    //keywords
    ABS {

        Expression create(Expression left, Expression right) {
            return new Abs(left);
        }
    },
    AND {

        Expression create(Expression left, Expression right) {
            return new And(left, right);
        }
    },
    MOD {

        Expression create(Expression left, Expression right) {
            return new Mod(left, right);
        }
    },
    NAND {

        Expression create(Expression left, Expression right) {
            return new Nand(left, right);
        }
    },
    NOR {

        Expression create(Expression left, Expression right) {
            return new Nor(left, right);
        }
    },
    NOT {

        Expression create(Expression left, Expression right) {
            return new Not(left);
        }
    },
    OR {

        Expression create(Expression left, Expression right) {
            return new Or(left, right);
        }
    },
    REM {

        Expression create(Expression left, Expression right) {
            return new Rem(left, right);
        }
    },
    ROL {

        Expression create(Expression left, Expression right) {
            return new Rol(left, right);
        }
    },
    ROR {

        Expression create(Expression left, Expression right) {
            return new Ror(left, right);
        }
    },
    SLA {

        Expression create(Expression left, Expression right) {
            return new Sla(left, right);
        }
    },
    SLL {

        Expression create(Expression left, Expression right) {
            return new Sll(left, right);
        }
    },
    SRA {

        Expression create(Expression left, Expression right) {
            return new Sra(left, right);
        }
    },
    SRL {

        Expression create(Expression left, Expression right) {
            return new Sla(left, right);
        }
    },
    XNOR {

        Expression create(Expression left, Expression right) {
            return new Xnor(left, right);
        }
    },
    XOR {

        Expression create(Expression left, Expression right) {
            return new Xor(left, right);
        }
    },
    //symbols
    AMPERSAND {

        Expression create(Expression left, Expression right) {
            return new Concatenate(left, right);
        }
    },
    MUL {

        Expression create(Expression left, Expression right) {
            return new Multiply(left, right);
        }
    },
    DOUBLESTAR {

        Expression create(Expression left, Expression right) {
            return new Pow(left, right);
        }
    },
    LT {

        Expression create(Expression left, Expression right) {
            return new LessThan(left, right);
        }
    },
    LE {

        Expression create(Expression left, Expression right) {
            return new LessEquals(left, right);
        }
    },
    GT {

        Expression create(Expression left, Expression right) {
            return new GreaterThan(left, right);
        }
    },
    GE {

        Expression create(Expression left, Expression right) {
            return new GreaterEquals(left, right);
        }
    },
    NEQ {

        Expression create(Expression left, Expression right) {
            return new NotEquals(left, right);
        }
    },
    DBLQUOTE {

        Expression create(Expression left, Expression right) {
            return null;
        }
    },
    DIV {

        Expression create(Expression left, Expression right) {
            return new Divide(left, right);
        }
    },
    PLUS {

        Expression create(Expression left, Expression right) {
            return new Add(left, right);
        }
    },
    MINUS {

        Expression create(Expression left, Expression right) {
            return new Subtract(left, right);
        }
    },
    EQ {

        Expression create(Expression left, Expression right) {
            return new Equals(left, right);
        }
    },
    LPAREN {

        Expression create(Expression left, Expression right) {
            return null;
        }
    },
    RPAREN {

        Expression create(Expression left, Expression right) {
            return null;
        }
    },
    //literals
    PLACEHOLDER {

        Expression create(Expression left, Expression right) {
            return null;
        }
    },
    BINARY_BIT_STRING_LITERAL {

        Expression create(Expression left, Expression right) {
            return null;
        }
    },
    HEX_BIT_STRING_LITERAL {

        Expression create(Expression left, Expression right) {
            return null;
        }
    },
    OCTAL_BIT_STRING_LITERAL {

        Expression create(Expression left, Expression right) {
            return null;
        }
    },
    STRING_LITERAL {

        Expression create(Expression left, Expression right) {
            return null;
        }
    },
    CHARACTER_LITERAL {

        Expression create(Expression left, Expression right) {
            return null;
        }
    },
    DECIMAL_LITERAL {

        Expression create(Expression left, Expression right) {
            return null;
        }
    },
    BASED_LITERAL {

        Expression create(Expression left, Expression right) {
            return null;
        }
    },
    //whitespace
    WHITESPACE {

        Expression create(Expression left, Expression right) {
            return null;
        }
    },
    //virtual tokens
    EOF {

        Expression create(Expression left, Expression right) {
            return null;
        }
    },
    ERROR {

        Expression create(Expression left, Expression right) {
            return null;
        }
    };

    abstract Expression create(Expression left, Expression right);
}

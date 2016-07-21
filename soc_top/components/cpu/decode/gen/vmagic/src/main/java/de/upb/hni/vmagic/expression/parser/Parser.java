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
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.expression.Minus;
import de.upb.hni.vmagic.expression.Not;
import de.upb.hni.vmagic.expression.Parentheses;
import de.upb.hni.vmagic.expression.Plus;
import de.upb.hni.vmagic.literal.BasedLiteral;
import de.upb.hni.vmagic.literal.BinaryLiteral;
import de.upb.hni.vmagic.literal.CharacterLiteral;
import de.upb.hni.vmagic.literal.DecimalLiteral;
import de.upb.hni.vmagic.literal.HexLiteral;
import de.upb.hni.vmagic.literal.OctalLiteral;
import de.upb.hni.vmagic.literal.StringLiteral;
import java.util.List;

/**
 * Expression parser parser.
 */
class Parser {

    private final Lexer input;
    private final List<Expression> parameters;
    private Token lookahead;
    private static final Token[][] binaryHierarchy = {
        {Token.AND, Token.OR, Token.NAND, Token.NOR, Token.XOR, Token.XNOR},
        {Token.EQ, Token.NEQ, Token.LT, Token.LE, Token.GT, Token.GE},
        {Token.SLL, Token.SRL, Token.SLA, Token.SRA, Token.ROL, Token.ROR},
        {Token.PLUS, Token.MINUS, Token.AMPERSAND},
        {Token.MUL, Token.DIV, Token.MOD, Token.REM},
        {Token.DOUBLESTAR}
    };
    private static final int SIMPLE_EXPRESSION_LEVEL = 3;

    public Parser(Lexer input, List<Expression> parameters) {
        this.input = input;
        this.parameters = parameters;

        lookahead = input.nextToken();
        if (lookahead == Token.ERROR) {
            throw new IllegalArgumentException("lexical error");
        }
    }

    private String accept(Token t) {
        if (lookahead != t) {
            throw new IllegalArgumentException("illegal token " + lookahead.toString() + " (expecting " + t.toString() + ")");
        }

        String text = input.getTokenText();

        lookahead = input.nextToken();
        if (lookahead == Token.ERROR) {
            throw new IllegalArgumentException("lexical error");
        }
        return text;
    }

    private Token getBinaryType(int level) {
        for (Token token : binaryHierarchy[level]) {
            if (token == lookahead) {
                return token;
            }
        }

        return null;
    }

    public Expression getExpression() {
        Expression e = expression(0);

        if (lookahead != Token.EOF) {
            throw new IllegalArgumentException("extraneous output at end of expression template (" + lookahead.toString() + ")");
        }

        return e;
    }

    private Expression expression(int level) {
        if (level < binaryHierarchy.length) {
            Expression ret;

            if (level == SIMPLE_EXPRESSION_LEVEL) {
                if (lookahead == Token.PLUS) {
                    accept(Token.PLUS);
                    ret = new Plus(expression(level + 1));
                } else if (lookahead == Token.MINUS) {
                    accept(Token.MINUS);
                    ret = new Minus(expression(level + 1));
                } else {
                    ret = expression(level + 1);
                }
            } else {
                ret = expression(level + 1);
            }

            for (;;) {
                Token type = getBinaryType(level);

                if (type != null) {
                    accept(type);
                    ret = type.create(ret, expression(level + 1));
                } else {
                    return ret;
                }
            }
        } else {
            return factor();
        }
    }

    private Expression factor() {
        if (lookahead == Token.ABS) {
            accept(Token.ABS);
            return new Abs(primary());
        } else if (lookahead == Token.NOT) {
            accept(Token.NOT);
            return new Not(primary());
        } else {
            return primary();
        }
    }

    private Expression primary() {
        String text;

        switch (lookahead) {
            case PLACEHOLDER:
                text = accept(Token.PLACEHOLDER);
                int index;
                try {
                    index = Integer.parseInt(text.substring(1)) - 1;
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("illegal placeholder " + text);
                }
                if (index < 0 || index > parameters.size() - 1) {
                    throw new IllegalArgumentException("illegal placeholder " + text);
                }
                return parameters.get(index);

            case LPAREN:
                accept(Token.LPAREN);
                Expression expr = new Parentheses(expression(0));
                accept(Token.RPAREN);
                return expr;

            case DECIMAL_LITERAL:
                text = accept(Token.DECIMAL_LITERAL);
                return new DecimalLiteral(text);

            case BASED_LITERAL:
                text = accept(Token.BASED_LITERAL);
                return new BasedLiteral(text);

            case STRING_LITERAL:
                text = accept(Token.STRING_LITERAL);
                return new StringLiteral(text.substring(1, text.length() - 1));

            case CHARACTER_LITERAL:
                text = accept(Token.CHARACTER_LITERAL);
                return new CharacterLiteral(text.charAt(1));

            case BINARY_BIT_STRING_LITERAL:
                text = accept(Token.BINARY_BIT_STRING_LITERAL);
                return new BinaryLiteral(text.substring(2, text.length() - 1));

            case HEX_BIT_STRING_LITERAL:
                text = accept(Token.HEX_BIT_STRING_LITERAL);
                return new HexLiteral(text.substring(2, text.length() - 1));

            case OCTAL_BIT_STRING_LITERAL:
                text = accept(Token.OCTAL_BIT_STRING_LITERAL);
                return new OctalLiteral(text.substring(2, text.length() - 1));

            default:
                String errorToken = lookahead.toString();
                accept(lookahead);
                throw new IllegalArgumentException("illegal primary " + errorToken);
        }
    }
}

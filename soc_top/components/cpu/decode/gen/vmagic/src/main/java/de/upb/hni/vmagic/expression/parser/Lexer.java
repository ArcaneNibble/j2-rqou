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

/**
 * Expression parser lexer.
 */
class Lexer {

    private static final char EOF = 0;
    private final String input;
    private int currentIndex;
    private StringBuilder tokenText;

    public Lexer(String input) {
        this.input = input.toLowerCase();
    }

    private char consume() {
        if (currentIndex < input.length()) {
            char c = input.charAt(currentIndex++);
            tokenText.append(c);
            return c;
        } else {
            return EOF;
        }
    }

    public Token nextToken() {
        Token token;

        while ((token = nextTokenWithWhitespace()) == Token.WHITESPACE) {
            //do nothing
        }

        return token;
    }

    private Token nextTokenWithWhitespace() {
        tokenText = new StringBuilder();

        switch (consume()) {
            case 'a':
                switch (consume()) {
                    case 'b':
                        return keywordOrError(Token.ABS, "s");

                    case 'n':
                        return keywordOrError(Token.AND, "d");
                }

            case 'b':
                if (consume() != '\"') {
                    return Token.ERROR;
                }
                based_integer();
                if (consume() != '\"') {
                    return Token.ERROR;
                }
                return Token.BINARY_BIT_STRING_LITERAL;

            case 'm':
                return keywordOrError(Token.MOD, "od");

            case 'n':
                switch (consume()) {
                    case 'a':
                        return keywordOrError(Token.NAND, "nd");

                    case 'o':
                        switch (consume()) {
                            case 'r':
                                return Token.NOR;

                            case 't':
                                return Token.NOT;
                        }
                }

            case 'o':
                switch (consume()) {
                    case 'r':
                        return Token.ABS.OR;

                    case '\"':
                        based_integer();
                        if (consume() != '\"') {
                            return Token.ERROR;
                        }
                        return Token.OCTAL_BIT_STRING_LITERAL;
                }

            case 'r':
                switch (consume()) {
                    case 'e':
                        return keywordOrError(Token.REM, "m");

                    case 'o':
                        switch (consume()) {
                            case 'l':
                                return Token.ROL;

                            case 'r':
                                return Token.ROR;
                        }
                }

            case 's':
                switch (consume()) {
                    case 'l':
                        switch (consume()) {
                            case 'a':
                                return Token.SLA;
                            case 'l':
                                return Token.SLL;
                        }
                    case 'r':
                        switch (consume()) {
                            case 'a':
                                return Token.SRA;
                            case 'l':
                                return Token.SRL;
                        }
                }

            case 'x':
                switch (consume()) {
                    case 'n':
                        return keywordOrError(Token.XNOR, "or");
                    case 'o':
                        return keywordOrError(Token.XOR, "r");
                    case '\"':
                        based_integer();
                        if (consume() != '\"') {
                            return Token.ERROR;
                        }
                        return Token.HEX_BIT_STRING_LITERAL;
                }

            case '*':
                if (lookahead() == '*') {
                    consume();
                    return Token.DOUBLESTAR;
                } else {
                    return Token.MUL;
                }

            case '<':
                if (lookahead() == '=') {
                    consume();
                    return Token.LE;
                } else {
                    return Token.LT;
                }

            case '>':
                if (lookahead() == '=') {
                    consume();
                    return Token.GE;
                } else {
                    return Token.GT;
                }

            case '!':
                if (consume() == '=') {
                    return Token.NEQ;
                }
                return Token.ERROR;

            case '/':
                return Token.DIV;

            case '+':
                return Token.PLUS;

            case '-':
                return Token.MINUS;

            case '=':
                return Token.EQ;

            case '(':
                return Token.LPAREN;

            case ')':
                return Token.RPAREN;

            case '%':
                integer();
                return Token.PLACEHOLDER;

            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return decimal_or_based_literal();

            case '"':
                while (lookahead() != '\"') {
                    if (consume() == EOF) {
                        return Token.ERROR;
                    }
                }
                consume();

                return Token.STRING_LITERAL;

            case '\'':
                consume();
                if (consume() == '\'') {
                    return Token.CHARACTER_LITERAL;
                } else {
                    return Token.ERROR;
                }

            case ' ':
            case '\t':
            case '\n':
            case '\r':
                return Token.WHITESPACE;

            case EOF:
                return Token.EOF;
        }

        return Token.ERROR;
    }

    private Token decimal_or_based_literal() {
        Token type = Token.DECIMAL_LITERAL;
        integer();
        if (lookahead() == '.') {
            consume();
            if (!isDigit(lookahead())) {
                return Token.ERROR;
            }
            integer();
        } else if (lookahead() == '#') {
            type = Token.BASED_LITERAL;
            consume();
            based_integer();
            if (lookahead() == '.') {
                consume();
                based_integer();
            }
            if (consume() != '#') {
                return Token.ERROR;
            }
        }
        if (lookahead() == 'e') {
            consume();
            if (lookahead() == '-' || lookahead() == '+') {
                consume();
            }
            if (!isDigit(lookahead())) {
                return Token.ERROR;
            }
            integer();
        }
        return type;
    }

    private void based_integer() {
        boolean first = true;

        char c = lookahead();

        while (isDigit(c) || (c >= 'a' && c <= 'f') || (!first && c == '_')) {
            first = false;
            consume();
            c = lookahead();
        }
    }

    private void integer() {
        while (isDigit(lookahead()) || lookahead() == '_') {
            consume();
        }
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private char lookahead() {
        if (currentIndex < input.length()) {
            return input.charAt(currentIndex);
        } else {
            return EOF;
        }
    }

    private Token keywordOrError(Token type, String text) {
        for (char c : text.toCharArray()) {
            if (c != consume()) {
                return Token.ERROR;
            }
        }

        return type;
    }

    public String getTokenText() {
        return tokenText.toString();
    }
}

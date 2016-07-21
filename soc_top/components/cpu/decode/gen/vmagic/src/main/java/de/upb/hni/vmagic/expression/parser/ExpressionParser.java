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

import de.upb.hni.vmagic.expression.Expression;
import java.util.Arrays;
import java.util.List;

/**
 * VHDL expression parser.
 * The expression parser is a versatile utility to easily create complex VHDL expressions.
 * Without it you would need to create an expression by manually creating a hierarchy of meta
 * class objects. The expression parser simplifies this task by providing a printf like interface
 * for creating expressions.
 * <p>
 * The expression template is a VHDL expression with placeholdes, which get replaced by the objects
 * in the paramter list. The syntax of the placeholders is <code>%i</code>, where <code>i</code> is
 * the index of the object that should be inserted at the placeholdes position. The index of the
 * first paramter is 1.
 *
 * <h3>Examples:</h3>
 *
 * <h4>Using the ExpressionParser to create a simple Expression:</h4>
 * VHDL Output: <code>(10 + 20) * 30</code>
 * <pre>
 * Expression e = ExpressionParser.parse("(10 + 20) * 30");
 * </pre>
 * <h4>Creating a more complex expression using placeholders:</h4>
 * VHDL Output: <code>(s1 AND "01100100") OR (s1 xor s2)</code>
 * <pre>
 * Signal s1 = new Signal("s1", StdLogic1164.STD_LOGIC_VECTOR(8));
 * Signal s2 = new Signal("s2", StdLogic1164.STD_LOGIC_VECTOR(8));
 * Expression e = ExpressionParser.parse("(%1 and \"01100100\") or (%1 xor %2)", s1, s2);
 * </pre> 
 */
public class ExpressionParser {

    /**
     * Prevent instantiation.
     */
    private ExpressionParser() {
    }

    /**
     * Parses the expression template.
     * The given parameters are inserted at the position of the placeholders in the template string.
     * @param template the template
     * @param parameters a list of expressions used as parameters
     * @return the generated expression
     */
    public static Expression parse(String template, Expression... parameters) {
        return parse(template, Arrays.asList(parameters));
    }

    /**
     * Parses the expression template.
     * The given parameters are inserted at the position of the placeholders in the template string.
     * @param template the template
     * @param parameters a list of expressions used as parameters
     * @return the generated expression
     */
    public static Expression parse(String template, List<Expression> parameters) {
        Lexer lexer = new Lexer(template);
        Parser parser = new Parser(lexer, parameters);

        return parser.getExpression();
    }
}

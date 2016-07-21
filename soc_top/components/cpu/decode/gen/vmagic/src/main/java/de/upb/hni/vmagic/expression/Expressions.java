/*
 * Copyright 2009, 2010, 2011 University of Paderborn
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

import de.upb.hni.vmagic.expression.Aggregate.ElementAssociation;
import de.upb.hni.vmagic.AssociationElement;
import de.upb.hni.vmagic.builtin.SignalAttributes;
import de.upb.hni.vmagic.builtin.StdLogic1164;
import de.upb.hni.vmagic.literal.CharacterLiteral;
import de.upb.hni.vmagic.object.AttributeExpression;
import de.upb.hni.vmagic.object.Signal;

/**
 * Methods for expression creation.
 */
public class Expressions {

    private Expressions() {
    }

    private static Expression clockEdge(Signal clock, boolean rising, boolean useFunction) {
        if (useFunction) {
            FunctionCall call;
            if (rising) {
                call = new FunctionCall(StdLogic1164.RISING_EDGE);
            } else {
                call = new FunctionCall(StdLogic1164.FALLING_EDGE);
            }
            call.getParameters().add(new AssociationElement(clock));
            return call;
        } else {
            Expression condition1 = new AttributeExpression<Signal>(clock, SignalAttributes.EVENT);
            Expression state = rising ? StdLogic1164.STD_LOGIC_1 : StdLogic1164.STD_LOGIC_0;
            Expression condition2 = new Equals(clock, state);

            return new And(condition1, condition2);
        }
    }

    /**
     * Creates a rising edge clock condition.
     * Generated VHDL: <code>clock'event and clock = '1'</code>
     * @param clock the clock signal
     * @return the risign edge clock condition
     */
    public static Expression risingEdge(Signal clock) {
        return clockEdge(clock, true, false);
    }

    /**
     * Creates a rising edge clock condition with or without using a function call.
     * Generated VHDL: <code>clock'event and clock = '1'</code> or <code>rising_edge(clock)</code>
     * @param clock the clock signal
     * @param useFunction <code>true</code>, if the <code>rising_edge</code> should be used
     * @return the risign edge clock condition
     */
    public static Expression risingEdge(Signal clock, boolean useFunction) {
        return clockEdge(clock, true, useFunction);
    }

    /**
     * Creates a falling edge clock condition.
     * Generated VHDL: <code>clock'event and clock = '0'</code>
     * @param clock the clock signal
     * @return the risign edge clock condition
     */
    public static Expression fallingEdge(Signal clock) {
        return clockEdge(clock, false, false);
    }

    /**
     * Creates a falling edge clock condition with or without using a function call.
     * Generated VHDL: <code>clock'event and clock = '0'</code> or <code>falling_edge(clock)</code>
     * @param clock the clock signal
     * @param useFunction <code>true</code>, if the <code>falling_edge</code> should be used
     * @return the risign edge clock condition
     */
    public static Expression fallingEdge(Signal clock, boolean useFunction) {
        return clockEdge(clock, false, useFunction);
    }

    /**
     * Returns the clock signal in an edge condition.
     * If the expression is no edge condition <code>null</code> is returned.
     * <p>Recognized expressions:
     * <code><ul>
     * <li>clock'event and clock = '0'
     * <li>clock'event and clock = '1'
     * <li>clock = '0' and clock'event
     * <li>clock = '1' and clock'event
     * <li>not clock'stable and clock = '0'
     * <li>not clock'stable and clock = '1'
     * <li>clock = '0' and not clock'stable
     * <li>clock = '1' and not clock'stable
     * <li>falling_edge(clock)
     * <li>rising_edge(clock)
     * </ul></code>
     * @param expression the expression
     * @return the clock signal or <code>null</code>
     */
    public static Signal getEdgeConditionClock(Expression expression) {
        if (expression instanceof BinaryExpression) {
            BinaryExpression binExpr = toBinaryExpression(expression, ExpressionKind.AND);
            if (binExpr == null) {
                return null;
            }

            Signal clock = clockLevelToSignal(binExpr.getLeft());
            if (clock != null) {
                return isEventExpression(binExpr.getRight(), clock) ? clock : null;
            }

            clock = clockLevelToSignal(binExpr.getRight());
            if (clock != null) {
                return isEventExpression(binExpr.getLeft(), clock) ? clock : null;
            }

        } else if (expression instanceof FunctionCall) {
            FunctionCall call = (FunctionCall) expression;
            if (call.getFunction().equals(StdLogic1164.FALLING_EDGE)
                    || call.getFunction().equals(StdLogic1164.RISING_EDGE)) {
                if (call.getParameters().size() == 1) {
                    AssociationElement ae = call.getParameters().get(0);
                    if (ae.getActual() instanceof Signal) {
                        Signal s = (Signal) ae.getActual();
                        return s;
                    }
                }
            }
        } else if (expression instanceof Parentheses) {
            Parentheses p = (Parentheses) expression;
            return getEdgeConditionClock(p.getExpression());
        } else if (expression instanceof Aggregate) {
            Aggregate a = (Aggregate) expression;
            if (a.getAssociations().size() != 1) {
                return null;
            }
            ElementAssociation association = a.getAssociations().get(0);
            if (association.getChoices().isEmpty()) {
                return getEdgeConditionClock(association.getExpression());
            }
        }

        return null;
    }

    private static Signal clockLevelToSignal(Expression expression) {
        BinaryExpression binExpr = toBinaryExpression(expression, ExpressionKind.EQUALS);

        if (binExpr != null) {
            if (binExpr.getRight() instanceof CharacterLiteral) {
                CharacterLiteral literal = (CharacterLiteral) binExpr.getRight();
                if (literal.getCharacter() != '0' && literal.getCharacter() != '1') {
                    return null;
                }
            } else {
                return null;
            }

            if (binExpr.getLeft() instanceof Signal) {
                Signal clock = (Signal) binExpr.getLeft();
                return clock;
            }
        }

        return null;
    }

    private static boolean isEventExpression(Expression expression, Signal clock) {
        if (expression instanceof AttributeExpression) {
            AttributeExpression ae = (AttributeExpression) expression;
            if (!ae.getAttribute().getIdentifier().equalsIgnoreCase("event")) {
                return false;
            }

            return ae.getPrefix().equals(clock);
        } else if (expression instanceof Not) {
            Not not = (Not) expression;
            if (not.getExpression() instanceof AttributeExpression) {
                AttributeExpression ae = (AttributeExpression) not.getExpression();
                if (!ae.getAttribute().getIdentifier().equalsIgnoreCase("stable")) {
                    return false;
                }

                return ae.getPrefix().equals(clock);
            }
        }

        return false;
    }

    private static BinaryExpression toBinaryExpression(Expression expr, ExpressionKind kind) {
        if (expr instanceof BinaryExpression) {
            BinaryExpression binExpr = (BinaryExpression) expr;

            if (binExpr.getExpressionKind() == kind) {
                return binExpr;
            }
        }

        return null;
    }
}

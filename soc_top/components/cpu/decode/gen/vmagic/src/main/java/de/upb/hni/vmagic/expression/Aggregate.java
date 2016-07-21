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

import de.upb.hni.vmagic.Choice;
import de.upb.hni.vmagic.Choices;
import de.upb.hni.vmagic.VhdlElement;
import de.upb.hni.vmagic.object.SignalAssignmentTarget;
import de.upb.hni.vmagic.object.VariableAssignmentTarget;
import de.upb.hni.vmagic.type.SubtypeIndication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Aggregate.
 */
//TODO: check if aggregate is a valid signal assignment or variable assignment target
public class Aggregate extends Primary<Aggregate>
        implements SignalAssignmentTarget, VariableAssignmentTarget {

    private final List<ElementAssociation> associations = new ArrayList<ElementAssociation>();

    /**
     * Creates an empty aggregate.
     */
    public Aggregate() {
    }

    /**
     * Creates an aggregate that contains the given expressions.
     * @param expressions the epxressions
     */
    public Aggregate(Expression... expressions) {
        this(Arrays.asList(expressions));
    }

    /**
     * Creates an aggregate that contains the given expressions.
     * @param expressions the epxressions
     */
    public Aggregate(List<Expression> expressions) {
        for (Expression expression : expressions) {
            createAssociation(expression);
        }
    }

    /**
     * Returns the associations.
     * @return the associations
     */
    public List<ElementAssociation> getAssociations() {
        return associations;
    }

    /**
     * Creates a new positional element association and adds it to this aggregate.
     * @param expression the expression
     * @return the created element association
     */
    public ElementAssociation createAssociation(Expression expression) {
        ElementAssociation association = new ElementAssociation(expression);
        associations.add(association);
        return association;
    }

    /**
     * Creates a new named element association and adds it to this aggregate.
     * @param expression the expression
     * @param choices the choices
     * @return the created element association
     */
    public ElementAssociation createAssociation(Expression expression,
            List<Choice> choices) {
        ElementAssociation association = new ElementAssociation(expression, choices);
        associations.add(association);
        return association;
    }

    /**
     * Creates a new named element association and adds it to this aggregate.
     * @param expression the expression
     * @param choices the choices
     * @return the created element association
     */
    public ElementAssociation createAssociation(Expression expression, Choice... choices) {
        return createAssociation(expression, Arrays.asList(choices));
    }

    @Override
    public SubtypeIndication getType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    void accept(ExpressionVisitor visitor) {
        visitor.visitAggregate(this);
    }

    @Override
    public Aggregate copy() {
        Aggregate a = new Aggregate();
        for (ElementAssociation association : associations) {
            ElementAssociation associationCopy = new ElementAssociation(
                    association.expression.copy(), association.getChoices());
            a.associations.add(associationCopy);
        }

        return a;
    }

    /**
     * Creates a new aggregate of the type "(others => expression)".
     * @param expression the expression
     * @return the created aggregate
     */
    public static Aggregate OTHERS(Expression expression) {
        Aggregate aggregate = new Aggregate();
        aggregate.createAssociation(expression, Choices.OTHERS);
        return aggregate;
    }

    /**
     * An <code>ElementAssociation</code> associates choices with and expression.
     */
    public static class ElementAssociation extends VhdlElement {

        private final List<Choice> choices = new ArrayList<Choice>();
        private Expression expression;

        /**
         * Creates a positional element association.
         * @param expression the expression
         */
        private ElementAssociation(Expression expression) {
            this.expression = expression;
        }

        /**
         * Creates a named element association.
         * @param expression the associated epxression
         * @param choices the choices
         */
        private ElementAssociation(Expression expression, List<Choice> choices) {
            this.expression = expression;
            this.choices.addAll(choices);
        }

        /**
         * Returns the list of choices.
         * A positional element association returns an empty list.
         * @return the list of choices
         */
        public List<Choice> getChoices() {
            return choices;
        }

        /**
         * Returns the associated expression.
         * @return the associated expression
         */
        public Expression getExpression() {
            return expression;
        }

        /**
         * Sets the associated expression.
         * @param expression the associated expression
         */
        public void setExpression(Expression expression) {
            this.expression = expression;
        }
    }
}

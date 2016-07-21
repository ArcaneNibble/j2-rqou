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

package de.upb.hni.vmagic.statement;

import de.upb.hni.vmagic.Choice;
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.util.ParentSetList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Case statement.
 * 
 *
 * @vmagic.example
 * EnumerationType stateType = new EnumerationType("STATE_TYPE", "IDLE", "RUN");
 * Signal state = new Signal("STATE", stateType);
 * CaseStatement statement = new CaseStatement(state);
 * statement.createAlternative(stateType.getLiterals().get(0)).
 *  getStatements().add(new ReportStatement("state is idle"));
 * statement.createAlternative(Choices.OTHERS).
 *  getStatements().add(new ReportStatement("state is not idle"));
 * ---
 * case STATE is
 *  when IDLE =>
 *   report "state is idle";
 *
 *  when others =>
 *   report "state is not idle";
 * end case;
 */
public class CaseStatement extends SequentialStatement {

    private Expression expression;
    private final List<Alternative> alternatives = new ArrayList<Alternative>();

    /**
     * Creates a case statement.
     * @param expression the expression
     */
    public CaseStatement(Expression expression) {
        this.expression = expression;
    }

    /**
     * Returns the expression.
     * @return the epxression
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Sets the expression.
     * @param expression the expression
     */
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Creates a new alternative and adds it to this case statement.
     * @param choices one or more choices that select this alternative
     * @return the created alternative
     */
    public Alternative createAlternative(Choice... choices) {
        Alternative alternative = new Alternative(choices);
        alternatives.add(alternative);
        return alternative;
    }

    /**
     * Creates a new alternative and adds it to this case statement.
     * @param choices a list of choices that select this alternative
     * @return the created alternative
     */
    public Alternative createAlternative(List<Choice> choices) {
        Alternative alternative = new Alternative(choices);
        alternatives.add(alternative);
        return alternative;
    }

    /**
     * Returns the alternatives.
     * @return a modifiable list of case alternatives
     */
    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    @Override
    void accept(SequentialStatementVisitor visitor) {
        visitor.visitCaseStatement(this);
    }

    /**
     * Case statement alternative.
     */
    public final class Alternative {

        private final List<Choice> choices;
        private final List<SequentialStatement> statements =
                ParentSetList.createProxyList(CaseStatement.this);

        private Alternative(Choice... choices) {
            this(Arrays.asList(choices));
        }

        private Alternative(List<Choice> choices) {
            this.choices = new ArrayList<Choice>(choices);
        }

        /**
         * Returns the choices.
         * @return a modifiable list of choices
         */
        public List<Choice> getChoices() {
            return choices;
        }

        /**
         * Returns the statements.
         * @return a modifiable list of sequential statements
         */
        public List<SequentialStatement> getStatements() {
            return statements;
        }
    }
}

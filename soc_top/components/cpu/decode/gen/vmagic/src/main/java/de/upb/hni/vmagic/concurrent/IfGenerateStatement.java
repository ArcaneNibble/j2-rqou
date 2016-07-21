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

package de.upb.hni.vmagic.concurrent;

import de.upb.hni.vmagic.Scope;
import de.upb.hni.vmagic.Scopes;
import de.upb.hni.vmagic.expression.Expression;

/**
 * If generate statement.
 */
public class IfGenerateStatement extends AbstractGenerateStatement {

    private Expression condition;
    private final Scope scope = Scopes.createScope(this);

    /**
     * Creates an if generate statement.
     * @param label the label
     * @param condition the if condition
     */
    public IfGenerateStatement(String label, Expression condition) {
        setLabel(label);
        this.condition = condition;
    }

    /**
     * Returns the if condition.
     * @return the condition
     */
    public Expression getCondition() {
        return condition;
    }

    /**
     * Sets the if condition.
     * @param condition the condition
     */
    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    @Override
    void accept(ConcurrentStatementVisitor visitor) {
        visitor.visitIfGenerateStatement(this);
    }

    public Scope getScope() {
        return scope;
    }
}

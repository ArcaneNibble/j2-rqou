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

import de.upb.hni.vmagic.DiscreteRange;
import de.upb.hni.vmagic.Resolvable;
import de.upb.hni.vmagic.Scope;
import de.upb.hni.vmagic.Scopes;
import de.upb.hni.vmagic.builtin.Standard;
import de.upb.hni.vmagic.object.Constant;

/**
 * For loop.
 *
 * @vmagic.example
 * ForStatement loop = new ForStatement("I",
 *  new Range(0, Range.Direction.TO, 127));
 * loop.getStatements().add(new NullStatement());
 * ---
 * for I in 0 to 127 loop
 *  null;
 * end loop;
 */
public class ForStatement extends LoopStatement {

    //TODO: make type of loop parameter unmutable
    private final Constant loopParameter;
    private DiscreteRange range;
    private final Scope scope = Scopes.createScope(this, new ResolvableImpl());

    /**
     * Creates a for loop.
     * @param loopParameter the identifier of the loop parameter
     * @param range the loop range
     */
    public ForStatement(String loopParameter, DiscreteRange range) {
        this.loopParameter = new Constant(loopParameter, Standard.INTEGER);
        this.range = range;
    }

    /**
     * Returns loop parameter.
     * @return the loop parameter
     */
    public Constant getParameter() {
        return loopParameter;
    }

    /**
     * Returns the loop range.
     * @return the range
     */
    public DiscreteRange getRange() {
        return range;
    }

    /**
     * Sets the loop range.
     * @param range the range
     */
    public void setRange(DiscreteRange range) {
        this.range = range;
    }

    @Override
    void accept(SequentialStatementVisitor visitor) {
        visitor.visitForStatement(this);
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    private class ResolvableImpl implements Resolvable {

        public Object resolve(String identifier) {
            if (identifier.equalsIgnoreCase(getLabel())) {
                return ForStatement.this;
            }

            if (identifier.equalsIgnoreCase(getParameter().getIdentifier())) {
                return getParameter();
            }

            return null;
        }
    }
}

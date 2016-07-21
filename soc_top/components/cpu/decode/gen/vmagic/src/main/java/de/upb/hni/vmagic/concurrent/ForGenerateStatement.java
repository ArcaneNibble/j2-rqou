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

import de.upb.hni.vmagic.DiscreteRange;
import de.upb.hni.vmagic.Resolvable;
import de.upb.hni.vmagic.Scope;
import de.upb.hni.vmagic.Scopes;
import de.upb.hni.vmagic.builtin.Standard;
import de.upb.hni.vmagic.object.Constant;

/**
 * For generate statement.
 */
public class ForGenerateStatement extends AbstractGenerateStatement {

    //TODO: make type of loop parameter unmutable
    private final Constant loopParameter;
    private DiscreteRange range;
    private final Resolvable resolvable = new ResolvableImpl();
    private final Scope scope = Scopes.createScope(this, resolvable);

    /**
     * Creates a for generate statement.
     * @param label the label
     * @param loopParameter the identifier of the for loop parameter
     * @param range the loop range
     */
    public ForGenerateStatement(String label, String loopParameter, DiscreteRange range) {
        setLabel(label);
        this.loopParameter = new Constant(loopParameter, Standard.INTEGER);
        this.range = range;
    }

    /**
     * Returns the loop parameter.
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
    void accept(ConcurrentStatementVisitor visitor) {
        visitor.visitForGenerateStatement(this);
    }

    public Scope getScope() {
        return scope;
    }

    private class ResolvableImpl implements Resolvable {

        public Object resolve(String identifier) {
            if (identifier.equalsIgnoreCase(loopParameter.getIdentifier())) {
                return loopParameter;
            }

            return null;
        }

    }
}

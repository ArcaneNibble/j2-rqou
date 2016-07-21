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

package de.upb.hni.vmagic;

import de.upb.hni.vmagic.expression.Expression;

/**
 * Association element.
 */
//FIXME: dummy implementation
public class AssociationElement extends VhdlElement {

    private String formal;
    private Expression actual;

    /**
     * Creates an association element without a formal part.
     * @param actual the actual part
     */
    public AssociationElement(Expression actual) {
        this.actual = actual;
    }

    /**
     * Creates an association element with a formal and actual part.
     * @param formal the formal part
     * @param actual the actual part
     */
    public AssociationElement(String formal, Expression actual) {
        this.formal = formal;
        this.actual = actual;
    }

    /**
     * Return the actual part of this association.
     * @return the actual part
     */
    public Expression getActual() {
        return actual;
    }

    /**
     * Sets the actual part of this association.
     * @param actual the actual part
     */
    public void setActual(Expression actual) {
        this.actual = actual;
    }

    /**
     * Returns the formal part of this association.
     * @return the formal part
     */
    public String getFormal() {
        return formal;
    }

    /**
     * Sets the formal part of this association.
     * @param formal the formal part
     */
    public void setFormal(String formal) {
        this.formal = formal;
    }
}

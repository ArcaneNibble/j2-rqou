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

import de.upb.hni.vmagic.LabeledElement;

/**
 * Abstract base class for concurrent statements.
 */
public abstract class ConcurrentStatement extends LabeledElement {

    private String label;

    /**
     * Returns the statement's label.
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the statement's label.
     * Use <code>null</code> as the parameter to disable the label.
     * @param label the label or <code>null</code>
     */
    public void setLabel(String label) {
        this.label = label;
    }

    abstract void accept(ConcurrentStatementVisitor visitor);
}
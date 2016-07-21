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

import de.upb.hni.vmagic.declaration.Component;

/**
 * Component instantitation.
 */
public class ComponentInstantiation extends AbstractComponentInstantiation {

    private Component component;

    /**
     * Creates a component instantiation.
     * @param label the label
     * @param component the instantiated component
     */
    public ComponentInstantiation(String label, Component component) {
        super(label);

        this.component = component;
    }

    /**
     * Returns the instantiated component.
     * @return the instantiated component
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Sets the instantiated component.
     * @param component the instantiated component
     */
    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    void accept(ConcurrentStatementVisitor visitor) {
        visitor.visitComponentInstantiation(this);
    }
}

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

import de.upb.hni.vmagic.AssociationElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for component, entity and configuration instantiations.
 */
public abstract class AbstractComponentInstantiation extends ConcurrentStatement {

    private List<AssociationElement> portMap = new ArrayList<AssociationElement>();
    private List<AssociationElement> genericMap = new ArrayList<AssociationElement>();

    /**
     * Creates an abstract component instantiation with the given label.
     * @param label the label of the instantiated componet
     */
    public AbstractComponentInstantiation(String label) {
        setLabel(label);
    }

    /**
     * Returns the generic map.
     * @return a modifiable list of association elements
     */
    public List<AssociationElement> getGenericMap() {
        return genericMap;
    }

    /**
     * Returns the port map.
     * @return a modifiable list of association elements
     */
    public List<AssociationElement> getPortMap() {
        return portMap;
    }
}

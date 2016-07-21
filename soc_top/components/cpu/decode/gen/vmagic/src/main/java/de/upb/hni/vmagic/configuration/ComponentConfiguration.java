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

package de.upb.hni.vmagic.configuration;

import de.upb.hni.vmagic.ComponentSpecification;
import de.upb.hni.vmagic.AssociationElement;
import de.upb.hni.vmagic.EntityAspect;
import java.util.ArrayList;
import java.util.List;

/**
 * Component configuration.
 */
public class ComponentConfiguration extends ConfigurationItem {

    private ComponentSpecification componentSpecification;
    private AbstractBlockConfiguration blockConfiguration;
    private EntityAspect entityAspect;
    private final List<AssociationElement> portMap = new ArrayList<AssociationElement>();
    private final List<AssociationElement> genericMap = new ArrayList<AssociationElement>();

    /**
     * Creates a component configuration.
     * @param componentSpecification specifies the configured components
     */
    public ComponentConfiguration(ComponentSpecification componentSpecification) {
        this.componentSpecification = componentSpecification;
    }

    /**
     * Returns the block configuration.
     * @return the block configuration
     */
    public AbstractBlockConfiguration getBlockConfiguration() {
        return blockConfiguration;
    }

    /**
     * Sets the block configuration.
     * @param blockConfiguration the block configuration
     */
    public void setBlockConfiguration(AbstractBlockConfiguration blockConfiguration) {
        this.blockConfiguration = blockConfiguration;
    }

    /**
     * Returns the component specification.
     * @return the component specification
     */
    public ComponentSpecification getComponentSpecification() {
        return componentSpecification;
    }

    /**
     * Sets the component specification.
     * @param componentSpecification the component specification
     */
    public void setComponentSpecification(ComponentSpecification componentSpecification) {
        this.componentSpecification = componentSpecification;
    }

    /**
     * Returns the entity aspect.
     * @return the entity aspect
     */
    public EntityAspect getEntityAspect() {
        return entityAspect;
    }

    /**
     * Sets the entity aspect.
     * @param entityAspect the entity aspect
     */
    public void setEntityAspect(EntityAspect entityAspect) {
        this.entityAspect = entityAspect;
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

    @Override
    void accept(ConfigurationVisitor visitor) {
        visitor.visitComponentConfiguration(this);
    }
}

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

package de.upb.hni.vmagic.libraryunit;

import de.upb.hni.vmagic.NamedEntity;
import de.upb.hni.vmagic.Scope;
import de.upb.hni.vmagic.configuration.AbstractBlockConfiguration;
import de.upb.hni.vmagic.declaration.ConfigurationDeclarativeItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration.
 */
public class Configuration extends LibraryUnit implements NamedEntity {

    private String identifier;
    private Entity entity;
    private final List<ConfigurationDeclarativeItem> declarations =
            new ArrayList<ConfigurationDeclarativeItem>();
    private AbstractBlockConfiguration blockConfiguration;

    /**
     * Creates a configuration.
     * @param identifier the identifier of this configuration
     * @param entity the entity
     * @param blockConfiguration the block configuration
     */
    public Configuration(String identifier, Entity entity,
            AbstractBlockConfiguration blockConfiguration) {
        this.identifier = identifier;
        this.entity = entity;
        this.blockConfiguration = blockConfiguration;
    }

    /**
     * Returns the configured entity.
     * @return the configured entity
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * Sets the configured entity.
     * @param entity the configured entity
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Returns the identifier of this configuration.
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier of this configuration.
     * @param identifier the identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the list of declarations in this configuration.
     * @return the list of declarations
     */
    public List<ConfigurationDeclarativeItem> getDeclarations() {
        return declarations;
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

    public Scope getScope() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    void accept(LibraryUnitVisitor visitor) {
        visitor.visitConfiguration(this);
    }
}

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

import de.upb.hni.vmagic.libraryunit.Architecture;
import de.upb.hni.vmagic.libraryunit.Configuration;
import de.upb.hni.vmagic.libraryunit.Entity;

/**
 * Entity aspect.
 */
public abstract class EntityAspect extends VhdlElement {

    /**
     * An entity aspect using the <code>OPEN</code> keyword.
     */
    public static EntityAspect OPEN = new EntityAspect() {

        //TODO: use output keyword case setting
        @Override
        public String toString() {
            return "open";
        }
    };

    /**
     * Creates an entity aspect based on an entity.
     * @param entity the entity
     * @return the created entity aspect
     */
    public static EntityAspect entity(Entity entity) {
        return new EntityEntityAspect(entity);
    }

    /**
     * Creates an entity aspect based on an architecture.
     * @param architecute the architecture
     * @return the created entity aspect
     */
    public static EntityAspect architecture(Architecture architecute) {
        return new ArchitectureEntityAspect(architecute);
    }

    /**
     * Creates an entity aspect based on a configuration.
     * @param configuration the configuration
     * @return the created entity aspect
     */
    public static EntityAspect configuration(Configuration configuration) {
        return new ConfigurationEntityAspect(configuration);
    }

    private static class EntityEntityAspect extends EntityAspect {

        private final Entity entity;

        public EntityEntityAspect(Entity entity) {
            this.entity = entity;
        }

        @Override
        public String toString() {
            if (entity != null) {
                return "entity " + entity.getIdentifier();
            } else {
                return "entity null";
            }
        }
    }

    private static class ArchitectureEntityAspect extends EntityAspect {

        private final Architecture architecture;

        public ArchitectureEntityAspect(Architecture architecture) {
            this.architecture = architecture;
        }

        //TODO: use output keyword case setting
        @Override
        public String toString() {
            if (architecture == null) {
                return "entity null(null)";
            } else {
                if (architecture.getEntity() != null) {
                    return "entity " + architecture.getEntity().getIdentifier() +
                            '(' + architecture.getIdentifier() + ')';
                } else {
                    return "entity null(" + architecture.getIdentifier() + ")";
                }
            }
        }
    }

    private static class ConfigurationEntityAspect extends EntityAspect {

        private final Configuration configuration;

        public ConfigurationEntityAspect(Configuration configuration) {
            this.configuration = configuration;
        }

        @Override
        public String toString() {
            if (configuration != null) {
                return "configuration " + configuration.getIdentifier();
            } else {
                return "configuration null";
            }
        }
    }
}

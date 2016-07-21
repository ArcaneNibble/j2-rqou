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

import de.upb.hni.vmagic.libraryunit.Entity;

/**
 * Entity instantiation.
 */
public class EntityInstantiation extends AbstractComponentInstantiation {

    private Entity entity;

    /**
     * Creates a entity instantiation.
     * @param label the label
     * @param entity the instantiated entity
     */
    public EntityInstantiation(String label, Entity entity) {
        super(label);
        this.entity = entity;
    }

    /**
     * Returns the instantiated entity.
     * @return the entity
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * Sets the instantiated entity.
     * @param entity the entity
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    void accept(ConcurrentStatementVisitor visitor) {
        visitor.visitEntityInstantiation(this);
    }
}

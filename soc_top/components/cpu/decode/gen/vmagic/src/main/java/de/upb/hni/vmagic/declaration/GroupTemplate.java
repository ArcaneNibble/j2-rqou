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

package de.upb.hni.vmagic.declaration;

import de.upb.hni.vmagic.NamedEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Group template.
 */
public class GroupTemplate extends DeclarativeItem implements BlockDeclarativeItem,
        EntityDeclarativeItem, PackageBodyDeclarativeItem, PackageDeclarativeItem,
        ProcessDeclarativeItem, SubprogramDeclarativeItem, NamedEntity {

    private String identifier;
    private final List<EntityClass> entityClasses = new ArrayList<EntityClass>();
    private boolean repeatLast;

    /**
     * Creates a group template.
     * @param identifier the identifier of this group template
     */
    public GroupTemplate(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the identifier of this group template.
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier of this group template.
     * @param identifier the identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the entity classes.
     * @return a modifiable list of entity classes
     */
    public List<EntityClass> getEntityClasses() {
        return entityClasses;
    }

    /**
     * Returns if the last entity class is repeated.
     * @return <code>true</code>, if the last entity class is repeated
     */
    public boolean isRepeatLast() {
        return repeatLast;
    }

    /**
     * Sets if the last entity class can be repeated.
     * @param repeatLast <code>true</code>, if the last entity class can be repeated
     */
    public void setRepeatLast(boolean repeatLast) {
        this.repeatLast = repeatLast;
    }

    @Override
    void accept(DeclarationVisitor visitor) {
        visitor.visitGroupTemplateDeclaration(this);
    }
}

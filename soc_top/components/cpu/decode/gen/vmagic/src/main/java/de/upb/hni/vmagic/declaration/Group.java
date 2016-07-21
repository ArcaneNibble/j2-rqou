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
 * Group declaration.
 */
//TODO: use names and character literals as constituents.
public class Group extends DeclarativeItem implements BlockDeclarativeItem,
        ConfigurationDeclarativeItem, EntityDeclarativeItem, PackageBodyDeclarativeItem,
        PackageDeclarativeItem, ProcessDeclarativeItem, SubprogramDeclarativeItem, NamedEntity {

    private String identifier;
    private GroupTemplate template;
    private final List<String> constituents = new ArrayList<String>();

    /**
     * Creates a group declaration.
     * @param identifier the identifier
     * @param template the group template
     */
    public Group(String identifier, GroupTemplate template) {
        this.identifier = identifier;
        this.template = template;
    }

    /**
     * Returns the identifier of this group.
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier of this group.
     * @param identifier the identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the group template.
     * @return the template
     */
    public GroupTemplate getTemplate() {
        return template;
    }

    /**
     * Sets the group template.
     * @param template the template
     */
    public void setTemplate(GroupTemplate template) {
        this.template = template;
    }

    /**
     * Returns the constituents.
     * @return a modifiable list of constituents
     */
    public List<String> getConstituents() {
        return constituents;
    }

    @Override
    void accept(DeclarationVisitor visitor) {
        visitor.visitGroupDeclaration(this);
    }
}

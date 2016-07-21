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

import de.upb.hni.vmagic.DeclarativeRegion;
import de.upb.hni.vmagic.Scope;
import de.upb.hni.vmagic.object.Constant;
import de.upb.hni.vmagic.libraryunit.Entity;
import de.upb.hni.vmagic.NamedEntity;
import de.upb.hni.vmagic.Scopes;
import de.upb.hni.vmagic.object.Signal;
import de.upb.hni.vmagic.object.VhdlObjectProvider;
import de.upb.hni.vmagic.util.ResolvableList;
import de.upb.hni.vmagic.util.VhdlCollections;
import java.util.List;

/**
 * Component.
 */
public class Component extends DeclarativeItem
        implements BlockDeclarativeItem, PackageDeclarativeItem, NamedEntity, DeclarativeRegion {

    private String identifier;
    private final ResolvableList<VhdlObjectProvider<Constant>> generic =
            VhdlCollections.createVhdlObjectList();
    private final ResolvableList<VhdlObjectProvider<Signal>> port =
            VhdlCollections.createVhdlObjectList();
    private final Scope scope = Scopes.createScope(this, generic, port);

    /**
     * Creates a component.
     * @param identifier the component identifier
     */
    public Component(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Creates a component based on an entity.
     * The identifier, port and generic of the entity is used to initialize the
     * component.
     * @param entity the entity
     */
    public Component(Entity entity) {
        this.identifier = entity.getIdentifier();
        generic.addAll(entity.getGeneric());
        port.addAll(entity.getPort());
    }

    /**
     * Returns the identifier of this component.
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier of this component.
     * @param identifier the identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the generic.
     * @return a list of constants
     */
    public List<VhdlObjectProvider<Constant>> getGeneric() {
        return generic;
    }

    /**
     * Returns the port.
     * @return a list of signals
     */
    public List<VhdlObjectProvider<Signal>> getPort() {
        return port;
    }

    public Scope getScope() {
        return scope;
    }

    @Override
    void accept(DeclarationVisitor visitor) {
        visitor.visitComponentDeclaration(this);
    }
}

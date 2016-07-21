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

import de.upb.hni.vmagic.object.Constant;
import de.upb.hni.vmagic.NamedEntity;
import de.upb.hni.vmagic.Scope;
import de.upb.hni.vmagic.Scopes;
import de.upb.hni.vmagic.object.Signal;
import de.upb.hni.vmagic.concurrent.EntityStatement;
import de.upb.hni.vmagic.declaration.EntityDeclarativeItem;
import de.upb.hni.vmagic.object.VhdlObjectProvider;
import de.upb.hni.vmagic.util.ResolvableList;
import de.upb.hni.vmagic.util.VhdlCollections;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity declaration.
 */
public class Entity extends LibraryUnit implements NamedEntity {

    private String identifier;
    private final ResolvableList<VhdlObjectProvider<Signal>> port =
            VhdlCollections.createVhdlObjectList();
    private final ResolvableList<VhdlObjectProvider<Constant>> generic =
            VhdlCollections.createVhdlObjectList();
    private final ResolvableList<EntityDeclarativeItem> declarations =
            VhdlCollections.createDeclarationList();
    private final List<EntityStatement> statements = new ArrayList<EntityStatement>();
    private final Scope scope = Scopes.createScope(this, generic, port, declarations);

    /**
     * Creates a entity.
     * @param identifier the identifier
     */
    public Entity(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the identifier of this entity.
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier of this entity.
     * @param identifier the identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the list of declarations in this entity.
     * @return the list of declarations
     */
    public List<EntityDeclarativeItem> getDeclarations() {
        return declarations;
    }

    /**
     * Returns the list of statements in this entity.
     * @return the list of statements
     */
    public List<EntityStatement> getStatements() {
        return statements;
    }

    /**
     * Returns the generic.
     * @return the list of constants in the generic of this entity
     */
    public List<VhdlObjectProvider<Constant>> getGeneric() {
        return generic;
    }

    /**
     * Returns the port.
     * @return the list of signals in the port of this entity
     */
    public List<VhdlObjectProvider<Signal>> getPort() {
        return port;
    }

    public Scope getScope() {
        return scope;
    }

    @Override
    void accept(LibraryUnitVisitor visitor) {
        visitor.visitEntity(this);
    }
}

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
import de.upb.hni.vmagic.Resolvable;
import de.upb.hni.vmagic.Scope;
import de.upb.hni.vmagic.Scopes;
import de.upb.hni.vmagic.concurrent.ConcurrentStatement;
import de.upb.hni.vmagic.declaration.BlockDeclarativeItem;
import de.upb.hni.vmagic.util.ResolvableList;
import de.upb.hni.vmagic.util.VhdlCollections;
import java.util.List;

/**
 * Architecture body.
 */
public class Architecture extends LibraryUnit implements NamedEntity {

    private String identifier;
    private Entity entity;
    private final ResolvableList<BlockDeclarativeItem> declarations =
            VhdlCollections.createDeclarationList();
    private final ResolvableList<ConcurrentStatement> statements =
            VhdlCollections.createLabeledElementList(this);
    private final Resolvable resolvable = new ResolvableImpl();
    private final Scope scope = Scopes.createScope(this, declarations, statements, resolvable);

    /**
     * Creates an architecture.
     * @param identifier the architectures identifier
     * @param entity the associated entity
     */
    public Architecture(String identifier, Entity entity) {
        this.identifier = identifier;
        this.entity = entity;
    }

    /**
     * Returns the entity that belogs to this architecture.
     * @return the entity
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * Sets the entity that belongs to this architecture.
     * @param entity the entity
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Returns the identifier of this architecture.
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier of this architecture.
     * @param identifier the identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the list of declarations in this architecture.
     * @return the list of declarations
     */
    public List<BlockDeclarativeItem> getDeclarations() {
        return declarations;
    }

    /**
     * Returns the list of statements in this architecture.
     * @return the list of statements
     */
    public List<ConcurrentStatement> getStatements() {
        return statements;
    }

    public Scope getScope() {
        return scope;
    }

    @Override
    void accept(LibraryUnitVisitor visitor) {
        visitor.visitArchitecture(this);
    }

    private class ResolvableImpl implements Resolvable {

        public Object resolve(String identifier) {
            if (getEntity() != null) {
                return getEntity().getScope().resolveLocal(identifier);
            }
            return null;
        }
    }
}

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
import de.upb.hni.vmagic.Scopes;
import de.upb.hni.vmagic.declaration.PackageDeclarativeItem;
import de.upb.hni.vmagic.util.ResolvableList;
import de.upb.hni.vmagic.util.VhdlCollections;
import java.util.List;

/**
 * PackageDeclaration declaration.
 */
public class PackageDeclaration extends LibraryUnit implements NamedEntity {

    private String identifier;
    private final ResolvableList<PackageDeclarativeItem> declarations =
            VhdlCollections.createDeclarationList();
    //TODO: also resolve the package declaration?
    private final Scope scope = Scopes.createScope(this, declarations);

    /**
     * Creates a package declaration.
     * @param identifier the package identifier
     */
    public PackageDeclaration(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the identifier of this package.
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier of this package.
     * @param identifier the identifer
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the list of declarations in this package.
     * @return the list of declarations
     */
    public List<PackageDeclarativeItem> getDeclarations() {
        return declarations;
    }

    public Scope getScope() {
        return scope;
    }

    @Override
    void accept(LibraryUnitVisitor visitor) {
        visitor.visitPackageDeclaration(this);
    }
}

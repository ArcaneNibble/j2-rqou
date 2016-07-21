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

import de.upb.hni.vmagic.Resolvable;
import de.upb.hni.vmagic.Scope;
import de.upb.hni.vmagic.Scopes;
import de.upb.hni.vmagic.declaration.PackageBodyDeclarativeItem;
import de.upb.hni.vmagic.util.ResolvableList;
import de.upb.hni.vmagic.util.VhdlCollections;
import java.util.List;

/**
 * PackageDeclaration body.
 */
public class PackageBody extends LibraryUnit {

    private PackageDeclaration pack;
    private final ResolvableList<PackageBodyDeclarativeItem> declarations =
            VhdlCollections.createDeclarationList();
    private final Resolvable resolvable = new ResolvableImpl();
    private final Scope scope = Scopes.createScope(this, declarations, resolvable);

    /**
     * Creates a package body.
     * @param pack the associated package
     */
    public PackageBody(PackageDeclaration pack) {
        this.pack = pack;
    }

    /**
     * Returns the associated package.
     * @return the package
     */
    public PackageDeclaration getPackage() {
        return pack;
    }

    /**
     * Sets the associated package.
     * @param pack the package
     */
    public void setPackage(PackageDeclaration pack) {
        this.pack = pack;
    }

    /**
     * Returns the list of declarations in this package body.
     * @return the list of declarations
     */
    public List<PackageBodyDeclarativeItem> getDeclarations() {
        return declarations;
    }

    public Scope getScope() {
        return scope;
    }

    @Override
    void accept(LibraryUnitVisitor visitor) {
        visitor.visitPackageBody(this);
    }

    private class ResolvableImpl implements Resolvable {

        public Object resolve(String identifier) {
            if (pack != null) {
                return pack.getScope().resolveLocal(identifier);
            }

            return null;
        }

    }
}

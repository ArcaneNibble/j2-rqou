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

import de.upb.hni.vmagic.Scope;
import de.upb.hni.vmagic.declaration.BlockDeclarativeItem;
import de.upb.hni.vmagic.declaration.ConfigurationDeclarativeItem;
import de.upb.hni.vmagic.declaration.EntityDeclarativeItem;
import de.upb.hni.vmagic.declaration.PackageBodyDeclarativeItem;
import de.upb.hni.vmagic.declaration.PackageDeclarativeItem;
import de.upb.hni.vmagic.declaration.ProcessDeclarativeItem;
import de.upb.hni.vmagic.declaration.SubprogramDeclarativeItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Use clause.
 */
//TODO: don't use names for declarations
//TODO: remove LibraryUnit?
public class UseClause extends LibraryUnit
        implements BlockDeclarativeItem, ConfigurationDeclarativeItem,
        EntityDeclarativeItem, PackageBodyDeclarativeItem,
        PackageDeclarativeItem, ProcessDeclarativeItem,
        SubprogramDeclarativeItem {

    private final List<String> declarations;

    /**
     * Creates a use clause.
     * @param declarations the declarations
     */
    public UseClause(String... declarations) {
        this(Arrays.asList(declarations));
    }

    /**
     * Creates a use clause.
     * @param declarations the declarations
     */
    public UseClause(List<String> declarations) {
        this.declarations = new ArrayList<String>(declarations);
    }

    /**
     * Returns the list of declarations in this use clause
     * @return the list of declarations
     */
    //TODO: rename?
    public List<String> getDeclarations() {
        return declarations;
    }

    //TODO: implement
    public Scope getScope() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    void accept(LibraryUnitVisitor visitor) {
        visitor.visitUseClause(this);
    }
}

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

import de.upb.hni.vmagic.builtin.Libraries;
import de.upb.hni.vmagic.builtin.Standard;
import de.upb.hni.vmagic.libraryunit.LibraryUnit;
import de.upb.hni.vmagic.libraryunit.UseClause;
import de.upb.hni.vmagic.util.ResolvableList;
import de.upb.hni.vmagic.util.VhdlCollections;
import java.util.List;

/**
 * Root declarative region.
 * The root declarative region is the base of the scope tree and can contain
 * multiple libraries.
 */
public class RootDeclarativeRegion extends VhdlElement implements DeclarativeRegion {

    private final ResolvableList<LibraryDeclarativeRegion> libraries =
            VhdlCollections.createNamedEntityList(this);
    private final Scope scope = Scopes.createScope(this, libraries, new UseClauseResolvable());
    private final Scope internalScope = Scopes.createScope(this, libraries);

    /**
     * Creates an root declarative region containing the Standard library.
     */
    public RootDeclarativeRegion() {
        libraries.add(Libraries.STD);
    }

    /**
     * Returns the libraries in this root declarative region.
     * @return a modifiable list of library declarative regions
     */
    public List<LibraryDeclarativeRegion> getLibraries() {
        return libraries;
    }

    public Scope getScope() {
        return scope;
    }

    //TODO: move to VhdlFile. Problem:
    //File 1:
    //library ieee;
    //use ieee.std_logic_1164.all;
    //entity ent is
    //end;
    //----
    //File 2:
    //architecture beh of ent is
    //signal test : std_logic;
    //begin
    //end;
    private class UseClauseResolvable implements Resolvable {

        private Object descentHierarchy(String[] parts, String identifier) {
            Scope scope = internalScope;

            for (int i = 0; i < parts.length - 1; i++) {
                DeclarativeRegion region =
                        scope.resolveLocal(parts[i], DeclarativeRegion.class);
                if (region == null) {
                    return null;
                } else {
                    scope = region.getScope();
                }
            }
            return scope.resolveLocal(identifier);
        }

        public Object resolve(String identifier) {
            for (LibraryDeclarativeRegion library : libraries) {
                for (VhdlFile file : library.getFiles()) {
                    for (LibraryUnit libraryUnit : file.getElements()) {
                        if (libraryUnit instanceof UseClause) {
                            UseClause useClause = (UseClause) libraryUnit;

                            for (String declaration : useClause.getDeclarations()) {
                                String[] parts = declaration.split("\\.");
                                if (parts[parts.length - 1].equalsIgnoreCase("all")) {
                                    Object o = descentHierarchy(parts, identifier);
                                    if (o != null) {
                                        return o;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            //implicit use std.standard.all:
            return Standard.PACKAGE.getScope().resolveLocal(identifier);
        }
    }
}

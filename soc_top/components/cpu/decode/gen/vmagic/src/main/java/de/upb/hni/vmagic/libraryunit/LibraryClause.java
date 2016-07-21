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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Library clause.
 */
//TODO: remove LibraryUnit
public class LibraryClause extends LibraryUnit {

    private final List<String> libraries = new ArrayList<String>();

    /**
     * Crates a library clause.
     * @param libraries the libraries
     */
    public LibraryClause(String... libraries) {
        this.libraries.addAll(Arrays.asList(libraries));
    }

    /**
     * Crates a library clause.
     * @param libraries the libraries
     */
    public LibraryClause(List<String> libraries) {
        this.libraries.addAll(libraries);
    }

    /**
     * Returns the list of libraries in this library clause.
     * @return the list of libraries
     */
    public List<String> getLibraries() {
        return libraries;
    }

    //TODO: implement
    public Scope getScope() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    void accept(LibraryUnitVisitor visitor) {
        visitor.visitLibraryClause(this);
    }
}

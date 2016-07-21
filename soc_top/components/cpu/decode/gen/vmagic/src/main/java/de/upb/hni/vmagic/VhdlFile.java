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

import de.upb.hni.vmagic.libraryunit.LibraryUnit;
import de.upb.hni.vmagic.util.ResolvableList;
import de.upb.hni.vmagic.util.VhdlCollections;
import java.util.List;

/**
 * VHDL file.
 */
public class VhdlFile extends VhdlElement implements DeclarativeRegion {

    private final ResolvableList<LibraryUnit> elements =
            VhdlCollections.createNamedEntityList(this);
    private final Scope scope = Scopes.createScope(this, elements);

    /**
     * Returns the list of elements in this VHDL file.
     * @return the list of elements
     */
    public List<LibraryUnit> getElements() {
        return elements;
    }

    public Scope getScope() {
        return scope;
    }

   
}

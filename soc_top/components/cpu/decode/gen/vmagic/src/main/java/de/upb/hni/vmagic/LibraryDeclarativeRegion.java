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
import de.upb.hni.vmagic.util.ParentSetList;
import java.util.List;

/**
 * Library declarative region.
 */
//TODO: rename class to Library
public class LibraryDeclarativeRegion extends VhdlElement implements DeclarativeRegion, NamedEntity {

    private final List<VhdlFile> files = ParentSetList.create(this);
    private String identifier;
    private final Resolvable resolvable = new ResolvableImpl();
    private final Scope scope = Scopes.createScope(this, resolvable);

    /**
     * Creates a library declarative region.
     * @param identifier the identifier of the library
     */
    public LibraryDeclarativeRegion(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the identifier of this library declarative region.
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier of this libraray declarative region.
     * @param identifier the identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns a list of files included in this libray.
     * @return a modifiable list of files
     */
    public List<VhdlFile> getFiles() {
        return files;
    }

    public Scope getScope() {
        return scope;
    }

    private class ResolvableImpl implements Resolvable {
        public Object resolve(String identifier) {
            for (VhdlFile file : files) {
                for (LibraryUnit libraryUnit : file.getElements()) {
                    if (libraryUnit instanceof NamedEntity) {
                        NamedEntity ie = (NamedEntity) libraryUnit;
                        if (ie.getIdentifier().equalsIgnoreCase(identifier)) {
                            return ie;
                        }
                    }
                }
            }

            return null;
        }
    }
}

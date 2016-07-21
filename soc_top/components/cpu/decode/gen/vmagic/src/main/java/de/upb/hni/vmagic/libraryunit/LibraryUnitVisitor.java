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

import java.util.List;

/**
 * Library unit visitor.
 * The library unit visits all library units in a list of library units.
 * To use this class you need to subclass it and override the <code>visit...()</code> methods
 * you want to handle.
 */
public class LibraryUnitVisitor {

    /**
     * Visits a library unit.
     * No visit method is called when the parameter is <code>null</code>.
     * @param unit the library unit
     */
    public void visit(LibraryUnit unit) {
        if (unit != null) {
            unit.accept(this);
        }
    }

    /**
     * Visits a list of library units.
     * <code>null</code> items in the list are ignored.
     * The list parameter must not be <code>null</code>.
     * @param units the list of units
     */
    public void visit(List<? extends LibraryUnit> units) {
        for (LibraryUnit unit : units) {
            if (unit != null) {
                unit.accept(this);
            }
        }
    }

    /**
     * Visits an architecture.
     * @param architecture the architecture
     */
    protected void visitArchitecture(Architecture architecture) {
    }

    /**
     * Visits a configuration.
     * @param configuration the configuration
     */
    protected void visitConfiguration(Configuration configuration) {
    }

    /**
     * Visits an entity.
     * @param entity the entity
     */
    protected void visitEntity(Entity entity) {
    }

    /**
     * Visits a package body.
     * @param packageBody the package body
     */
    protected void visitPackageBody(PackageBody packageBody) {
    }

    /**
     * Visits a package declaration.
     * @param packageDeclaration the pacakge declaration
     */
    protected void visitPackageDeclaration(PackageDeclaration packageDeclaration) {
    }

    /**
     * Visits a library clause.
     * @param libraryClause the library clause
     */
    //TODO: remove: library clause is no library unit
    protected void visitLibraryClause(LibraryClause libraryClause) {
    }

    /**
     * Visits a use clause
     * @param useClause the use clause
     */
    protected void visitUseClause(UseClause useClause) {
    }
}

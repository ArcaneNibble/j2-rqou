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

package de.upb.hni.vmagic.output;

import de.upb.hni.vmagic.concurrent.ConcurrentStatementVisitor;
import de.upb.hni.vmagic.configuration.ConfigurationVisitor;
import de.upb.hni.vmagic.declaration.DeclarationVisitor;
import de.upb.hni.vmagic.expression.ExpressionVisitor;
import de.upb.hni.vmagic.libraryunit.LibraryUnitVisitor;
import de.upb.hni.vmagic.statement.SequentialStatementVisitor;
import de.upb.hni.vmagic.type.TypeVisitor;

class VhdlOutputModule extends OutputModule {

    private final SequentialStatementVisitor sequentialStatementVisitor;
    private final ConcurrentStatementVisitor concurrentStatementVisitor;
    private final LibraryUnitVisitor libraryUnitVisitor;
    private final DeclarationVisitor declarationVisitor;
    private final ExpressionVisitor expressionVisitor;
    private final ConfigurationVisitor configurationVisitor;
    private final TypeVisitor typeVisitor;
    private final MiscellaneousElementOutput miscellaneousElementOutput;

    VhdlOutputModule(VhdlWriter writer) {
        sequentialStatementVisitor = new VhdlSequentialStatementVisitor(writer, this);
        concurrentStatementVisitor = new VhdlConcurrentStatementVisitor(writer, this);
        libraryUnitVisitor = new VhdlLibraryUnitVisitor(writer, this);
        declarationVisitor = new VhdlDeclarationVisitor(writer, this);
        expressionVisitor = new VhdlExpressionVisitor(writer, this);
        configurationVisitor = new VhdlConfigurationVisitor(writer, this);
        typeVisitor = new VhdlTypeVisitor(writer, this);
        miscellaneousElementOutput = new VhdlMiscellaneousElementOutput(writer, this);
    }

    protected SequentialStatementVisitor getSequentialStatementVisitor() {
        return sequentialStatementVisitor;
    }

    protected ConcurrentStatementVisitor getConcurrentStatementVisitor() {
        return concurrentStatementVisitor;
    }

    protected LibraryUnitVisitor getLibraryUnitVisitor() {
        return libraryUnitVisitor;
    }

    protected DeclarationVisitor getDeclarationVisitor() {
        return declarationVisitor;
    }

    protected ExpressionVisitor getExpressionVisitor() {
        return expressionVisitor;
    }

    protected ConfigurationVisitor getConfigurationVisitor() {
        return configurationVisitor;
    }

    protected TypeVisitor getTypeVisitor() {
        return typeVisitor;
    }

    protected MiscellaneousElementOutput getMiscellaneousElementOutput() {
        return miscellaneousElementOutput;
    }
}

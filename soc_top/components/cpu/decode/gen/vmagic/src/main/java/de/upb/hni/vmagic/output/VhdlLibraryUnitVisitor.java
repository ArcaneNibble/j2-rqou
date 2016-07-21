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

import de.upb.hni.vmagic.libraryunit.Architecture;
import de.upb.hni.vmagic.libraryunit.Configuration;
import de.upb.hni.vmagic.libraryunit.Entity;
import de.upb.hni.vmagic.libraryunit.LibraryClause;
import de.upb.hni.vmagic.libraryunit.LibraryUnit;
import de.upb.hni.vmagic.libraryunit.LibraryUnitVisitor;
import de.upb.hni.vmagic.libraryunit.PackageBody;
import de.upb.hni.vmagic.libraryunit.PackageDeclaration;
import de.upb.hni.vmagic.libraryunit.UseClause;
import java.util.List;

/**
 * Library unit output visitor.
 */
class VhdlLibraryUnitVisitor extends LibraryUnitVisitor {

    private final VhdlWriter writer;
    private final OutputModule output;

    public VhdlLibraryUnitVisitor(VhdlWriter writer, OutputModule output) {
        this.writer = writer;
        this.output = output;
    }

    @Override
    public void visit(LibraryUnit unit) {
        VhdlOutputHelper.handleAnnotationsBefore(unit, writer);
        super.visit(unit);
        VhdlOutputHelper.handleAnnotationsAfter(unit, writer);
    }

    @Override
    public void visit(List<? extends LibraryUnit> units) {
        for (LibraryUnit unit : units) {
            visit(unit);
        }
    }

    @Override
    protected void visitArchitecture(Architecture architecture) {
        writer.append(Keyword.ARCHITECTURE).append(' ');
        writer.appendIdentifier(architecture).append(' ');
        writer.append(Keyword.OF).append(' ');
        writer.appendIdentifier(architecture.getEntity()).append(' ');
        writer.append(Keyword.IS).newLine().indent();
        output.writeDeclarationMarkers(architecture.getDeclarations());
        writer.dedent().append(Keyword.BEGIN).newLine().indent();
        output.writeConcurrentStatements(architecture.getStatements());
        writer.dedent().append(Keyword.END).append(";").newLine();
    }

    @Override
    protected void visitConfiguration(Configuration configuration) {
        writer.append(Keyword.CONFIGURATION).append(' ');
        writer.appendIdentifier(configuration).append(' ');
        writer.append(Keyword.OF).append(' ');
        writer.appendIdentifier(configuration.getEntity()).append(' ');
        writer.append(Keyword.IS).newLine();

        writer.indent();
        output.writeDeclarationMarkers(configuration.getDeclarations());
        output.writeConfigurationItem(configuration.getBlockConfiguration());
        writer.dedent();

        writer.append(Keyword.END);
        if (writer.getFormat().isRepeatLabels()) {
            writer.append(' ').append(Keyword.CONFIGURATION);
            writer.append(' ').appendIdentifier(configuration.getEntity());
        }
        writer.append(";").newLine();
    }

    @Override
    protected void visitEntity(Entity entity) {
        writer.append(Keyword.ENTITY).append(' ');
        writer.appendIdentifier(entity).append(' ');
        writer.append(Keyword.IS).newLine();
        writer.indent();
        if (!entity.getGeneric().isEmpty()) {
            output.getMiscellaneousElementOutput().generic(entity.getGeneric());
        }
        if (!entity.getPort().isEmpty()) {
            output.getMiscellaneousElementOutput().port(entity.getPort());
        }
        output.writeDeclarationMarkers(entity.getDeclarations());
        writer.dedent();
        if (!entity.getStatements().isEmpty()) {
            writer.append(Keyword.BEGIN).newLine().indent();
            output.writeConcurrentStatements(entity.getStatements());
            writer.dedent();
        }
        writer.append(Keyword.END);
        if (writer.getFormat().isRepeatLabels()) {
            writer.append(' ').append(Keyword.ENTITY);
            writer.append(' ').appendIdentifier(entity);
        }
        writer.append(";").newLine();
    }

    @Override
    protected void visitPackageBody(PackageBody packageBody) {
        writer.append(Keyword.PACKAGE, Keyword.BODY).append(' ');
        writer.appendIdentifier(packageBody.getPackage()).append(' ');
        writer.append(Keyword.IS).newLine();

        writer.indent();
        output.writeDeclarationMarkers(packageBody.getDeclarations());
        writer.dedent();

        writer.append(Keyword.END);
        if (writer.getFormat().isRepeatLabels()) {
            writer.append(' ').append(Keyword.PACKAGE, Keyword.BODY);
            writer.append(' ').appendIdentifier(packageBody.getPackage());
        }
        writer.append(";").newLine();
    }

    @Override
    protected void visitPackageDeclaration(PackageDeclaration packageDeclaration) {
        writer.append(Keyword.PACKAGE).append(' ');
        writer.appendIdentifier(packageDeclaration).append(' ');
        writer.append(Keyword.IS).newLine();

        writer.indent();
        output.writeDeclarationMarkers(packageDeclaration.getDeclarations());
        writer.dedent();

        writer.append(Keyword.END);
        if (writer.getFormat().isRepeatLabels()) {
            writer.append(' ').append(Keyword.PACKAGE);
            writer.append(' ').appendIdentifier(packageDeclaration);
        }
        writer.append(";").newLine();
    }

    @Override
    protected void visitLibraryClause(LibraryClause libraryClause) {
        writer.append(Keyword.LIBRARY).append(' ');
        writer.appendStrings(libraryClause.getLibraries(), ", ");
        writer.append(";").newLine();

    }

    @Override
    protected void visitUseClause(UseClause useClause) {
        writer.append(Keyword.USE).append(' ');
        writer.appendStrings(useClause.getDeclarations(), ", ");
        writer.append(';').newLine();
    }
}

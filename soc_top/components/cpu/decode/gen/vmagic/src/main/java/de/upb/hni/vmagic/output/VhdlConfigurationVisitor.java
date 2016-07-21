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

import de.upb.hni.vmagic.configuration.ConfigurationItem;
import de.upb.hni.vmagic.libraryunit.UseClause;
import de.upb.hni.vmagic.configuration.AbstractBlockConfiguration;
import de.upb.hni.vmagic.configuration.ArchitectureConfiguration;
import de.upb.hni.vmagic.configuration.BlockStatementConfiguration;
import de.upb.hni.vmagic.configuration.ComponentConfiguration;
import de.upb.hni.vmagic.configuration.ConfigurationVisitor;
import de.upb.hni.vmagic.configuration.GenerateStatementConfiguration;
import java.util.List;

/**
 * Vhdl output configuration visitor.
 */
class VhdlConfigurationVisitor extends ConfigurationVisitor {

    private final VhdlWriter writer;
    private final OutputModule output;

    public VhdlConfigurationVisitor(VhdlWriter writer, OutputModule output) {
        this.writer = writer;
        this.output = output;
    }

    @Override
    public void visit(ConfigurationItem item) {
        VhdlOutputHelper.handleAnnotationsBefore(item, writer);
        super.visit(item);
        VhdlOutputHelper.handleAnnotationsAfter(item, writer);
    }

    @Override
    public void visit(List<? extends ConfigurationItem> items) {
        for (ConfigurationItem item : items) {
            visit(item);
        }
    }

    private void appendBlockConfiguration(AbstractBlockConfiguration block, String blockSpecification) {
        writer.append(Keyword.FOR).append(' ');
        writer.append(blockSpecification);
        writer.newLine().indent();

        for (UseClause useClause : block.getUseClauses()) {
            output.writeLibraryUnit(useClause);
        }
        visit(block.getConfigurationItems());

        writer.dedent().append(Keyword.END, Keyword.FOR).append(";").newLine();
    }

    @Override
    protected void visitArchitectureConfiguration(ArchitectureConfiguration configuration) {
        String blockIdentifier;
        if (configuration.getArchitecture() != null) {
            blockIdentifier = configuration.getArchitecture().getIdentifier();
        } else {
            blockIdentifier = "null";
        }
        appendBlockConfiguration(configuration, blockIdentifier);
    }

    @Override
    protected void visitBlockStatementConfiguration(BlockStatementConfiguration configuration) {
        appendBlockConfiguration(configuration, configuration.getBlock().getLabel());
    }

    @Override
    protected void visitComponentConfiguration(ComponentConfiguration configuration) {
        writer.append(Keyword.FOR).append(' ');
        output.writeComponentSpecification(configuration.getComponentSpecification());
        writer.indent();

        boolean hasBindingIndication = false;

        if (configuration.getEntityAspect() != null) {
            hasBindingIndication = true;
            writer.newLine().append(Keyword.USE).append(' ');
            writer.append(configuration.getEntityAspect().toString());
        }

        if (!configuration.getGenericMap().isEmpty()) {
            hasBindingIndication = true;
            writer.newLine();
            writer.append(Keyword.GENERIC, Keyword.MAP).append(" (").newLine();
            writer.indent().beginAlign();
            output.getMiscellaneousElementOutput().genericMap(configuration.getGenericMap());
            writer.endAlign().dedent();
            writer.append(")");
        }

        if (!configuration.getPortMap().isEmpty()) {
            hasBindingIndication = true;
            writer.newLine();
            writer.append(Keyword.PORT, Keyword.MAP).append(" (").newLine();
            writer.indent().beginAlign();
            output.getMiscellaneousElementOutput().portMap(configuration.getPortMap());
            writer.endAlign().dedent();
            writer.append(")");
        }

        if (hasBindingIndication) {
            writer.append(";");
        }

        writer.newLine();

        if (configuration.getBlockConfiguration() != null) {
            visit(configuration.getBlockConfiguration());
        }
        writer.dedent().append(Keyword.END, Keyword.FOR);
        writer.append(';').newLine();
    }

    @Override
    protected void visitGenerateStatementConfiguration(GenerateStatementConfiguration configuration) {
        appendBlockConfiguration(configuration, configuration.getGenerateStatement().getLabel());
    }
}

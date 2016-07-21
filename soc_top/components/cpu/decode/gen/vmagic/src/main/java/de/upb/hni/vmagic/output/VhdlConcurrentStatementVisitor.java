/*
 * Copyright 2009, 2010, 2011 University of Paderborn
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

import de.upb.hni.vmagic.Annotations;
import de.upb.hni.vmagic.Choice;
import de.upb.hni.vmagic.annotation.ComponentInstantiationFormat;
import de.upb.hni.vmagic.annotation.OptionalIsFormat;
import de.upb.hni.vmagic.concurrent.AbstractComponentInstantiation;
import de.upb.hni.vmagic.concurrent.AbstractGenerateStatement;
import de.upb.hni.vmagic.concurrent.AbstractProcessStatement;
import de.upb.hni.vmagic.concurrent.ArchitectureInstantiation;
import de.upb.hni.vmagic.concurrent.BlockStatement;
import de.upb.hni.vmagic.concurrent.ComponentInstantiation;
import de.upb.hni.vmagic.concurrent.ConcurrentAssertionStatement;
import de.upb.hni.vmagic.concurrent.ConcurrentProcedureCall;
import de.upb.hni.vmagic.concurrent.ConcurrentStatement;
import de.upb.hni.vmagic.concurrent.ConcurrentStatementVisitor;
import de.upb.hni.vmagic.concurrent.ConditionalSignalAssignment;
import de.upb.hni.vmagic.concurrent.ConfigurationInstantiation;
import de.upb.hni.vmagic.concurrent.EntityInstantiation;
import de.upb.hni.vmagic.concurrent.ForGenerateStatement;
import de.upb.hni.vmagic.concurrent.IfGenerateStatement;
import de.upb.hni.vmagic.concurrent.SelectedSignalAssignment;
import de.upb.hni.vmagic.libraryunit.Entity;
import de.upb.hni.vmagic.object.Signal;
import java.util.List;

/**
 * Concurrent statement output visitor.
 */
class VhdlConcurrentStatementVisitor extends ConcurrentStatementVisitor {

    private final VhdlWriter writer;
    private final OutputModule output;

    public VhdlConcurrentStatementVisitor(VhdlWriter writer, OutputModule output) {
        this.writer = writer;
        this.output = output;
    }

    @Override
    public void visit(ConcurrentStatement statement) {
        VhdlOutputHelper.handleAnnotationsBefore(statement, writer);
        super.visit(statement);
        VhdlOutputHelper.handleAnnotationsAfter(statement, writer);
    }

    @Override
    public void visit(List<? extends ConcurrentStatement> statements) {
        for (ConcurrentStatement statement : statements) {
            visit(statement);
        }
    }

    private void appendLabel(ConcurrentStatement statement) {
        if (statement.getLabel() != null) {
            writer.append(statement.getLabel()).append(" : ");
        }
    }

    private void appendComponentInstantiationMaps(AbstractComponentInstantiation instantiation) {
        if (!instantiation.getGenericMap().isEmpty()) {
            writer.newLine();
            writer.append(Keyword.GENERIC, Keyword.MAP).append(" (").newLine();
            writer.indent().beginAlign();
            output.getMiscellaneousElementOutput().genericMap(instantiation.getGenericMap());
            writer.endAlign().dedent();
            writer.append(")");
        }

        if (!instantiation.getPortMap().isEmpty()) {
            writer.newLine();
            writer.append(Keyword.PORT, Keyword.MAP).append(" (").newLine();
            writer.indent().beginAlign();
            output.getMiscellaneousElementOutput().portMap(instantiation.getPortMap());
            writer.endAlign().dedent();
            writer.append(")");
        }
    }

    private void appendGenerateStatementSuffix(AbstractGenerateStatement statement) {
        writer.append(' ').append(Keyword.GENERATE).newLine();
        if (!statement.getDeclarations().isEmpty()) {
            writer.indent();
            output.writeDeclarationMarkers(statement.getDeclarations());
            writer.dedent().append(Keyword.BEGIN).newLine();
        }
        writer.indent();
        visit(statement.getStatements());
        writer.dedent();
        writer.append(Keyword.END, Keyword.GENERATE);
        if (writer.getFormat().isRepeatLabels()) {
            writer.append(' ').append(statement.getLabel());
        }
        writer.append(";").newLine();
    }

    @Override
    protected void visitArchitectureInstantiation(ArchitectureInstantiation statement) {
        appendLabel(statement);
        Entity entity = statement.getArchitecture().getEntity();
        writer.append(Keyword.ENTITY).append(' ').appendIdentifier(entity);
        writer.append('(').appendIdentifier(statement.getArchitecture()).append(')');
        writer.indent();
        appendComponentInstantiationMaps(statement);
        writer.dedent().append(';').newLine();
    }

    @Override
    protected void visitBlockStatement(BlockStatement statement) {
        appendLabel(statement);
        writer.append(Keyword.BLOCK);
        if (statement.getGuardExpression() != null) {
            writer.append("(");
            output.writeExpression(statement.getGuardExpression());
            writer.append(")");
        }

        OptionalIsFormat format = Annotations.getAnnotation(statement, OptionalIsFormat.class);
        if (format != null && format.isUseIs()) {
            writer.append(' ').append(Keyword.IS);
        }

        writer.newLine().indent();
        if (!statement.getGeneric().isEmpty()) {
            output.getMiscellaneousElementOutput().generic(statement.getGeneric());
            if (!statement.getGenericMap().isEmpty()) {
                writer.append(Keyword.GENERIC, Keyword.MAP).append(" (").newLine();
                writer.indent().beginAlign();
                output.getMiscellaneousElementOutput().genericMap(statement.getGenericMap());
                writer.endAlign().dedent();
                writer.append(");").newLine();
            }
        }

        if (!statement.getPort().isEmpty()) {
            output.getMiscellaneousElementOutput().port(statement.getPort());
            if (!statement.getPortMap().isEmpty()) {
                writer.append(Keyword.PORT, Keyword.MAP).append(" (").newLine();
                writer.indent().beginAlign();
                output.getMiscellaneousElementOutput().portMap(statement.getPortMap());
                writer.endAlign().dedent();
                writer.append(");").newLine();
            }
        }

        output.writeDeclarationMarkers(statement.getDeclarations());
        writer.dedent().append(Keyword.BEGIN).newLine();
        writer.indent();
        visit(statement.getStatements());
        writer.dedent();
        writer.append(Keyword.END, Keyword.BLOCK);
        if (writer.getFormat().isRepeatLabels()) {
            writer.append(' ').append(statement.getLabel());
        }
        writer.append(";").newLine();
    }

    @Override
    protected void visitComponentInstantiation(ComponentInstantiation statement) {
        appendLabel(statement);

        ComponentInstantiationFormat format =
                Annotations.getAnnotation(statement, ComponentInstantiationFormat.class);

        if (format != null && format.isUseOptionalComponentKeyword()) {
            writer.append(Keyword.COMPONENT).append(' ');
        }

        writer.appendIdentifier(statement.getComponent()).indent();
        appendComponentInstantiationMaps(statement);
        writer.dedent().append(';').newLine();
    }

    @Override
    protected void visitConcurrentAssertionStatement(ConcurrentAssertionStatement statement) {
        appendLabel(statement);
        if (statement.isPostponed()) {
            writer.append(Keyword.POSTPONED).append(' ');
        }
        writer.append(Keyword.ASSERT).append(' ');
        output.writeExpression(statement.getCondition());
        if (statement.getReportedExpression() != null) {
            writer.append(' ').append(Keyword.REPORT).append(' ');
            output.writeExpression(statement.getReportedExpression());
        }
        if (statement.getSeverity() != null) {
            writer.append(' ').append(Keyword.SEVERITY).append(' ');
            output.writeExpression(statement.getSeverity());
        }
        writer.append(";").newLine();
    }

    @Override
    protected void visitConcurrentProcedureCall(ConcurrentProcedureCall statement) {
        appendLabel(statement);
        if (statement.isPostponed()) {
            writer.append(Keyword.POSTPONED).append(' ');
        }
        writer.append(statement.getProcedure());
        if (!statement.getParameters().isEmpty()) {
            writer.append('(');
            output.getMiscellaneousElementOutput().concurrentProcedureCallParameters(statement.getParameters());
            writer.append(')');
        }
        writer.append(';').newLine();
    }

    @Override
    protected void visitConditionalSignalAssignment(ConditionalSignalAssignment statement) {
        appendLabel(statement);
        if (statement.isPostponed()) {
            writer.append(Keyword.POSTPONED).append(' ');
        }

        output.writeSignalAssignmentTarget(statement.getTarget());
        writer.append(" <= ");

        if (statement.isGuarded()) {
            writer.append(Keyword.GUARDED).append(' ');
        }

        if (statement.getDelayMechanism() != null) {
            output.getMiscellaneousElementOutput().delayMechanism(statement.getDelayMechanism());
            writer.append(' ');
        }

        boolean first = true;
        for (ConditionalSignalAssignment.ConditionalWaveformElement element : statement.getConditionalWaveforms()) {
            if (first) {
                first = false;
            } else {
                writer.append(' ').append(Keyword.ELSE).append(' ');
            }

            output.getMiscellaneousElementOutput().waveform(element.getWaveform());

            if (element.getCondition() != null) {
                writer.append(' ').append(Keyword.WHEN).append(' ');
                output.writeExpression(element.getCondition());
            }
        }
        writer.append(';').newLine();
    }

    @Override
    protected void visitConfigurationInstantiation(ConfigurationInstantiation statement) {
        appendLabel(statement);
        writer.append(Keyword.CONFIGURATION).append(' ');
        writer.appendIdentifier(statement.getConfiguration()).indent();
        appendComponentInstantiationMaps(statement);
        writer.dedent().append(';').newLine();
    }

    @Override
    protected void visitEntityInstantiation(EntityInstantiation statement) {
        appendLabel(statement);
        writer.append(Keyword.ENTITY).append(' ');
        writer.appendIdentifier(statement.getEntity()).indent();
        appendComponentInstantiationMaps(statement);
        writer.dedent().append(';').newLine();
    }

    @Override
    protected void visitForGenerateStatement(ForGenerateStatement statement) {
        appendLabel(statement);
        writer.append(Keyword.FOR).append(' ');
        output.writeExpression(statement.getParameter());
        writer.append(' ').append(Keyword.IN).append(' ');
        output.writeDiscreteRange(statement.getRange());
        appendGenerateStatementSuffix(statement);
    }

    @Override
    protected void visitIfGenerateStatement(IfGenerateStatement statement) {
        appendLabel(statement);
        writer.append(Keyword.IF).append(' ');
        output.writeExpression(statement.getCondition());
        appendGenerateStatementSuffix(statement);
    }

    @Override
    protected void visitProcessStatement(AbstractProcessStatement statement) {
        appendLabel(statement);
        if (statement.isPostponed()) {
            writer.append(Keyword.POSTPONED).append(' ');
        }
        writer.append(Keyword.PROCESS);
        if (!statement.getSensitivityList().isEmpty()) {
            writer.append("(");
            boolean first = true;
            for (Signal signal : statement.getSensitivityList()) {
                if (first) {
                    first = false;
                } else {
                    writer.append(", ");
                }
                if (signal == null) {
                    writer.append("null");
                } else {
                    writer.appendIdentifier(signal);
                }
            }
            writer.append(")");
        }

        OptionalIsFormat format = Annotations.getAnnotation(statement, OptionalIsFormat.class);
        if (format != null && format.isUseIs()) {
            writer.append(' ').append(Keyword.IS);
        }

        writer.newLine().indent();
        output.writeDeclarationMarkers(statement.getDeclarations());
        writer.dedent().append(Keyword.BEGIN).newLine().indent();
        output.writeSequentialStatements(statement.getStatements());
        writer.dedent().append(Keyword.END);
        if (statement.isPostponed() && writer.getFormat().isRepeatLabels()) {
            writer.append(' ').append(Keyword.POSTPONED);
        }
        writer.append(' ');
        writer.append(Keyword.PROCESS);
        if (statement.getLabel() != null && writer.getFormat().isRepeatLabels()) {
            writer.append(' ').append(statement.getLabel());
        }
        writer.append(";").newLine();
    }

    @Override
    protected void visitSelectedSignalAssignment(SelectedSignalAssignment statement) {
        appendLabel(statement);
        if (statement.isPostponed()) {
            writer.append(Keyword.POSTPONED).append(' ');
        }
        writer.append(Keyword.WITH).append(' ');
        output.writeExpression(statement.getExpression());
        writer.append(' ').append(Keyword.SELECT);
        writer.newLine().indent();
        output.writeSignalAssignmentTarget(statement.getTarget());
        writer.append(" <=");
        if (statement.isGuarded()) {
            writer.append(' ').append(Keyword.GUARDED);
        }
        if (statement.getDelayMechanism() != null) {
            writer.append(' ');
            output.getMiscellaneousElementOutput().delayMechanism(statement.getDelayMechanism());
        }
        writer.newLine().indent();

        boolean first = true;
        for (SelectedSignalAssignment.SelectedWaveform waveform : statement.getSelectedWaveforms()) {
            if (first) {
                first = false;
            } else {
                writer.append(',').newLine();
            }

            output.getMiscellaneousElementOutput().waveform(waveform.getWaveform());

            writer.append(' ').append(Keyword.WHEN).append(' ');

            boolean first3 = true;
            for (Choice choice : waveform.getChoices()) {
                if (first3) {
                    first3 = false;
                } else {
                    writer.append(" | ");
                }
                output.writeChoice(choice);
            }
        }
        writer.append(';').dedent().dedent().newLine();
    }
}

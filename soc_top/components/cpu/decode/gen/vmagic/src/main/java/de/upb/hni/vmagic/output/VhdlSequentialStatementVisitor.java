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

import de.upb.hni.vmagic.Choice;
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.statement.AssertionStatement;
import de.upb.hni.vmagic.statement.CaseStatement;
import de.upb.hni.vmagic.statement.CaseStatement.Alternative;
import de.upb.hni.vmagic.statement.ExitStatement;
import de.upb.hni.vmagic.statement.ForStatement;
import de.upb.hni.vmagic.statement.IfStatement;
import de.upb.hni.vmagic.statement.IfStatement.ElsifPart;
import de.upb.hni.vmagic.statement.LoopStatement;
import de.upb.hni.vmagic.statement.NextStatement;
import de.upb.hni.vmagic.statement.NullStatement;
import de.upb.hni.vmagic.statement.ProcedureCall;
import de.upb.hni.vmagic.statement.ReportStatement;
import de.upb.hni.vmagic.statement.ReturnStatement;
import de.upb.hni.vmagic.statement.SequentialStatement;
import de.upb.hni.vmagic.statement.SequentialStatementVisitor;
import de.upb.hni.vmagic.statement.SignalAssignment;
import de.upb.hni.vmagic.statement.VariableAssignment;
import de.upb.hni.vmagic.statement.WaitStatement;
import de.upb.hni.vmagic.statement.WhileStatement;
import java.util.List;

/**
 * Sequential statement output visitor.
 */
class VhdlSequentialStatementVisitor extends SequentialStatementVisitor {

    private final VhdlWriter writer;
    private final OutputModule output;

    public VhdlSequentialStatementVisitor(VhdlWriter writer, OutputModule output) {
        this.writer = writer;
        this.output = output;
    }

    @Override
    public void visit(SequentialStatement statement) {
        VhdlOutputHelper.handleAnnotationsBefore(statement, writer);
        super.visit(statement);
        VhdlOutputHelper.handleAnnotationsAfter(statement, writer);
    }

    @Override
    public void visit(List<? extends SequentialStatement> statements) {
        for (SequentialStatement statement : statements) {
            visit(statement);
        }
    }

    private void appendLabel(SequentialStatement statement) {
        if (statement.getLabel() != null) {
            writer.append(statement.getLabel()).append(" : ");
        }
    }

    private void appendLoopPart(LoopStatement statement) {
        writer.append(Keyword.LOOP).newLine().indent();
        visit(statement.getStatements());
        writer.dedent().append(Keyword.END, Keyword.LOOP).append(';').newLine();
    }

    private void appendExitOrNextStatement(SequentialStatement statement,
            Keyword keyword, LoopStatement loop, Expression condition) {
        appendLabel(statement);
        writer.append(keyword);
        if (loop != null) {
            String label = loop.getLabel();

            if (label == null) {
                //FIXME: unify handling of null values
                throw new NullPointerException("Loop label is null");
            }

            writer.append(' ').append(label);
        }
        if (condition != null) {
            writer.append(' ').append(Keyword.WHEN).append(' ');
            output.writeExpression(condition);
        }
        writer.append(';').newLine();
    }

    @Override
    protected void visitAssertionStatement(AssertionStatement statement) {
        appendLabel(statement);
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
        writer.append(';').newLine();
    }

    @Override
    protected void visitCaseStatement(CaseStatement statement) {
        appendLabel(statement);
        writer.append(Keyword.CASE).append(' ');
        output.writeExpression(statement.getExpression());
        writer.append(' ').append(Keyword.IS).newLine();

        writer.indent();
        for (CaseStatement.Alternative alternative : statement.getAlternatives()) {
            visitCaseStatementAlternative(alternative);
        }
        writer.dedent();

        writer.append(Keyword.END, Keyword.CASE);

        if (writer.getFormat().isRepeatLabels() && statement.getLabel() != null) {
            writer.append(' ').append(statement.getLabel());
        }

        writer.append(';').newLine();
    }

    @Override
    protected void visitCaseStatementAlternative(Alternative alternative) {
        writer.append(Keyword.WHEN).append(' ');

        boolean first = true;
        for (Choice choice : alternative.getChoices()) {
            if (first) {
                first = false;
            } else {
                writer.append(" | ");
            }
            output.writeChoice(choice);
        }
        writer.append(" =>").newLine();

        writer.indent();
        output.writeSequentialStatements(alternative.getStatements());
        writer.dedent();
        writer.newLine();
    }

    @Override
    protected void visitExitStatement(ExitStatement statement) {
        appendExitOrNextStatement(statement, Keyword.EXIT, statement.getLoop(),
                statement.getCondition());
    }

    @Override
    protected void visitForStatement(ForStatement statement) {
        appendLabel(statement);
        writer.append(Keyword.FOR).append(' ');
        output.writeExpression(statement.getParameter());
        writer.append(' ').append(Keyword.IN).append(' ');
        output.writeDiscreteRange(statement.getRange());
        writer.append(' ');
        appendLoopPart(statement);
    }

    @Override
    protected void visitIfStatement(IfStatement statement) {
        appendLabel(statement);
        writer.append(Keyword.IF).append(' ');
        output.writeExpression(statement.getCondition());
        writer.append(' ').append(Keyword.THEN).newLine();
        writer.indent();
        visit(statement.getStatements());
        writer.dedent();
        for (IfStatement.ElsifPart elsifPart : statement.getElsifParts()) {
            visitIfStatementElsifPart(elsifPart);
        }
        if (!statement.getElseStatements().isEmpty()) {
            writer.append(Keyword.ELSE).newLine();
            writer.indent();
            visit(statement.getElseStatements());
            writer.dedent();
        }
        writer.append(Keyword.END, Keyword.IF);
        if (statement.getLabel() != null && writer.getFormat().isRepeatLabels()) {
            writer.append(' ').append(statement.getLabel());
        }
        writer.append(';').newLine();
    }

    @Override
    protected void visitIfStatementElsifPart(ElsifPart part) {
        writer.append(Keyword.ELSIF).append(' ');
        output.writeExpression(part.getCondition());
        writer.append(' ').append(Keyword.THEN).newLine();
        writer.indent();
        visit(part.getStatements());
        writer.dedent();
    }

    @Override
    protected void visitLoopStatement(LoopStatement statement) {
        appendLabel(statement);
        appendLoopPart(statement);
    }

    @Override
    protected void visitNextStatement(NextStatement statement) {
        appendExitOrNextStatement(statement, Keyword.NEXT, statement.getLoop(),
                statement.getCondition());
    }

    @Override
    protected void visitNullStatement(NullStatement statement) {
        appendLabel(statement);
        writer.append(Keyword.NULL).append(';').newLine();
    }

    @Override
    protected void visitProcedureCall(ProcedureCall statement) {
        appendLabel(statement);
        writer.append(statement.getProcedure());
        if (!statement.getParameters().isEmpty()) {
            writer.append('(');
            output.getMiscellaneousElementOutput().procedureCallParameters(statement.getParameters());
            writer.append(')');
        }
        writer.append(';').newLine();
    }

    @Override
    protected void visitReportStatement(ReportStatement statement) {
        appendLabel(statement);
        writer.append(Keyword.REPORT).append(' ');
        output.writeExpression(statement.getReportExpression());
        if (statement.getSeverity() != null) {
            writer.append(' ').append(Keyword.SEVERITY).append(' ');
            output.writeExpression(statement.getSeverity());
        }
        writer.append(';').newLine();
    }

    @Override
    protected void visitReturnStatement(ReturnStatement statement) {
        appendLabel(statement);
        writer.append(Keyword.RETURN);
        if (statement.getReturnedExpression() != null) {
            writer.append(' ');
            output.writeExpression(statement.getReturnedExpression());
        }
        writer.append(';').newLine();
    }

    @Override
    protected void visitSignalAssignment(SignalAssignment statement) {
        appendLabel(statement);
        output.writeSignalAssignmentTarget(statement.getTarget());
        writer.append(" <= ");

        if (statement.getDelayMechanism() != null) {
            output.getMiscellaneousElementOutput().delayMechanism(statement.getDelayMechanism());
            writer.append(' ');
        }

        output.getMiscellaneousElementOutput().waveform(statement.getWaveform());
        writer.append(";").newLine();
    }

    @Override
    protected void visitVariableAssignment(VariableAssignment statement) {
        appendLabel(statement);
        output.writeVariableAssignmentTarget(statement.getTarget());
        writer.append(" := ");
        output.writeExpression(statement.getValue());
        writer.append(";").newLine();
    }

    @Override
    protected void visitWaitStatement(WaitStatement statement) {
        appendLabel(statement);
        writer.append(Keyword.WAIT);
        if (!statement.getSensitivityList().isEmpty()) {
            writer.append(' ').append(Keyword.ON).append(' ');
            writer.appendIdentifiers(statement.getSensitivityList(), ", ");
        }
        if (statement.getCondition() != null) {
            writer.append(' ').append(Keyword.UNTIL).append(' ');
            output.writeExpression(statement.getCondition());
        }
        if (statement.getTimeout() != null) {
            writer.append(' ').append(Keyword.FOR).append(' ');
            output.writeExpression(statement.getTimeout());
        }
        writer.append(';').newLine();
    }

    @Override
    protected void visitWhileStatement(WhileStatement statement) {
        appendLabel(statement);
        writer.append(Keyword.WHILE).append(' ');
        output.writeExpression(statement.getCondition());
        writer.append(' ');
        appendLoopPart(statement);
    }
}

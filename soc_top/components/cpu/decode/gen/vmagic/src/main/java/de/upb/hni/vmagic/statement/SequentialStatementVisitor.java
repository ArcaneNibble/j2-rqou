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

package de.upb.hni.vmagic.statement;

import java.util.List;

/**
 * Sequential statement visitor.
 * The sequential statement visitor visits all statements in a hierarchy of statements.
 * To use this class you need to subclass it and override the <code>visit...()</code> methods
 * you want to handle. If you override the vist methods for loops, case- or if-statments you need
 * to call <code>super.visit...(statement)</code> to visit the child statements.
 */
public class SequentialStatementVisitor {

    /**
     * Visits a sequential statement.
     * No visit method is called when the parameter is <code>null</code>.
     * @param statement the statement
     */
    public void visit(SequentialStatement statement) {
        if (statement != null) {
            statement.accept(this);
        }
    }

    /**
     * Visits a list of sequential statements.
     * <code>null</code> items in the list are ignored.
     * The list parameter must not be <code>null</code>.
     * @param statements the list of statements
     */
    public void visit(List<? extends SequentialStatement> statements) {
        for (SequentialStatement statement : statements) {
            if (statement != null) {
                visit(statement);
            }
        }
    }

    /**
     * Visits a assertion statement.
     * @param statement the statement
     */
    protected void visitAssertionStatement(AssertionStatement statement) {
    }

    /**
     * Visits a case statement.
     * @param statement the statement
     */
    protected void visitCaseStatement(CaseStatement statement) {
        for (CaseStatement.Alternative alternative : statement.getAlternatives()) {
            visitCaseStatementAlternative(alternative);
        }
    }

    /**
     * Visits an alternative of a case statement.
     * @param alternative the alternative
     */
    protected void visitCaseStatementAlternative(CaseStatement.Alternative alternative) {
        visit(alternative.getStatements());
    }

    /**
     * Visits an exit statment.
     * @param statement the statement
     */
    protected void visitExitStatement(ExitStatement statement) {
    }

    /**
     * Visits a for statement.
     * @param statement the statement
     */
    protected void visitForStatement(ForStatement statement) {
        for (SequentialStatement s : statement.getStatements()) {
            visit(s);
        }
    }

    /**
     * Visits a if statement.
     * @param statement the statement
     */
    protected void visitIfStatement(IfStatement statement) {
        visit(statement.getStatements());
        for (IfStatement.ElsifPart elsifPart : statement.getElsifParts()) {
            visitIfStatementElsifPart(elsifPart);
        }
        visit(statement.getElseStatements());
    }

    /**
     * Visits the elsif part of a if statement.
     * @param part the elsif part
     */
    protected void visitIfStatementElsifPart(IfStatement.ElsifPart part) {
        visit(part.getStatements());
    }

    /**
     * Visits a loop statement.
     * @param statement the statement
     */
    protected void visitLoopStatement(LoopStatement statement) {
        for (SequentialStatement s : statement.getStatements()) {
            visit(s);
        }
    }

    /**
     * Visits a next statement.
     * @param statement the statement
     */
    protected void visitNextStatement(NextStatement statement) {
    }

    /**
     * Visits a null statement.
     * @param statement the statement
     */
    protected void visitNullStatement(NullStatement statement) {
    }

    /**
     * Visits a procedure call statement.
     * @param statement the statement
     */
    protected void visitProcedureCall(ProcedureCall statement) {
    }

    /**
     * Visits a report statement.
     * @param statement the statement
     */
    protected void visitReportStatement(ReportStatement statement) {
    }

    /**
     * Visits a return statement.
     * @param statement the statement
     */
    protected void visitReturnStatement(ReturnStatement statement) {
    }

    /**
     * Visits a signal assignment statement.
     * @param statement the statement
     */
    protected void visitSignalAssignment(SignalAssignment statement) {
    }

    /**
     * Visits a variable assignment statement.
     * @param statement the statement
     */
    protected void visitVariableAssignment(VariableAssignment statement) {
    }

    /**
     * Visits a wait statement.
     * @param statement the statement
     */
    protected void visitWaitStatement(WaitStatement statement) {
    }

    /**
     * Visits a while statement.
     * @param statement the statement
     */
    protected void visitWhileStatement(WhileStatement statement) {
        for (SequentialStatement s : statement.getStatements()) {
            visit(s);
        }
    }
}

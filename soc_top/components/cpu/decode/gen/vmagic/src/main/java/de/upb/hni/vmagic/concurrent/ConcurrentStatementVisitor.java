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

package de.upb.hni.vmagic.concurrent;

import java.util.List;

/**
 * Concurrent statement visitor.
 * The concurrent statement visitor visits all statements in a hierarchy of statements.
 * To use this class you need to subclass it and override the <code>visit...()</code> methods
 * you want to handle.
 */
public class ConcurrentStatementVisitor {

    /**
     * Visits a concurrent statement.
     * No visit method is called if the parameter equals <code>null</code>.
     * @param statement the concurrent statement or <code>null</code>
     */
    public void visit(ConcurrentStatement statement) {
        if (statement != null) {
            statement.accept(this);
        }
    }

    /**
     * Visits a list of concurrent statements.
     * <code>null</code> items in the list are ignored.
     * The list parameter must not be <code>null</code>.
     * @param statements the list of concurrent statements
     */
    public void visit(List<? extends ConcurrentStatement> statements) {
        for (ConcurrentStatement statement : statements) {
            if (statement != null) {
                statement.accept(this);
            }
        }
    }

    /**
     * Visits a process statement.
     * @param statement the statement
     */
    protected void visitProcessStatement(AbstractProcessStatement statement) {
    }

    /**
     * Visits a for generate statement.
     * @param statement the statement
     */
    protected void visitForGenerateStatement(ForGenerateStatement statement) {
    }

    /**
     * Visits a if generate statement.
     * @param statement the statement
     */
    protected void visitIfGenerateStatement(IfGenerateStatement statement) {
    }

    /**
     * Visits a concurrent procedure call.
     * @param statement the statement
     */
    protected void visitConcurrentProcedureCall(ConcurrentProcedureCall statement) {
    }

    /**
     * Visits an architecture instantiation.
     * @param statement the statement
     */
    protected void visitArchitectureInstantiation(ArchitectureInstantiation statement) {
    }

    /**
     * Visits a component instantiation.
     * @param statement the statement
     */
    protected void visitComponentInstantiation(ComponentInstantiation statement) {
    }

    /**
     * Visits a configuration instantiation.
     * @param statement the statement
     */
    protected void visitConfigurationInstantiation(ConfigurationInstantiation statement) {
    }

    /**
     * Visits an entity instantiation.
     * @param statement the statement
     */
    protected void visitEntityInstantiation(EntityInstantiation statement) {
    }

    /**
     * Visits a block statement.
     * @param statement the statement
     */
    protected void visitBlockStatement(BlockStatement statement) {
        visit(statement.getStatements());
    }

    /**
     * Visits a concurrent assertion statement.
     * @param statement the statement
     */
    protected void visitConcurrentAssertionStatement(ConcurrentAssertionStatement statement) {
    }

    /**
     * Visits a conditional signal assignment.
     * @param statement the statement
     */
    protected void visitConditionalSignalAssignment(ConditionalSignalAssignment statement) {
    }

    /**
     * Visits a selected signal assignement.
     * @param statement the statement
     */
    protected void visitSelectedSignalAssignment(SelectedSignalAssignment statement) {
    }
}

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

package de.upb.hni.vmagic.declaration;

import java.util.List;

/**
 * Declaration visitor.
 */
public class DeclarationVisitor {

    /**
     * Visits a declaration.
     * No visit method is called if the parameter is <code>null</code>.
     * @param declaration the declaration or <code>null</code>
     */
    public void visit(DeclarativeItem declaration) {
        if (declaration != null) {
            declaration.accept(this);
        }
    }

    /**
     * Visits a list of declarations.
     * <code>null</code> items in the list are ignored.
     * The list parameter must not be <code>null</code>.
     * @param declarations the list of declarations
     */
    public void visit(List<? extends DeclarativeItem> declarations) {
        for (DeclarativeItem declaration : declarations) {
            if (declaration != null) {
                declaration.accept(this);
            }
        }
    }

    /**
     * Vists an alias declaration.
     * @param declaration the alias declaration
     */
    protected void visitAliasDeclaration(Alias declaration) {
    }

    /**
     * Visits an attribute declaration.
     * @param declaration the attribute declaration
     */
    protected void visitAttributeDeclaration(Attribute declaration) {
    }

    /**
     * Visits an attribute specification.
     * @param specification the attribute specification
     */
    protected void visitAttributeSpecification(AttributeSpecification specification) {
    }

    /**
     * Visits a component declaration.
     * @param declaration the component declaration
     */
    protected void visitComponentDeclaration(Component declaration) {
    }

    /**
     * Visits a configuration specification.
     * @param specification the configuration specification
     */
    protected void visitConfigurationSpecification(ConfigurationSpecification specification) {
    }

    /**
     * Visits a constant declaration.
     * @param declaration the constant declaration
     */
    protected void visitConstantDeclaration(ConstantDeclaration declaration) {
    }

    /**
     * Visits a disconnection specification.
     * @param specification the disconnection specification
     */
    protected void visitDisconnectionSpecification(DisconnectionSpecification specification) {
    }

    /**
     * Visits a file declaration.
     * @param declaration the file declaration
     */
    protected void visitFileDeclaration(FileDeclaration declaration) {
    }

    /**
     * Visits a function body.
     * @param declaration the function body
     */
    protected void visitFunctionBody(FunctionBody declaration) {
    }

    /**
     * Visits a function declaration.
     * @param declaration the function declaration
     */
    protected void visitFunctionDeclaration(FunctionDeclaration declaration) {
    }

    /**
     * Visits a group declaration.
     * @param declaration the group declaration
     */
    protected void visitGroupDeclaration(Group declaration) {
    }

    /**
     * Visits a group template declaration.
     * @param declaration the group template declaration
     */
    protected void visitGroupTemplateDeclaration(GroupTemplate declaration) {
    }

    /**
     * Visits a procedure body.
     * @param declaration the procedure body
     */
    protected void visitProcedureBody(ProcedureBody declaration) {
    }

    /**
     * Visits a procedure declaration.
     * @param declaration the procedure declaration
     */
    protected void visitProcedureDeclaration(ProcedureDeclaration declaration) {
    }

    /**
     * Visits a signal declaration.
     * @param declaration the signal declaration
     */
    protected void visitSignalDeclaration(SignalDeclaration declaration) {
    }

    /**
     * Visits a subtype declaration.
     * @param declaration the subtype declaration
     */
    protected void visitSubtypeDeclaration(Subtype declaration) {
    }

    /**
     * Visits a variable declaration.
     * @param declaration the variable declaration
     */
    protected void visitVariableDeclaration(VariableDeclaration declaration) {
    }
}

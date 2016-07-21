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
import de.upb.hni.vmagic.Choices;
import de.upb.hni.vmagic.ComponentSpecification;
import de.upb.hni.vmagic.DiscreteRange;
import de.upb.hni.vmagic.Range;
import de.upb.hni.vmagic.RangeAttributeName;
import de.upb.hni.vmagic.SubtypeDiscreteRange;
import de.upb.hni.vmagic.VhdlElement;
import de.upb.hni.vmagic.VhdlFile;
import de.upb.hni.vmagic.concurrent.ConcurrentStatement;
import de.upb.hni.vmagic.concurrent.ConcurrentStatementVisitor;
import de.upb.hni.vmagic.configuration.ConfigurationItem;
import de.upb.hni.vmagic.configuration.ConfigurationVisitor;
import de.upb.hni.vmagic.declaration.DeclarationVisitor;
import de.upb.hni.vmagic.declaration.DeclarativeItem;
import de.upb.hni.vmagic.declaration.DeclarativeItemMarker;
import de.upb.hni.vmagic.declaration.Subtype;
import de.upb.hni.vmagic.expression.Aggregate;
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.expression.ExpressionVisitor;
import de.upb.hni.vmagic.libraryunit.LibraryUnit;
import de.upb.hni.vmagic.libraryunit.LibraryUnitVisitor;
import de.upb.hni.vmagic.libraryunit.UseClause;
import de.upb.hni.vmagic.object.ArrayElement;
import de.upb.hni.vmagic.object.RecordElement;
import de.upb.hni.vmagic.object.Signal;
import de.upb.hni.vmagic.object.SignalAssignmentTarget;
import de.upb.hni.vmagic.object.Slice;
import de.upb.hni.vmagic.object.Variable;
import de.upb.hni.vmagic.object.VariableAssignmentTarget;
import de.upb.hni.vmagic.statement.SequentialStatement;
import de.upb.hni.vmagic.statement.SequentialStatementVisitor;
import de.upb.hni.vmagic.type.IndexSubtypeIndication;
import de.upb.hni.vmagic.type.RangeSubtypeIndication;
import de.upb.hni.vmagic.type.ResolvedSubtypeIndication;
import de.upb.hni.vmagic.type.SubtypeIndication;
import de.upb.hni.vmagic.type.Type;
import de.upb.hni.vmagic.type.TypeVisitor;
import de.upb.hni.vmagic.type.UnresolvedType;
import java.util.List;

/**
 * Output module.
 * An output module contains all visitors that are necessary to output a hierarchy
 * of VhdlElements to a file or another data structure.
 */
public abstract class OutputModule {

    /**
     * Writes a sequential statement.
     * @param statement the statment
     */
    public void writeSeqentialStatement(SequentialStatement statement) {
        getSequentialStatementVisitor().visit(statement);
    }

    /**
     * Writes a list of sequential statements.
     * @param statements the list of statements
     */
    public void writeSequentialStatements(List<? extends SequentialStatement> statements) {
        getSequentialStatementVisitor().visit(statements);
    }

    /**
     * Writes a concurrent statement.
     * @param statement the statement
     */
    public void writeConcurrentStatement(ConcurrentStatement statement) {
        getConcurrentStatementVisitor().visit(statement);
    }

    /**
     * Writes a list of concurrent statments.
     * @param statements the list of statement
     */
    public void writeConcurrentStatements(List<? extends ConcurrentStatement> statements) {
        getConcurrentStatementVisitor().visit(statements);
    }

    /**
     * Writes a library unit.
     * @param unit the library unit
     */
    public void writeLibraryUnit(LibraryUnit unit) {
        getLibraryUnitVisitor().visit(unit);
    }

    /**
     * Writes a list of library units.
     * @param units the list of library units
     */
    public void writeLibraryUnits(List<? extends LibraryUnit> units) {
        getLibraryUnitVisitor().visit(units);
    }

    /**
     * Writes a declaration.
     * @param declaration the declaration
     */
    public void writeDeclaration(DeclarativeItem declaration) {
        getDeclarationVisitor().visit(declaration);
    }

    /**
     * Writes a list of declarations.
     * @param declarations the declarations
     */
    public void writeDeclarations(List<? extends DeclarativeItem> declarations) {
        getDeclarationVisitor().visit(declarations);
    }

    /**
     * Writes a declaration.
     * @param declaration the declaration
     */
    //TODO: remove
    public void writeDeclarationMarker(DeclarativeItemMarker declaration) {
        if (declaration instanceof DeclarativeItem) {
            getDeclarationVisitor().visit((DeclarativeItem) declaration);
        } else if (declaration instanceof Type) {
            getTypeVisitor().visit((Type) declaration);
        } else if (declaration instanceof UseClause) {
            getLibraryUnitVisitor().visit((UseClause) declaration);
        } else if (declaration == null) {
            //ignore
        } else {
            throw new IllegalStateException("Unknown declaration marker.");
        }
    }

    /**
     * Writes a list of delcarations.
     * @param declarations the list of declarations
     */
    //TODO: remove
    public void writeDeclarationMarkers(List<? extends DeclarativeItemMarker> declarations) {
        for (DeclarativeItemMarker declarativeItemMarker : declarations) {
            writeDeclarationMarker(declarativeItemMarker);
        }
    }

    /**
     * Writes an expression.
     * @param expression the expression
     */
    public void writeExpression(Expression expression) {
        getExpressionVisitor().visit(expression);
    }

    /**
     * Writes a configuration item.
     * @param configuration the configuration item
     */
    public void writeConfigurationItem(ConfigurationItem configuration) {
        getConfigurationVisitor().visit(configuration);
    }

    /**
     * Writes a list of configuration items.
     * @param configurations the list of configuration items
     */
    public void writeConfigurationItems(List<? extends ConfigurationItem> configurations) {
        getConfigurationVisitor().visit(configurations);
    }

    /**
     * Writes a signal assignment target.
     * @param target the target
     */
    public void writeSignalAssignmentTarget(SignalAssignmentTarget target) {
        if (target instanceof Aggregate) {
            writeExpression((Aggregate) target);
        } else if (target instanceof RecordElement) {
            writeExpression((RecordElement) target);
        } else if (target instanceof ArrayElement) {
            writeExpression((ArrayElement) target);
        } else if (target instanceof Slice) {
            writeExpression((Slice) target);
        } else if (target instanceof Signal) {
            writeExpression((Signal) target);
        } else if (target == null) {
            //ignore
        } else {
            throw new IllegalStateException("Unknown signal assignment target.");
        }
    }

    /**
     * Writes a variable assignment target.
     * @param target the target
     */
    public void writeVariableAssignmentTarget(VariableAssignmentTarget target) {
        if (target instanceof Aggregate) {
            writeExpression((Aggregate) target);
        } else if (target instanceof RecordElement) {
            writeExpression((RecordElement) target);
        } else if (target instanceof ArrayElement) {
            writeExpression((ArrayElement) target);
        } else if (target instanceof Slice) {
            writeExpression((Slice) target);
        } else if (target instanceof Variable) {
            writeExpression((Variable) target);
        } else if (target instanceof Signal) {
            writeExpression((Signal) target);
        } else if (target == null) {
            //ignore
        } else {
            throw new IllegalStateException("Unknown variable assignment target.");
        }
    }

    /**
     * Writes a subtype indication.
     * @param indication the subtype indication
     */
    public void writeSubtypeIndication(SubtypeIndication indication) {
        if (indication instanceof IndexSubtypeIndication) {
            getMiscellaneousElementOutput().indexSubtypeIndication((IndexSubtypeIndication) indication);
        } else if (indication instanceof ResolvedSubtypeIndication) {
            getMiscellaneousElementOutput().resolvedSubtypeIndication((ResolvedSubtypeIndication) indication);
        } else if (indication instanceof RangeSubtypeIndication) {
            getMiscellaneousElementOutput().rangeSubtypeIndication((RangeSubtypeIndication) indication);
        } else if (indication instanceof Type) {
            getMiscellaneousElementOutput().typeSubtypeIndication((Type) indication);
        } else if (indication instanceof Subtype) {
            getMiscellaneousElementOutput().subtypeSubtypeIndication((Subtype) indication);
        } else if (indication instanceof UnresolvedType) {
            getMiscellaneousElementOutput().unresolvedTypeSubtypeIndication((UnresolvedType) indication);
        } else if (indication == null) {
            //ignore
        } else {
            throw new IllegalStateException("Unknown subtype indication.");
        }
    }

    /**
     * Writes a discrete range.
     * @param range the discrete range
     */
    public void writeDiscreteRange(DiscreteRange range) {
        if (range instanceof Range) {
            getMiscellaneousElementOutput().range((Range) range);
        } else if (range instanceof RangeAttributeName) {
            getMiscellaneousElementOutput().rangeAttributeName((RangeAttributeName) range);
        } else if (range instanceof SubtypeDiscreteRange) {
            getMiscellaneousElementOutput().subtypeDiscreteRange((SubtypeDiscreteRange) range);
        } else if (range == null) {
            //ignore
        } else {
            throw new IllegalStateException("Unknown range.");
        }
    }

    /**
     * Writes a choice.
     * @param choice the choice
     */
    public void writeChoice(Choice choice) {
        if (choice == Choices.OTHERS) {
            getMiscellaneousElementOutput().choiceOthers();
        } else if (choice instanceof Expression) {
            writeExpression((Expression) choice);
        } else if (choice instanceof DiscreteRange) {
            writeDiscreteRange((DiscreteRange) choice);
        } else if (choice == null) {
            //ignore
        } else {
            throw new IllegalStateException("Unknown choice.");
        }
    }

    /**
     * Writes a component specification.
     * @param specification the component specification
     */
    public void writeComponentSpecification(ComponentSpecification specification) {
        if (specification == null) {
            return;
        }

        switch (specification.getType()) {
            case ALL:
                getMiscellaneousElementOutput().allComponentSpecification(specification);
                break;

            case INSTANTIATION_LIST:
                getMiscellaneousElementOutput().instantiationListComponentSpecification(specification);
                break;

            case OTHERS:
                getMiscellaneousElementOutput().othersComponentSpecification(specification);
                break;
        }
    }

    /**
     * Writes a VhdlElement.
     * @param element the element
     */
    public void writeVhdlElement(VhdlElement element) {
        if (element instanceof VhdlFile) {
            writeLibraryUnits(((VhdlFile) element).getElements());
        } else if (element instanceof ComponentSpecification) {
            writeComponentSpecification((ComponentSpecification) element);
        } else if (element instanceof ConcurrentStatement) {
            writeConcurrentStatement((ConcurrentStatement) element);
        } else if (element instanceof ConfigurationItem) {
            writeConfigurationItem((ConfigurationItem) element);
        } else if (element instanceof DeclarativeItem) {
            writeDeclaration((DeclarativeItem) element);
        } else if (element instanceof Expression) {
            writeExpression((Expression) element);
        } else if (element instanceof LibraryUnit) {
            writeLibraryUnit((LibraryUnit) element);
        } else if (element instanceof SequentialStatement) {
            writeSeqentialStatement((SequentialStatement) element);
        } else {
            throw new IllegalStateException("cannot output this element");
        }
    }

    /**
     * Returns the sequential statement visitor.
     * @return the sequential statement visitor
     */
    protected abstract SequentialStatementVisitor getSequentialStatementVisitor();

    /**
     * Returns the concurrent statement visitor.
     * @return the concurrent statement visitor
     */
    protected abstract ConcurrentStatementVisitor getConcurrentStatementVisitor();

    /**
     * Returns the library unit visitor.
     * @return the library unit visitor
     */
    protected abstract LibraryUnitVisitor getLibraryUnitVisitor();

    /**
     * Returns the declaration visitor.
     * @return the declaration visitor
     */
    protected abstract DeclarationVisitor getDeclarationVisitor();

    /**
     * Returns the expression visitor.
     * @return the expression visitor
     */
    protected abstract ExpressionVisitor getExpressionVisitor();

    /**
     * Returns the configuration visitor.
     * @return the configuration visitor
     */
    protected abstract ConfigurationVisitor getConfigurationVisitor();

    /**
     * Returns the type visitor.
     * @return the type visitro
     */
    protected abstract TypeVisitor getTypeVisitor();

    /**
     * Returns the miscellaneous element output.
     * @return the miscellaneous element output
     */
    protected abstract MiscellaneousElementOutput getMiscellaneousElementOutput();
}

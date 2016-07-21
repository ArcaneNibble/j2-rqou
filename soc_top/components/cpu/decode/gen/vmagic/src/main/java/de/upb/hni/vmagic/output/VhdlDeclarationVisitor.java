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
import de.upb.hni.vmagic.annotation.InterfaceDeclarationFormat;
import de.upb.hni.vmagic.annotation.OptionalIsFormat;
import de.upb.hni.vmagic.declaration.Alias;
import de.upb.hni.vmagic.declaration.Attribute;
import de.upb.hni.vmagic.declaration.AttributeSpecification;
import de.upb.hni.vmagic.declaration.Component;
import de.upb.hni.vmagic.declaration.ConfigurationSpecification;
import de.upb.hni.vmagic.declaration.ConstantDeclaration;
import de.upb.hni.vmagic.declaration.DeclarationVisitor;
import de.upb.hni.vmagic.declaration.DeclarativeItem;
import de.upb.hni.vmagic.declaration.DisconnectionSpecification;
import de.upb.hni.vmagic.declaration.FileDeclaration;
import de.upb.hni.vmagic.declaration.FunctionBody;
import de.upb.hni.vmagic.declaration.FunctionDeclaration;
import de.upb.hni.vmagic.declaration.Group;
import de.upb.hni.vmagic.declaration.GroupTemplate;
import de.upb.hni.vmagic.declaration.ProcedureBody;
import de.upb.hni.vmagic.declaration.ProcedureDeclaration;
import de.upb.hni.vmagic.declaration.SignalDeclaration;
import de.upb.hni.vmagic.declaration.Subtype;
import de.upb.hni.vmagic.declaration.VariableDeclaration;
import de.upb.hni.vmagic.object.Constant;
import de.upb.hni.vmagic.object.FileObject;
import de.upb.hni.vmagic.object.Signal;
import de.upb.hni.vmagic.object.Variable;
import de.upb.hni.vmagic.object.VhdlObject;
import de.upb.hni.vmagic.object.VhdlObjectProvider;
import java.util.List;

/**
 * Declaration output visitor.
 */
class VhdlDeclarationVisitor extends DeclarationVisitor {

    private final VhdlWriter writer;
    private final OutputModule output;

    public VhdlDeclarationVisitor(VhdlWriter writer, OutputModule output) {
        this.writer = writer;
        this.output = output;
    }

    @Override
    public void visit(DeclarativeItem declaration) {
        VhdlOutputHelper.handleAnnotationsBefore(declaration, writer);
        super.visit(declaration);
        VhdlOutputHelper.handleAnnotationsAfter(declaration, writer);
    }

    @Override
    public void visit(List<? extends DeclarativeItem> declarations) {
        for (DeclarativeItem declaration : declarations) {
            visit(declaration);
        }
    }

    private boolean isOutputMode(VhdlObject object) {
        if (object.getMode() == VhdlObject.Mode.NONE) {
            return false;
        }

        InterfaceDeclarationFormat format =
                Annotations.getAnnotation(object, InterfaceDeclarationFormat.class);

        if (format != null && format.isUseMode()) {
            return true;
        }

        return object.getMode() != VhdlObject.Mode.IN;
    }

    private boolean isOutputObjectClassProcedure(VhdlObject object) {
        InterfaceDeclarationFormat format =
                Annotations.getAnnotation(object, InterfaceDeclarationFormat.class);

        if (format != null && format.isUseObjectClass()) {
            return true;
        }

        switch (object.getObjectClass()) {
            case CONSTANT:
                return false;

            case VARIABLE:
                return (object.getMode() != Variable.Mode.INOUT
                        && object.getMode() != Variable.Mode.OUT);

            default:
                return true;
        }
    }

    private boolean isOutputObjectClassFunction(VhdlObject object) {
        InterfaceDeclarationFormat format =
                Annotations.getAnnotation(object, InterfaceDeclarationFormat.class);

        if (format != null && format.isUseObjectClass()) {
            return true;
        }

        return object.getObjectClass() != VhdlObject.ObjectClass.CONSTANT;
    }

    private void appendProcedureParameters(List<VhdlObjectProvider<? extends VhdlObject>> parameters) {
        if (!parameters.isEmpty()) {
            writer.append(" (");
            boolean first = true;
            for (VhdlObjectProvider<? extends VhdlObject> provider : parameters) {
                if (first) {
                    first = false;
                } else {
                    writer.append("; ");
                }

                VhdlObject object0 = provider.getVhdlObjects().get(0);

                if (isOutputObjectClassProcedure(object0)) {
                    writer.append(object0.getObjectClass()).append(' ');
                }

                writer.appendIdentifiers(provider.getVhdlObjects(), ", ");

                writer.append(" : ");

                if (isOutputMode(object0)) {
                    writer.append(object0.getMode()).append(' ');
                }

                VhdlObjectOutputHelper.interfaceSuffix(object0, writer, output);
            }
            writer.append(")");
        }
    }

    private void appendFunctionParameters(List<VhdlObjectProvider<? extends VhdlObject>> parameters) {
        if (!parameters.isEmpty()) {
            writer.append(" (");
            boolean first = true;
            for (VhdlObjectProvider<? extends VhdlObject> provider : parameters) {
                if (first) {
                    first = false;
                } else {
                    writer.append("; ");
                }

                VhdlObject object0 = provider.getVhdlObjects().get(0);

                //don't add type if object is a constant
                if (isOutputObjectClassFunction(object0)) {
                    writer.append(object0.getObjectClass()).append(' ');
                }

                writer.appendIdentifiers(provider.getVhdlObjects(), ", ");

                writer.append(" : ");

                if (isOutputMode(object0)) {
                    writer.append(object0.getMode()).append(' ');
                }

                VhdlObjectOutputHelper.interfaceSuffix(object0, writer, output);
            }
            writer.append(")");
        }
    }

    @Override
    protected void visitAliasDeclaration(Alias declaration) {
        writer.append(Keyword.ALIAS).append(' ').append(declaration.getDesignator());
        if (declaration.getSubtypeIndication() != null) {
            writer.append(" : ");
            output.writeSubtypeIndication(declaration.getSubtypeIndication());
        }
        writer.append(' ').append(Keyword.IS).append(' ').append(declaration.getAliased());
        if (declaration.getSignature() != null) {
            writer.append(' ');
            output.getMiscellaneousElementOutput().signature(declaration.getSignature());
        }
        writer.append(';').newLine();
    }

    @Override
    protected void visitAttributeDeclaration(Attribute declaration) {
        writer.append(Keyword.ATTRIBUTE).append(' ');
        writer.appendIdentifier(declaration);
        writer.append(" : ");
        output.writeSubtypeIndication(declaration.getType());
        writer.append(';').newLine();
    }

    private void appendEntityDesignator(AttributeSpecification.EntityNameList.EntityDesignator designator) {
        writer.append(designator.getEntityTag());
        if (designator.getSignature() != null) {
            writer.append(' ');
            output.getMiscellaneousElementOutput().signature(designator.getSignature());
        }
    }

    @Override
    protected void visitAttributeSpecification(AttributeSpecification specification) {
        writer.append(Keyword.ATTRIBUTE).append(' ');
        writer.appendIdentifier(specification.getAttribute()).append(' ');
        writer.append(Keyword.OF).append(' ');
        AttributeSpecification.EntityNameList entityNames = specification.getEntities();
        if (entityNames == AttributeSpecification.EntityNameList.ALL) {
            writer.append(Keyword.ALL);
        } else if (entityNames == AttributeSpecification.EntityNameList.OTHERS) {
            writer.append(Keyword.OTHERS);
        } else {
            boolean first = true;
            for (AttributeSpecification.EntityNameList.EntityDesignator designator : entityNames.getDesignators()) {
                if (first) {
                    first = false;
                } else {
                    writer.append(", ");
                }
                appendEntityDesignator(designator);
            }
        }
        writer.append(" : ").append(specification.getEntityClass()).append(' ');
        writer.append(Keyword.IS).append(' ');
        output.writeExpression(specification.getValue());
        writer.append(';').newLine();
    }

    @Override
    protected void visitComponentDeclaration(Component declaration) {
        writer.append(Keyword.COMPONENT).append(' ');
        writer.appendIdentifier(declaration);

        OptionalIsFormat format = Annotations.getAnnotation(declaration, OptionalIsFormat.class);
        if (format != null && format.isUseIs()) {
            writer.append(' ').append(Keyword.IS);
        }

        writer.indent().newLine();
        if (!declaration.getGeneric().isEmpty()) {
            output.getMiscellaneousElementOutput().generic(declaration.getGeneric());
        }
        if (!declaration.getPort().isEmpty()) {
            output.getMiscellaneousElementOutput().port(declaration.getPort());
        }
        writer.dedent().append(Keyword.END, Keyword.COMPONENT);
        if (writer.getFormat().isRepeatLabels()) {
            writer.append(' ').appendIdentifier(declaration);
        }
        writer.append(";").newLine();
    }

    @Override
    protected void visitConfigurationSpecification(ConfigurationSpecification specification) {
        writer.append(Keyword.FOR).append(' ');
        output.writeComponentSpecification(specification.getComponentSpecification());

        if (specification.getEntityAspect() != null) {
            writer.append(' ').append(Keyword.USE).append(' ');
            writer.append(specification.getEntityAspect().toString());
        }

        if (!specification.getGenericMap().isEmpty()) {
            writer.newLine();
            writer.append(Keyword.GENERIC, Keyword.MAP).append(" (").newLine();
            writer.indent().beginAlign();
            output.getMiscellaneousElementOutput().genericMap(specification.getGenericMap());
            writer.endAlign().dedent();
            writer.append(")");
        }

        if (!specification.getPortMap().isEmpty()) {
            writer.newLine();
            writer.append(Keyword.PORT, Keyword.MAP).append(" (").newLine();
            writer.indent().beginAlign();
            output.getMiscellaneousElementOutput().portMap(specification.getPortMap());
            writer.endAlign().dedent();
            writer.append(")");
        }

        writer.append(';').newLine();
    }

    //TODO: check if default values are equal
    //TODO: check if types match
    @Override
    protected void visitConstantDeclaration(ConstantDeclaration declaration) {
        Constant constant = declaration.getObjects().get(0);

        writer.append(Keyword.CONSTANT).append(' ');
        writer.appendIdentifiers(declaration.getObjects(), ", ");
        writer.append(" : ");
        output.writeSubtypeIndication(constant.getType());
        if (constant.getDefaultValue() != null) {
            writer.append(" := ");
            output.writeExpression(constant.getDefaultValue());
        }
        writer.append(";").newLine();
    }

    private void appendSignalList(DisconnectionSpecification.SignalList signals) {
        if (signals == DisconnectionSpecification.SignalList.ALL) {
            writer.append(Keyword.ALL);
        } else if (signals == DisconnectionSpecification.SignalList.OTHERS) {
            writer.append(Keyword.OTHERS);
        } else {
            boolean first = true;
            for (Signal signal : signals.getSignals()) {
                if (first) {
                    first = false;
                } else {
                    writer.append(", ");
                }
                writer.appendIdentifier(signal);
            }
        }
    }

    @Override
    protected void visitDisconnectionSpecification(DisconnectionSpecification specification) {
        writer.append(Keyword.DISCONNECT).append(' ');
        appendSignalList(specification.getSignals());
        writer.append(" : ");
        output.writeSubtypeIndication(specification.getType());
        writer.append(' ').append(Keyword.AFTER).append(' ');
        output.writeExpression(specification.getAfter());
        writer.append(';').newLine();
    }

    //TODO: check if types match
    @Override
    protected void visitFileDeclaration(FileDeclaration declaration) {
        FileObject file = declaration.getObjects().get(0);

        writer.append(Keyword.FILE).append(' ');
        writer.appendIdentifiers(declaration.getObjects(), ", ");
        writer.append(" : ");
        output.writeSubtypeIndication(file.getType());
        if (file.getOpenKind() != null) {
            writer.append(' ').append(Keyword.OPEN).append(' ');
            output.writeExpression(file.getOpenKind());
        }
        if (file.getLogicalName() != null) {
            writer.append(' ').append(Keyword.IS).append(' ');
            output.writeExpression(file.getLogicalName());
        }
        writer.append(";").newLine();
    }

    @Override
    protected void visitFunctionBody(FunctionBody declaration) {
        if (declaration.isImpure()) {
            writer.append(Keyword.IMPURE).append(' ');
        }
        writer.append(Keyword.FUNCTION).append(' ').appendIdentifier(declaration);
        appendFunctionParameters(declaration.getParameters());
        writer.append(' ').append(Keyword.RETURN).append(' ');
        output.writeSubtypeIndication(declaration.getReturnType());
        writer.append(' ').append(Keyword.IS).newLine().indent();
        output.writeDeclarationMarkers(declaration.getDeclarations());
        writer.dedent().append(Keyword.BEGIN).newLine().indent();
        output.writeSequentialStatements(declaration.getStatements());

        //TODO: add repeated label
        writer.dedent().append(Keyword.END).append(";").newLine();
    }

    @Override
    protected void visitFunctionDeclaration(FunctionDeclaration declaration) {
        if (declaration.isImpure()) {
            writer.append(Keyword.IMPURE).append(' ');
        }
        writer.append(Keyword.FUNCTION).append(' ').appendIdentifier(declaration);
        appendFunctionParameters(declaration.getParameters());
        writer.append(' ').append(Keyword.RETURN).append(' ');
        output.writeSubtypeIndication(declaration.getReturnType());
        writer.append(";").newLine();
    }

    @Override
    protected void visitGroupDeclaration(Group declaration) {
        writer.append(Keyword.GROUP).append(' ');
        writer.appendIdentifier(declaration).append(" : ");
        writer.appendIdentifier(declaration.getTemplate()).append(" (");
        writer.appendStrings(declaration.getConstituents(), ", ");
        writer.append(");").newLine();
    }

    @Override
    protected void visitGroupTemplateDeclaration(GroupTemplate declaration) {
        writer.append(Keyword.GROUP).append(' ');
        writer.appendIdentifier(declaration).append(' ');
        writer.append(Keyword.IS).append(" (");
        writer.appendOutputEnums(declaration.getEntityClasses(), ", ");
        if (declaration.isRepeatLast()) {
            writer.append(" <>");
        }
        writer.append(");").newLine();
    }

    @Override
    protected void visitProcedureBody(ProcedureBody declaration) {
        writer.append(Keyword.PROCEDURE).append(' ').appendIdentifier(declaration);
        appendProcedureParameters(declaration.getParameters());
        writer.append(' ').append(Keyword.IS).newLine().indent();
        output.writeDeclarationMarkers(declaration.getDeclarations());
        writer.dedent().append(Keyword.BEGIN).newLine().indent();
        output.writeSequentialStatements(declaration.getStatements());

        //TODO: add repeated label
        writer.dedent().append(Keyword.END).append(";").newLine();
    }

    @Override
    protected void visitProcedureDeclaration(ProcedureDeclaration declaration) {
        writer.append(Keyword.PROCEDURE).append(' ').appendIdentifier(declaration);
        appendProcedureParameters(declaration.getParameters());
        writer.append(";").newLine();
    }

    //TODO: check if default values are equal
    //TODO: check if types match
    @Override
    protected void visitSignalDeclaration(SignalDeclaration declaration) {
        Signal signal = declaration.getObjects().get(0);

        writer.append(Keyword.SIGNAL).append(' ');
        writer.appendIdentifiers(declaration.getObjects(), ", ");
        writer.append(" : ");
        output.writeSubtypeIndication(signal.getType());
        if (signal.getKind() != Signal.Kind.DEFAULT) {
            writer.append(' ').append(signal.getKind());
        }
        if (signal.getDefaultValue() != null) {
            writer.append(" := ");
            output.writeExpression(signal.getDefaultValue());
        }
        writer.append(";").newLine();
    }

    @Override
    protected void visitSubtypeDeclaration(Subtype declaration) {
        writer.append(Keyword.SUBTYPE).append(' ');
        writer.appendIdentifier(declaration).append(' ');
        writer.append(Keyword.IS).append(' ');
        output.writeSubtypeIndication(declaration.getSubtypeIndication());
        writer.append(';').newLine();
    }

    //TODO: check if default values are equal
    //TODO: check if shared attributes are equal
    //TODO: check if types match
    @Override
    protected void visitVariableDeclaration(VariableDeclaration declaration) {
        Variable variable = declaration.getObjects().get(0);

        if (variable.isShared()) {
            writer.append(Keyword.SHARED).append(' ');
        }
        writer.append(Keyword.VARIABLE).append(' ');
        writer.appendIdentifiers(declaration.getObjects(), ", ");
        writer.append(" : ");
        output.writeSubtypeIndication(variable.getType());
        if (variable.getDefaultValue() != null) {
            writer.append(" := ");
            output.writeExpression(variable.getDefaultValue());
        }
        writer.append(";").newLine();
    }
}

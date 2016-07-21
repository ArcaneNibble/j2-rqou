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
import de.upb.hni.vmagic.AssociationElement;
import de.upb.hni.vmagic.ComponentSpecification;
import de.upb.hni.vmagic.DelayMechanism;
import de.upb.hni.vmagic.DiscreteRange;
import de.upb.hni.vmagic.Range;
import de.upb.hni.vmagic.RangeAttributeName;
import de.upb.hni.vmagic.Signature;
import de.upb.hni.vmagic.SubtypeDiscreteRange;
import de.upb.hni.vmagic.WaveformElement;
import de.upb.hni.vmagic.annotation.InterfaceDeclarationFormat;
import de.upb.hni.vmagic.declaration.Subtype;
import de.upb.hni.vmagic.object.Constant;
import de.upb.hni.vmagic.object.Signal;
import de.upb.hni.vmagic.object.VhdlObject;
import de.upb.hni.vmagic.object.VhdlObjectGroup;
import de.upb.hni.vmagic.object.VhdlObjectProvider;
import de.upb.hni.vmagic.type.IndexSubtypeIndication;
import de.upb.hni.vmagic.type.RangeSubtypeIndication;
import de.upb.hni.vmagic.type.ResolvedSubtypeIndication;
import de.upb.hni.vmagic.type.SubtypeIndication;
import de.upb.hni.vmagic.type.Type;
import de.upb.hni.vmagic.type.UnresolvedType;
import java.util.List;

/**
 * VHDL output module for elements that are not handled by a visitor.
 */
class VhdlMiscellaneousElementOutput implements MiscellaneousElementOutput {

    private final VhdlWriter writer;
    private final OutputModule output;

    public VhdlMiscellaneousElementOutput(VhdlWriter writer, OutputModule output) {
        this.writer = writer;
        this.output = output;
    }

    @Override
    public void delayMechanism(DelayMechanism delayMechanism) {
        if (delayMechanism == DelayMechanism.INERTIAL) {
            writer.append(Keyword.INERTIAL);
        } else if (delayMechanism == DelayMechanism.TRANSPORT) {
            writer.append(Keyword.TRANSPORT);
        } else {
            writer.append(Keyword.REJECT).append(' ');
            output.writeExpression(delayMechanism.getPulseRejectionLimit());
            writer.append(' ').append(Keyword.INERTIAL);
        }
    }

    private void associationElement(AssociationElement element) {
        if (element.getFormal() != null) {
            writer.append(element.getFormal());
            writer.align();
            writer.append(" => ");
        }
        if (element.getActual() == null) {
            writer.append(Keyword.OPEN);
        } else {
            output.writeExpression(element.getActual());
        }
    }

    private void associationList(List<AssociationElement> associationList, boolean addLineBreaks) {
        boolean first = true;
        for (AssociationElement element : associationList) {
            if (first) {
                first = false;
            } else {
                writer.append(',');
                if (addLineBreaks) {
                    writer.newLine();
                } else {
                    writer.append(' ');
                }
            }
            VhdlOutputHelper.handleAnnotationsBefore(element, writer);
            associationElement(element);
            VhdlOutputHelper.handleAnnotationsAfter(element, writer);
        }
        if (addLineBreaks) {
            writer.newLine();
        }
    }

    private void writeInterfaceList(List<? extends VhdlObjectProvider<?>> list, boolean isGeneric) {
        writer.beginAlign();

        boolean first = true;
        for (VhdlObjectProvider<?> objectProvider : list) {
            if (first) {
                first = false;
            } else {
                writer.append(";").newLine();
            }

            if (objectProvider instanceof VhdlObjectGroup) {
                VhdlObjectGroup group = (VhdlObjectGroup) objectProvider;
                VhdlOutputHelper.handleAnnotationsBefore(group, writer);
            }

            //TODO: check for equal types etc.
            VhdlObject obj0 = objectProvider.getVhdlObjects().get(0);

            InterfaceDeclarationFormat format =
                    Annotations.getAnnotation(obj0, InterfaceDeclarationFormat.class);

            if (format != null && format.isUseObjectClass()) {
                writer.append(obj0.getObjectClass()).append(' ');
            }

            writer.appendIdentifiers(objectProvider.getVhdlObjects(), ", ");
            writer.align();

            writer.append(" : ");

            if (format != null) {
                if (format.isUseMode() || (obj0.getMode() != VhdlObject.Mode.IN)) {
                    writer.append(obj0.getMode()).append(' ');
                }
            } else {
                if (!isGeneric || obj0.getMode() != VhdlObject.Mode.IN) {
                    writer.append(obj0.getMode()).append(' ');
                }
            }

            VhdlObjectOutputHelper.interfaceSuffix(obj0, writer, output);
        }

        writer.newLine().endAlign();
    }

    @Override
    public void generic(List<VhdlObjectProvider<Constant>> generic) {
        writer.append(Keyword.GENERIC).append(" (").newLine().indent();
        writeInterfaceList(generic, true);
        writer.dedent().append(");").newLine();
    }

    @Override
    public void port(List<VhdlObjectProvider<Signal>> port) {
        writer.append(Keyword.PORT).append(" (").newLine().indent();
        writeInterfaceList(port, false);
        writer.dedent().append(");").newLine();
    }

    @Override
    public void genericMap(List<AssociationElement> genericMap) {
        associationList(genericMap, true);
    }

    @Override
    public void portMap(List<AssociationElement> genericMap) {
        associationList(genericMap, true);
    }

    @Override
    public void procedureCallParameters(List<AssociationElement> parameters) {
        associationList(parameters, false);
    }

    @Override
    public void concurrentProcedureCallParameters(List<AssociationElement> parameters) {
        associationList(parameters, false);
    }

    @Override
    public void functionCallParameters(List<AssociationElement> parameters) {
        associationList(parameters, false);
    }

    @Override
    public void signature(Signature signature) {
        writer.append('[');

        boolean first = true;
        for (SubtypeIndication type : signature.getParameterTypes()) {
            if (first) {
                first = false;
            } else {
                writer.append(", ");
            }
            output.writeSubtypeIndication(type);
        }

        if (signature.getReturnType() != null) {
            if (!signature.getParameterTypes().isEmpty()) {
                writer.append(' ');
            }
            writer.append(Keyword.RETURN).append(' ');
            output.writeSubtypeIndication(signature.getReturnType());
        }

        writer.append(']');
    }

    @Override
    public void waveform(List<WaveformElement> waveform) {
        if (waveform.isEmpty()) {
            writer.append(Keyword.UNAFFECTED);
        } else {
            boolean first = true;
            for (WaveformElement waveformElement : waveform) {
                if (first) {
                    first = false;
                } else {
                    writer.append(", ");
                }

                output.writeExpression(waveformElement.getValue());
                if (waveformElement.getAfter() != null) {
                    writer.append(' ').append(Keyword.AFTER).append(' ');
                    output.writeExpression(waveformElement.getAfter());
                }
            }
        }
    }

    @Override
    public void range(Range range) {
        output.writeExpression(range.getFrom());
        writer.append(' ');
        switch (range.getDirection()) {
            case DOWNTO:
                writer.append(Keyword.DOWNTO);
                break;

            case TO:
                writer.append(Keyword.TO);
                break;
        }
        writer.append(' ');
        output.writeExpression(range.getTo());
    }

    @Override
    public void rangeAttributeName(RangeAttributeName range) {
        writer.append(range.getPrefix()).append('\'').append(range.getType());
        if (range.getIndex() != null) {
            writer.append('(');
            output.writeExpression(range.getIndex());
            writer.append(')');
        }
    }

    @Override
    public void subtypeDiscreteRange(SubtypeDiscreteRange range) {
        output.writeSubtypeIndication(range.getSubtypeIndication());
    }

    @Override
    public void choiceOthers() {
        writer.append(Keyword.OTHERS);
    }

    @Override
    public void indexSubtypeIndication(IndexSubtypeIndication indication) {
        if (indication.getBaseType() != null) {
            output.writeSubtypeIndication(indication.getBaseType());
        } else {
            writer.append("null");
        }
        writer.append('(');
        boolean first = true;
        for (DiscreteRange range : indication.getRanges()) {
            if (first) {
                first = false;
            } else {
                writer.append(", ");
            }
            output.writeDiscreteRange(range);
        }
        writer.append(')');
    }

    @Override
    public void rangeSubtypeIndication(RangeSubtypeIndication indication) {
        if (indication.getBaseType() != null) {
            output.writeSubtypeIndication(indication.getBaseType());
        } else {
            writer.append("null");
        }
        writer.append(' ').append(Keyword.RANGE).append(' ');
        output.writeDiscreteRange(indication.getRange());
    }

    @Override
    public void resolvedSubtypeIndication(ResolvedSubtypeIndication indication) {
        writer.append(indication.getResolutionFunction()).append(' ');
        if (indication.getBaseType() != null) {
            output.writeSubtypeIndication(indication.getBaseType());
        } else {
            writer.append("null");
        }
    }

    @Override
    public void subtypeSubtypeIndication(Subtype subtype) {
        writer.appendIdentifier(subtype);
    }

    @Override
    public void typeSubtypeIndication(Type subtype) {
        writer.appendIdentifier(subtype);
    }

    @Override
    public void unresolvedTypeSubtypeIndication(UnresolvedType subtype) {
        writer.appendIdentifier(subtype);
    }

    @Override
    public void allComponentSpecification(ComponentSpecification specification) {
        writer.append(Keyword.ALL).append(" : ");
        writer.appendIdentifier(specification.getComponent());
    }

    @Override
    public void othersComponentSpecification(ComponentSpecification specification) {
        writer.append(Keyword.OTHERS).append(" : ");
        writer.appendIdentifier(specification.getComponent());
    }

    @Override
    public void instantiationListComponentSpecification(ComponentSpecification specification) {
        writer.appendStrings(specification.getLabels(), ", ");
        writer.append(" : ").appendIdentifier(specification.getComponent());
    }
}

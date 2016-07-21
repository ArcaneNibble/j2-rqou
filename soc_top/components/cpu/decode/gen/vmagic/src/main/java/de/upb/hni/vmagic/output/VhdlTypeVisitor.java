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

import de.upb.hni.vmagic.DiscreteRange;
import de.upb.hni.vmagic.literal.EnumerationLiteral;
import de.upb.hni.vmagic.type.AccessType;
import de.upb.hni.vmagic.type.ConstrainedArray;
import de.upb.hni.vmagic.type.EnumerationType;
import de.upb.hni.vmagic.type.FileType;
import de.upb.hni.vmagic.type.IncompleteType;
import de.upb.hni.vmagic.type.IntegerType;
import de.upb.hni.vmagic.type.PhysicalType;
import de.upb.hni.vmagic.type.RecordType;
import de.upb.hni.vmagic.type.SubtypeIndication;
import de.upb.hni.vmagic.type.Type;
import de.upb.hni.vmagic.type.TypeVisitor;
import de.upb.hni.vmagic.type.UnconstrainedArray;

/**
 * VHDL output type visitor.
 */
class VhdlTypeVisitor extends TypeVisitor {

    private final VhdlWriter writer;
    private final OutputModule output;

    public VhdlTypeVisitor(VhdlWriter writer, OutputModule output) {
        this.writer = writer;
        this.output = output;
    }

    @Override
    public void visit(Type type) {
        VhdlOutputHelper.handleAnnotationsBefore(type, writer);
        super.visit(type);
        VhdlOutputHelper.handleAnnotationsAfter(type, writer);
    }

    private void appendTypePrefix(Type declaration) {
        writer.append(Keyword.TYPE).append(' ');
        writer.appendIdentifier(declaration).append(' ').append(Keyword.IS);
    }

    @Override
    protected void visitAccessType(AccessType type) {
        appendTypePrefix(type);
        writer.append(' ').append(Keyword.ACCESS).append(' ');
        output.writeSubtypeIndication(type.getDesignatedSubtype());
        writer.append(';').newLine();
    }

    @Override
    protected void visitConstrainedArray(ConstrainedArray type) {
        appendTypePrefix(type);
        writer.append(' ').append(Keyword.ARRAY).append(" (");

        boolean first = true;
        for (DiscreteRange range : type.getIndexRanges()) {
            if (first) {
                first = false;
            } else {
                writer.append(", ");
            }
            output.writeDiscreteRange(range);
        }

        writer.append(") ").append(Keyword.OF).append(' ');
        output.writeSubtypeIndication(type.getElementType());
        writer.append(';').newLine();
    }

    @Override
    protected void visitEnumerationType(EnumerationType type) {
        appendTypePrefix(type);
        writer.append(" (");

        boolean first = true;
        for (EnumerationLiteral literal : type.getLiterals()) {
            if (first) {
                first = false;
            } else {
                writer.append(", ");
            }

            writer.append(literal.toString());
        }

        writer.append(");").newLine();
    }

    @Override
    protected void visitFileType(FileType type) {
        appendTypePrefix(type);
        writer.append(' ').append(Keyword.FILE, Keyword.OF).append(' ');
        output.writeSubtypeIndication(type.getValueType());
        writer.append(';').newLine();
    }

    @Override
    protected void visitIncompleteType(IncompleteType type) {
        writer.append(Keyword.TYPE).append(' ');
        writer.appendIdentifier(type);
        writer.append(';').newLine();
    }

    @Override
    protected void visitIntegerType(IntegerType type) {
        appendTypePrefix(type);
        writer.append(' ').append(Keyword.RANGE).append(' ');
        output.writeDiscreteRange(type.getRange());
        writer.append(';').newLine();
    }

    @Override
    protected void visitPhysicalType(PhysicalType type) {
        //TODO: implement repeated label
        appendTypePrefix(type);
        writer.append(' ').append(Keyword.RANGE).append(' ');
        output.writeDiscreteRange(type.getRange());
        writer.append(' ').newLine();
        writer.indent().append(Keyword.UNITS).newLine();
        writer.indent().append(type.getPrimaryUnit()).append(';').newLine().beginAlign();
        for (PhysicalType.Unit unit : type.getUnits()) {
            writer.appendIdentifier(unit).align().append(" = ");
            if (unit.getFactor() != null) {
                output.writeExpression(unit.getFactor());
                writer.append(' ');
            }
            writer.append(unit.getBaseUnit()).append(';').newLine();
        }
        writer.endAlign().dedent();

        writer.append(Keyword.END, Keyword.UNITS).append(';').dedent().newLine();
    }

    @Override
    protected void visitRecordType(RecordType type) {
        //TODO: implement repeated label
        appendTypePrefix(type);
        writer.indent().newLine();
        writer.append(Keyword.RECORD).indent().newLine().beginAlign();
        for (RecordType.ElementDeclaration element : type.getElements()) {
            writer.appendStrings(element.getIdentifiers(), ", ");
            writer.align().append(" : ");
            output.writeSubtypeIndication(element.getType());
            writer.append(';').newLine();
        }
        writer.endAlign().dedent();
        writer.append(Keyword.END, Keyword.RECORD);
        writer.dedent();
        writer.append(';').newLine();
    }

    @Override
    protected void visitUnconstrainedArray(UnconstrainedArray type) {
        appendTypePrefix(type);
        writer.append(' ').append(Keyword.ARRAY).append(" (");

        boolean first = true;
        for (SubtypeIndication subtype : type.getIndexSubtypes()) {
            if (first) {
                first = false;
            } else {
                writer.append(", ");
            }
            output.writeSubtypeIndication(subtype);
            writer.append(' ').append(Keyword.RANGE).append(' ').append("<>");
        }

        writer.append(") ").append(Keyword.OF).append(' ');
        output.writeSubtypeIndication(type.getElementType());
        writer.append(';').newLine();
    }
}

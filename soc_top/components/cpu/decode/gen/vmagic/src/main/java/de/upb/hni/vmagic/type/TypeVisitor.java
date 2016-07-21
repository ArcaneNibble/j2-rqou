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

package de.upb.hni.vmagic.type;

/**
 * Type visitor.
 */
public class TypeVisitor {

    /**
     * Visits a type.
     * No visit method is called when the parameter is <code>null</code>.
     * @param type the type
     */
    public void visit(Type type) {
        if (type != null) {
            type.accept(this);
        }
    }

    /**
     * Visits a access type.
     * @param type the type
     */
    protected void visitAccessType(AccessType type) {
    }

    /**
     * Visits a constrained array.
     * @param type the type
     */
    protected void visitConstrainedArray(ConstrainedArray type) {
    }

    /**
     * Visits an enumeration type.
     * @param type the type
     */
    protected void visitEnumerationType(EnumerationType type) {
    }

    /**
     * Visits a file type.
     * @param type the type
     */
    protected void visitFileType(FileType type) {
    }

    /**
     * Visits an incomplete type.
     * @param type the type
     */
    protected void visitIncompleteType(IncompleteType type) {
    }

    /**
     * Visits an integer type.
     * @param type the type
     */
    protected void visitIntegerType(IntegerType type) {
    }

    /**
     * Visits a physical type.
     * @param type the type
     */
    protected void visitPhysicalType(PhysicalType type) {
    }

    /**
     * Visits a record type.
     * @param type the type
     */
    protected void visitRecordType(RecordType type) {
    }

    /**
     * Visits an unconstrained array.
     * @param type the type
     */
    protected void visitUnconstrainedArray(UnconstrainedArray type) {
    }
}

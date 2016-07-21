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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Record type.
 */
public class RecordType extends Type {

    private final List<ElementDeclaration> elements = new ArrayList<ElementDeclaration>();

    /**
     * Creates a record type.
     * @param identifier the identifier of this type
     */
    public RecordType(String identifier) {
        super(identifier);
    }

    /**
     * Creates a new element declaration and adds it to this record.
     * @param type the type
     * @param identifiers a list of identifiers
     * @return the created element
     */
    public ElementDeclaration createElement(SubtypeIndication type, List<String> identifiers) {
        ElementDeclaration element = new ElementDeclaration(type, identifiers);
        elements.add(element);
        return element;
    }

    /**
     * Creates a new element declaration and adds it to this record.
     * @param type the type
     * @param identifiers a variable number of identifiers
     * @return the created element
     */
    public ElementDeclaration createElement(SubtypeIndication type, String... identifiers) {
        return createElement(type, Arrays.asList(identifiers));
    }

    /**
     * Returns the elements.
     * @return a modifiable list of element declarations
     */
    public List<ElementDeclaration> getElements() {
        return elements;
    }

    @Override
    void accept(TypeVisitor visitor) {
        visitor.visitRecordType(this);
    }

    /**
     * Element declaration in a record type.
     */
    public static class ElementDeclaration {

        private final List<String> identifiers;
        private SubtypeIndication type;

        private ElementDeclaration(SubtypeIndication type, List<String> identifiers) {
            this.type = type;
            this.identifiers = new ArrayList<String>(identifiers);
        }

        /**
         * Returns the declared identifiers in this element declaration.
         * @return a modifiable list of identifiers
         */
        public List<String> getIdentifiers() {
            return identifiers;
        }

        /**
         * Returns the type of this elements.
         * @return the type
         */
        public SubtypeIndication getType() {
            return type;
        }

        /**
         * Sets the type of this elements.
         * @param type the type
         */
        public void setType(SubtypeIndication type) {
            this.type = type;
        }
    }
}

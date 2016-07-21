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

import de.upb.hni.vmagic.literal.EnumerationLiteral;
import java.util.ArrayList;
import java.util.List;

/**
 * Enumeration type.
 */
public class EnumerationType extends Type {

    private final List<EnumerationLiteral> literals = new ArrayList<EnumerationLiteral>();

    /**
     * Creates an empty enumeration type.
     * @param identifier the identifier
     */
    public EnumerationType(String identifier) {
        super(identifier);
    }

    /**
     * Creates a enumeration type with the given identifier literals.
     * @param identifier the identifier of this enumeration type
     * @param literals the identifier literals
     */
    public EnumerationType(String identifier, String... literals) {
        super(identifier);

        for (String literal : literals) {
            this.literals.add(new IdentifierEnumerationLiteral(literal, this));
        }
    }

    /**
     * Creates a enumeration type with the given character literals.
     * @param identifier the identifier of this enumeration type
     * @param literals the character literals
     */
    public EnumerationType(String identifier, char... literals) {
        super(identifier);

        for (char literal : literals) {
            this.literals.add(new CharacterEnumerationLiteral(literal, this));
        }
    }

    /**
     * Returns the literals.
     * @return a modifiable list of enumeration literals
     */
    public List<EnumerationLiteral> getLiterals() {
        return literals;
    }

    /**
     * Creates a character enumeration literal and adds it to this literal.
     * @param literal the literal value
     * @return the created enumeration literal
     */
    public EnumerationLiteral createLiteral(char literal) {
        EnumerationLiteral l = new CharacterEnumerationLiteral(literal, this);
        literals.add(l);
        return l;
    }

    /**
     * Creates a identifier enumeration literal and adds it to this literal.
     * @param literal the literal value
     * @return the created enumeration literal
     */
    public EnumerationLiteral createLiteral(String literal) {
        EnumerationLiteral l = new IdentifierEnumerationLiteral(literal, this);
        literals.add(l);
        return l;
    }

    @Override
    void accept(TypeVisitor visitor) {
        visitor.visitEnumerationType(this);
    }

    private class IdentifierEnumerationLiteral extends EnumerationLiteral {

        private final String literal;

        private IdentifierEnumerationLiteral(String literal, EnumerationType type) {
            super(type);
            this.literal = literal;
        }

        public String getLiteral() {
            return literal;
        }

        @Override
        public String toString() {
            return literal;
        }
    }

    private class CharacterEnumerationLiteral extends EnumerationLiteral {

        private final char literal;

        public CharacterEnumerationLiteral(char literal, EnumerationType type) {
            super(type);
            this.literal = literal;
        }

        public char getLiteral() {
            return literal;
        }

        @Override
        public String toString() {
            return "'" + literal + "'";
        }
    }
}

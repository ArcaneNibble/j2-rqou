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

import de.upb.hni.vmagic.Signature;
import de.upb.hni.vmagic.type.SubtypeIndication;

/**
 * Alias declaration.
 */
//TODO: replace dummy implementation
public class Alias extends DeclarativeItem
        implements BlockDeclarativeItem, EntityDeclarativeItem, PackageBodyDeclarativeItem,
        PackageDeclarativeItem, ProcessDeclarativeItem, SubprogramDeclarativeItem {

    private String designator;
    private SubtypeIndication subtypeIndication;
    private String aliased;
    private Signature signature;

    /**
     * Creates an alias declartion.
     * @param designator the alias designator
     * @param subtypeIndication the subtype indication
     */
    public Alias(String designator, SubtypeIndication subtypeIndication) {
        this.designator = designator;
        this.subtypeIndication = subtypeIndication;
    }

    /**
     * Creates an alias declaration.
     * @param designator the alias designator
     * @param aliased the identifier of the aliased object
     */
    public Alias(String designator, String aliased) {
        this.designator = designator;
        this.aliased = aliased;
    }

    /**
     * Creates an alias declaration.
     * @param designator the alias designator
     * @param subtypeIndication the subtype indication
     * @param aliased the identifier of the aliased object
     */
    public Alias(String designator, SubtypeIndication subtypeIndication, String aliased) {
        this.designator = designator;
        this.subtypeIndication = subtypeIndication;
        this.aliased = aliased;
    }

    /**
     * Returns the identifier of the aliased object.
     * @return the identifier
     */
    public String getAliased() {
        return aliased;
    }

    /**
     * Sets the identifier of the aliased object.
     * @param aliased the identifier
     */
    public void setAliased(String aliased) {
        this.aliased = aliased;
    }

    /**
     * Returns the alias designator.
     * @return the designator
     */
    public String getDesignator() {
        return designator;
    }

    /**
     * Sets the alias designator.
     * @param designator the designator
     */
    public void setDesignator(String designator) {
        this.designator = designator;
    }

    /**
     * Returns the subtype indication.
     * @return the subtype indication
     */
    public SubtypeIndication getSubtypeIndication() {
        return subtypeIndication;
    }

    /**
     * Sets the subtype indication.
     * @param subtypeIndication the subtype indication
     */
    public void setSubtypeIndication(SubtypeIndication subtypeIndication) {
        this.subtypeIndication = subtypeIndication;
    }

    /**
     * Returns the signature.
     * @return the signature or <code>null</code> if no signature is set
     */
    public Signature getSignature() {
        return signature;
    }

    /**
     * Sets the signature.
     * @param signature the signature or <code>null</code> to remove the signature
     */
    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    @Override
    void accept(DeclarationVisitor visitor) {
        visitor.visitAliasDeclaration(this);
    }
}

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

import de.upb.hni.vmagic.object.VhdlObjectProvider;
import de.upb.hni.vmagic.type.SubtypeIndication;
import java.util.List;

/**
 * Function declaration.
 */
public class FunctionDeclaration extends SubprogramDeclaration implements Function {

    private SubtypeIndication returnType;
    private boolean impure;

    /**
     * Creates a function declaration.
     * @param identifier the identifier
     * @param returnType the return type
     * @param parameters the parameters
     */
    public FunctionDeclaration(String identifier, SubtypeIndication returnType,
            VhdlObjectProvider... parameters) {
        super(identifier, parameters);
        this.returnType = returnType;
    }

    /**
     * Creates a function declaration.
     * @param identifier the identifier
     * @param returnType the return type
     * @param parameters the parameters
     */
    public FunctionDeclaration(String identifier, SubtypeIndication returnType,
            List<VhdlObjectProvider> parameters) {
        super(identifier, parameters);
        this.returnType = returnType;
    }

    public boolean isImpure() {
        return impure;
    }

    public void setImpure(boolean impure) {
        this.impure = impure;
    }

    public SubtypeIndication getReturnType() {
        return returnType;
    }

    public void setReturnType(SubtypeIndication returnType) {
        this.returnType = returnType;
    }

    @Override
    void accept(DeclarationVisitor visitor) {
        visitor.visitFunctionDeclaration(this);
    }
}

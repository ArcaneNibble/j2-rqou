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
import java.util.List;

/**
 * Procedure declaration.
 */
public class ProcedureDeclaration extends SubprogramDeclaration {

    /**
     * Creates a procedure declaration.
     * @param identifier the procedure identifier
     * @param parameters the parameters
     */
    public ProcedureDeclaration(String identifier, List<VhdlObjectProvider> parameters) {
        super(identifier, parameters);
    }

    /**
     * Creates a procedure declaration.
     * @param identifier the procedure identifier
     * @param parameters the parameters
     */
    public ProcedureDeclaration(String identifier, VhdlObjectProvider... parameters) {
        super(identifier, parameters);
    }

    @Override
    void accept(DeclarationVisitor visitor) {
        visitor.visitProcedureDeclaration(this);
    }
}

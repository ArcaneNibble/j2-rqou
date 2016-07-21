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

import de.upb.hni.vmagic.object.Variable;
import java.util.Arrays;
import java.util.List;

/**
 * Variable delcaration.
 */
public class VariableDeclaration extends ObjectDeclaration<Variable>
        implements BlockDeclarativeItem, EntityDeclarativeItem, PackageBodyDeclarativeItem,
        PackageDeclarativeItem, ProcessDeclarativeItem, SubprogramDeclarativeItem {

    /**
     * Creates a new variable declaration.
     * @param variables the declared variables
     */
    public VariableDeclaration(Variable... variables) {
        this(Arrays.asList(variables));
    }

    /**
     * Creates a new variable declaration.
     * @param variables the declared variables
     */
    public VariableDeclaration(List<Variable> variables) {
        super(variables);
    }

    @Override
    void accept(DeclarationVisitor visitor) {
        visitor.visitVariableDeclaration(this);
    }
}

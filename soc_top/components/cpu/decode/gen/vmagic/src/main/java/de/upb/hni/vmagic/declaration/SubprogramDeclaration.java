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

package de.upb.hni.vmagic.declaration;

import de.upb.hni.vmagic.object.VhdlObject;
import de.upb.hni.vmagic.object.VhdlObjectProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract base class for subprogram declarations.
 */
public abstract class SubprogramDeclaration extends DeclarativeItem
        implements BlockDeclarativeItem, EntityDeclarativeItem, PackageBodyDeclarativeItem,
        PackageDeclarativeItem, ProcessDeclarativeItem, SubprogramDeclarativeItem, Subprogram {

    private final List<VhdlObjectProvider<? extends VhdlObject>> parameters =
            new ArrayList<VhdlObjectProvider<? extends VhdlObject>>();
    private String identifier;

    /**
     * Creates a subprogram declaration.
     * @param identifier the identifier of this subprogram declaration
     * @param parameters the parameters
     */
    public SubprogramDeclaration(String identifier, VhdlObjectProvider... parameters) {
        this(identifier, Arrays.asList(parameters));
    }

    /**
     * Creates a subprogram declaration.
     * @param identifier the identifier of this subprogram declaration
     * @param parameters the parameters
     */
    public SubprogramDeclaration(String identifier,
            List<VhdlObjectProvider> parameters) {
        this.identifier = identifier;
        for (VhdlObjectProvider provider : parameters) {
            VhdlObjectProvider<? extends VhdlObject> p =
                    (VhdlObjectProvider<? extends VhdlObject>) provider;
            this.parameters.add(p);
        }
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public List<VhdlObjectProvider<? extends VhdlObject>> getParameters() {
        return parameters;
    }
}

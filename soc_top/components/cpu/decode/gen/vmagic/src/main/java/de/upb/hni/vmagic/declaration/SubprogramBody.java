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

import de.upb.hni.vmagic.DeclarativeRegion;
import de.upb.hni.vmagic.Scope;
import de.upb.hni.vmagic.Scopes;
import de.upb.hni.vmagic.object.VhdlObject;
import de.upb.hni.vmagic.object.VhdlObjectProvider;
import de.upb.hni.vmagic.statement.SequentialStatement;
import de.upb.hni.vmagic.util.ParentSetList;
import de.upb.hni.vmagic.util.ResolvableList;
import de.upb.hni.vmagic.util.VhdlCollections;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract base class for subprogram bodies.
 */
public abstract class SubprogramBody extends DeclarativeItem
        implements BlockDeclarativeItem, EntityDeclarativeItem, PackageBodyDeclarativeItem,
        ProcessDeclarativeItem, SubprogramDeclarativeItem, DeclarativeRegion, Subprogram {

    private final ResolvableList<VhdlObjectProvider<? extends VhdlObject>> parameters =
            VhdlCollections.createVhdlObjectList();
    private String identifier;
    private final ResolvableList<SubprogramDeclarativeItem> declarations =
            VhdlCollections.createDeclarationList();
    private final List<SequentialStatement> statements = ParentSetList.create(this);
    private final Scope scope = Scopes.createScope(this, parameters, declarations);

    /**
     * Creates a subprogram body.
     * @param identifier the identifier of this subprogram body
     * @param parameters the parameters
     */
    public SubprogramBody(String identifier, VhdlObjectProvider... parameters) {
        this(identifier, Arrays.asList(parameters));
    }

    /**
     * Creates a subprogram body.
     * @param identifier the identifier of this subprogram body
     * @param parameters the parameters
     */
    public SubprogramBody(String identifier, List<VhdlObjectProvider> parameters) {
        this.identifier = identifier;
        for (VhdlObjectProvider provider : parameters) {
            VhdlObjectProvider<? extends VhdlObject> p =
                    (VhdlObjectProvider<? extends VhdlObject>) provider;
            this.parameters.add(p);
        }
    }

    /**
     * Creates a subprogram body based on a subprogram declaration.
     * @param declaration the subprogam declaration
     */
    //TODO: link subprogram body to declaration
    public SubprogramBody(SubprogramDeclaration declaration) {
        this.identifier = declaration.getIdentifier();
        this.parameters.addAll(declaration.getParameters());
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

    /**
     * Returns the declarations.
     * @return a modifiable list of subprogram declarative items
     */
    public List<SubprogramDeclarativeItem> getDeclarations() {
        return declarations;
    }

    /**
     * Returns the statements.
     * @return a modifiable list of sequential statements
     */
    public List<SequentialStatement> getStatements() {
        return statements;
    }

    @Override
    public Scope getScope() {
        return scope;
    }
}

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

package de.upb.hni.vmagic.concurrent;

import de.upb.hni.vmagic.AssociationElement;
import de.upb.hni.vmagic.DeclarativeRegion;
import de.upb.hni.vmagic.Resolvable;
import de.upb.hni.vmagic.Scope;
import de.upb.hni.vmagic.Scopes;
import de.upb.hni.vmagic.builtin.Standard;
import de.upb.hni.vmagic.object.Constant;
import de.upb.hni.vmagic.object.Signal;
import de.upb.hni.vmagic.declaration.BlockDeclarativeItem;
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.object.VhdlObjectProvider;
import de.upb.hni.vmagic.util.ResolvableList;
import de.upb.hni.vmagic.util.VhdlCollections;
import java.util.ArrayList;
import java.util.List;

/**
 * Block statement.
 */
public class BlockStatement extends ConcurrentStatement implements DeclarativeRegion {

    private Expression guardExpression;
    private final ResolvableList<VhdlObjectProvider<Signal>> port =
            VhdlCollections.createVhdlObjectList();
    private final ResolvableList<VhdlObjectProvider<Constant>> generic =
            VhdlCollections.createVhdlObjectList();
    private final ResolvableList<BlockDeclarativeItem> declarations =
            VhdlCollections.createDeclarationList();
    private final List<ConcurrentStatement> statements =
            VhdlCollections.createLabeledElementList(this);
    private List<AssociationElement> portMap = new ArrayList<AssociationElement>();
    private List<AssociationElement> genericMap = new ArrayList<AssociationElement>();
    private final Scope scope = Scopes.createScope(this, port, generic,
            declarations, new GuardSignalResolvable());

    /**
     * Creates a block statement.
     * @param label the label
     */
    public BlockStatement(String label) {
        setLabel(label);
    }

    /**
     * Creates a block statement with a guard epxression.
     * @param label the label
     * @param guardExpression the guard expression
     */
    public BlockStatement(String label, Expression guardExpression) {
        setLabel(label);
        this.guardExpression = guardExpression;
    }

    /**
     * Returns the guard expression.
     * @return the guard expression or <code>null</code> if no guard expression is set
     */
    public Expression getGuardExpression() {
        return guardExpression;
    }

    /**
     * Sets the guard expression.
     * @param guardExpression the guard expression or
     *                        <code>null</code> to remove the guard expression
     */
    public void setGuardExpression(Expression guardExpression) {
        this.guardExpression = guardExpression;
    }

    /**
     * Returns the generic clause.
     * @return a modifiable list of constants
     */
    public List<VhdlObjectProvider<Constant>> getGeneric() {
        return generic;
    }

    /**
     * Returns the port clause.
     * @return a modifiable list of signals
     */
    public List<VhdlObjectProvider<Signal>> getPort() {
        return port;
    }

    /**
     * Returns the generic map.
     * @return a modifiable list of association elements
     */
    public List<AssociationElement> getGenericMap() {
        return genericMap;
    }

    /**
     * Returns the port map.
     * @return a modifiable list of association elements
     */
    public List<AssociationElement> getPortMap() {
        return portMap;
    }

    /**
     * Returns the declarations.
     * @return a modifiable list of block declarative items
     */
    public List<BlockDeclarativeItem> getDeclarations() {
        return declarations;
    }

    /**
     * Returns the statements.
     * @return a modifiable list of concurrent statements
     */
    public List<ConcurrentStatement> getStatements() {
        return statements;
    }

    public Scope getScope() {
        return scope;
    }

    @Override
    void accept(ConcurrentStatementVisitor visitor) {
        visitor.visitBlockStatement(this);
    }

    private class GuardSignalResolvable implements Resolvable {

        public Object resolve(String identifier) {
            if (guardExpression != null && identifier.equalsIgnoreCase("GUARD")) {
                return new Signal("guard", Standard.BOOLEAN);
            } else {
                return null;
            }
        }
    }
}

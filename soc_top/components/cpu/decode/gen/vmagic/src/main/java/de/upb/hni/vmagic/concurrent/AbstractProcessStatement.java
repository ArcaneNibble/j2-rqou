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

import de.upb.hni.vmagic.DeclarativeRegion;
import de.upb.hni.vmagic.Resolvable;
import de.upb.hni.vmagic.Scope;
import de.upb.hni.vmagic.Scopes;
import de.upb.hni.vmagic.object.Signal;
import de.upb.hni.vmagic.declaration.ProcessDeclarativeItem;
import de.upb.hni.vmagic.statement.SequentialStatement;
import de.upb.hni.vmagic.util.ResolvableList;
import de.upb.hni.vmagic.util.VhdlCollections;
import java.util.List;

/**
 * Abstract base class for process statements.
 */
public abstract class AbstractProcessStatement extends EntityStatement
        implements DeclarativeRegion {

    private final ResolvableImpl resolvable = new ResolvableImpl();
    private final Scope scope = Scopes.createScope(this, resolvable);

    /**
     * Creates an abstract process statement without a label.
     */
    public AbstractProcessStatement() {
    }

    /**
     * Creates an abstract process statement with the given label.
     * @param label the label
     */
    public AbstractProcessStatement(String label) {
        setLabel(label);
    }

    /**
     * Returns the sensitivity list.
     * @return the list of signals in the sensitivity list
     */
    public abstract List<Signal> getSensitivityList();

    /**
     * Returns the declarations.
     * @return a list of process declarative items
     */
    public abstract List<ProcessDeclarativeItem> getDeclarations();

    /**
     * Returns the statements.
     * @return a list of sequential statements
     */
    public abstract List<SequentialStatement> getStatements();

    public Scope getScope() {
        return scope;
    }

    @Override
    void accept(ConcurrentStatementVisitor visitor) {
        visitor.visitProcessStatement(this);
    }

    private class ResolvableImpl implements Resolvable {

        public Object resolve(String identifier) {
            ResolvableList<SequentialStatement> stmts =
                    VhdlCollections.createLabeledElementList(AbstractProcessStatement.this, getStatements());
            Object result = stmts.resolve(identifier);
            if (result != null) {
                return result;
            }

            ResolvableList<ProcessDeclarativeItem> decls =
                    VhdlCollections.createDeclarationList(getDeclarations());
            return decls.resolve(identifier);
        }
    }
}

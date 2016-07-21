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

package de.upb.hni.vmagic.statement;

import de.upb.hni.vmagic.DeclarativeRegion;
import de.upb.hni.vmagic.Resolvable;
import de.upb.hni.vmagic.Scope;
import de.upb.hni.vmagic.Scopes;
import de.upb.hni.vmagic.util.ParentSetList;
import java.util.List;

/**
 * Loop.
 *
 * @vmagic.example
 * LoopStatement loop = new LoopStatement();
 * loop.getStatements().add(new NullStatement());
 * ---
 * loop
 *  null;
 * end loop;
 */
public class LoopStatement extends SequentialStatement implements DeclarativeRegion {

    private final Resolvable resolvable = new ResolvableImpl();
    private final List<SequentialStatement> statements = ParentSetList.create(this);
    private final Scope scope = Scopes.createScope(this, resolvable);

    /**
     * Creates a loop statement.
     */
    public LoopStatement() {
    }

    /**
     * Returns the statements.
     * @return a modifiable list of sequential statements
     */
    public List<SequentialStatement> getStatements() {
        return statements;
    }

    @Override
    void accept(SequentialStatementVisitor visitor) {
        visitor.visitLoopStatement(this);
    }

    public Scope getScope() {
        return scope;
    }

    private class ResolvableImpl implements Resolvable {

        public Object resolve(String identifier) {
            if (identifier.equalsIgnoreCase(getLabel())) {
                return LoopStatement.this;
            }

            //TODO: move to ForStatement
            if (LoopStatement.this instanceof ForStatement) {
                ForStatement forStatement = (ForStatement) LoopStatement.this;
                if (identifier.equalsIgnoreCase(forStatement.getParameter().getIdentifier())) {
                    return forStatement.getParameter();
                }
            }

            return null;
        }
    }
}

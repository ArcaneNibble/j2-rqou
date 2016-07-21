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

import de.upb.hni.vmagic.object.Signal;
import de.upb.hni.vmagic.declaration.ProcessDeclarativeItem;
import de.upb.hni.vmagic.statement.SequentialStatement;
import de.upb.hni.vmagic.util.ParentSetList;
import java.util.ArrayList;
import java.util.List;

/**
 * Process statement.
 */
public class ProcessStatement extends AbstractProcessStatement {

    private final List<ProcessDeclarativeItem> declarations =
            new ArrayList<ProcessDeclarativeItem>();
    private final List<SequentialStatement> statements = ParentSetList.create(this);
    private final List<Signal> sensitivityList = new ArrayList<Signal>();

    /**
     * Creates a process statement without a label.
     */
    public ProcessStatement() {
    }

    /**
     * Creates a process statement.
     * @param label the process label
     */
    public ProcessStatement(String label) {
        super(label);
    }

    /**
     * Returns the declarations.
     * @return a modifiable list of process declarative items
     */
    public List<ProcessDeclarativeItem> getDeclarations() {
        return declarations;
    }

    /**
     * Returns the statements.
     * @return a modifiable list of sequential statements
     */
    public List<SequentialStatement> getStatements() {
        return statements;
    }

    /**
     * Returns the sensitivity list.
     * @return a modifiable list of signals
     */
    public List<Signal> getSensitivityList() {
        return sensitivityList;
    }
}

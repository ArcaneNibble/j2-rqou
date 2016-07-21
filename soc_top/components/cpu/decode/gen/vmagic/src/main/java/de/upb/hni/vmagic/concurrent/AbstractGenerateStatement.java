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
import de.upb.hni.vmagic.declaration.BlockDeclarativeItem;
import de.upb.hni.vmagic.util.VhdlCollections;
import java.util.List;

/**
 * Abstract base class for generate statements.
 */
public abstract class AbstractGenerateStatement extends ConcurrentStatement
        implements DeclarativeRegion {

    private final List<BlockDeclarativeItem> declarations =
            VhdlCollections.createDeclarationList();
    private final List<ConcurrentStatement> statements =
            VhdlCollections.createLabeledElementList(this);

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
}

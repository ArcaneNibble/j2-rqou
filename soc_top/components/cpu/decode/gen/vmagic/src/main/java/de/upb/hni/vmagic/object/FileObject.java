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

package de.upb.hni.vmagic.object;

import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.type.SubtypeIndication;
import java.util.Collections;
import java.util.List;

/**
 * VHDL File Object.
 */
public class FileObject extends DefaultVhdlObject<FileObject> {

    private Expression openKind;
    private Expression logicalName;

    /**
     * Creates a file.
     * @param identifier the identifier
     * @param type the type
     * @param openKind the open kind
     * @param logicalName the logical name
     */
    public FileObject(String identifier, SubtypeIndication type, Expression openKind, Expression logicalName) {
        super(identifier, type);
        this.openKind = openKind;
        this.logicalName = logicalName;
    }

    /**
     * Creates a file.
     * @param identifier the identifier
     * @param type the type
     * @param logicalName the logical name
     */
    public FileObject(String identifier, SubtypeIndication type, Expression logicalName) {
        super(identifier, type);
        this.logicalName = logicalName;
    }

    /**
     * Creates a file.
     * @param identifier the identifier
     * @param type the type
     */
    public FileObject(String identifier, SubtypeIndication type) {
        super(identifier, type);
    }

    /**
     * Returns the logical name.
     * @return the logical name
     */
    public Expression getLogicalName() {
        return logicalName;
    }

    /**
     * Sets the logical name.
     * @param logicalName the logical name
     */
    public void setLogicalName(Expression logicalName) {
        this.logicalName = logicalName;
    }

    /**
     * Returns the file open kind.
     * @return the open kind
     */
    public Expression getOpenKind() {
        return openKind;
    }

    /**
     * Sets the file open kind.
     * @param openKind the open kind
     */
    public void setOpenKind(Expression openKind) {
        this.openKind = openKind;
    }

    @Override
    public List<FileObject> getVhdlObjects() {
        return Collections.singletonList(this);
    }

    @Override
    public ObjectClass getObjectClass() {
        return ObjectClass.FILE;
    }

    @Override
    public void setMode(Mode mode) {
        throw new IllegalStateException("Setting the mode is not supported for files");
    }
}

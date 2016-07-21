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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * File group.
 */
public class FileGroup extends VhdlObjectGroup<FileObject> {

    private final List<FileObject> files;

    /**
     * Creates a group of files.
     * @param files a list of files
     */
    public FileGroup(List<FileObject> files) {
        this.files = new ArrayList<FileObject>(files);
    }

    /**
     * Creates a group of files.
     * @param files a variable number of files
     */
    public FileGroup(FileObject... files) {
        this(Arrays.asList(files));
    }

    /**
     * Returns the files in this group.
     * @return a modifiable list of files
     */
    @Override
    public List<FileObject> getElements() {
        return files;
    }

    @Override
    public List<FileObject> getVhdlObjects() {
        return Collections.unmodifiableList(files);
    }
}

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

package de.upb.hni.vmagic;

import java.util.Map;

/**
 * An element inside a VHDL file.
 */
public abstract class VhdlElement {

    private Map<Class<?>, Object> annotationList;
    private DeclarativeRegion parent;

    /**
     * Returns the annotation list for this VhdlElement.
     * The vMAGIC user normally doesn't need to use this function directly.
     * Use the methods provided by the <code>Annotations</code> class instead.
     * @return the annotation list
     */
    Map<Class<?>, Object> getAnnotationList() {
        return annotationList;
    }

    /**
     * Sets the annotation list for this VhdlElement.
     * The vMAGIC user normally doesn't need to use this function directly.
     * Use the methods provided by the <code>Annotations</code> class instead.
     * @param list the annotation list
     */
    void setAnnotationList(Map<Class<?>, Object> list) {
        this.annotationList = list;
    }

    /**
     * Sets the parent of this <code>VhdlChild</code>.
     * @param parent the parent
     */
    public void setParent(DeclarativeRegion parent) {
        this.parent = parent;
    }

    /**
     * Returns the parent of this <code>VhdlChild</code>.
     * @return the parent
     */
    public DeclarativeRegion getParent() {
        return parent;
    }
}

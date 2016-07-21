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

import java.util.HashMap;

/**
 * Utility class to retrieve and manipulate annotations.
 * Annotations are object of arbitrary classes that can be added to an instance of a meta class.
 * They can be used to add additional informations to a meta class instance. An example of using
 * anotations is the vMAGIC VHDL parser that stores error information in the created meta class
 * instances. Annotations are also used to store information about comments.
 */
public class Annotations {

    private Annotations() {
    }

    /**
     * Returns an annotation instance of the given class.
     * If no instance of the class is available in the element the function returns
     * <code>null</code>.
     * @param <T>
     * @param element the element
     * @param clazz the class of the instance
     * @return the instance, or <code>null</code>
     */
    public static <T> T getAnnotation(VhdlElement element, Class<T> clazz) {
        if (element.getAnnotationList() == null) {
            return null;
        } else {
            return clazz.cast(element.getAnnotationList().get(clazz));
        }
    }

    /**
     * Stores an annotation in the given element.
     * If an annotation of the same class existed before the call to this function it is replaced
     * by the new instance.
     * @param <T>
     * @param element the element
     * @param clazz the class of the instance
     * @param value the instance
     */
    public static <T> void putAnnotation(VhdlElement element, Class<T> clazz, T value) {
        if (element.getAnnotationList() == null) {
            element.setAnnotationList(new HashMap<Class<?>, Object>());
        }

        element.getAnnotationList().put(clazz, value);
    }
}

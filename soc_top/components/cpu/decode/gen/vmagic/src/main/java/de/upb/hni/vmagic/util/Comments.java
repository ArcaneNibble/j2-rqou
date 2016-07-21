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
package de.upb.hni.vmagic.util;

import de.upb.hni.vmagic.Annotations;
import de.upb.hni.vmagic.VhdlElement;
import de.upb.hni.vmagic.annotation.CommentAnnotationAfter;
import de.upb.hni.vmagic.annotation.CommentAnnotationBefore;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Comment utility functions.
 */
public class Comments {

    private Comments() {
    }

    /**
     * Returns the comments before the given vhdl element.
     * @param element the vhdl element
     * @return a list of line comments
     */
    public static List<String> getComments(VhdlElement element) {
        CommentAnnotationBefore annotation = Annotations.getAnnotation(element, CommentAnnotationBefore.class);
        if (annotation == null) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableList(annotation.getComments());
        }
    }

    /**
     * Returns the comments after the given vhdl element.
     * @param element the vhdl element
     * @return a list of line comments
     */
    public static List<String> getCommentsAfter(VhdlElement element) {
        CommentAnnotationAfter annotation = Annotations.getAnnotation(element, CommentAnnotationAfter.class);
        if (annotation == null) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableList(annotation.getComments());
        }
    }

    /**
     * Sets the comments before a vhdl element.
     * @param element the vhdl element
     * @param comments a list of line comments
     */
    public static void setComments(VhdlElement element, List<String> comments) {
        CommentAnnotationBefore annotation = new CommentAnnotationBefore(comments);
        Annotations.putAnnotation(element, CommentAnnotationBefore.class, annotation);
    }

    /**
     * Sets the comments before a vhdl element.
     * @param element the vhdl element
     * @param comments zero or more line comments
     */
    public static void setComments(VhdlElement element, String... comments) {
        setComments(element, Arrays.asList(comments));
    }

    /**
     * Sets the comments after a vhdl element.
     * @param element the vhdl element
     * @param comments a list of line comments
     */
    public static void setCommentsAfter(VhdlElement element, List<String> comments) {
        CommentAnnotationAfter annotation = new CommentAnnotationAfter(comments);
        Annotations.putAnnotation(element, CommentAnnotationAfter.class, annotation);
    }

    /**
     * Sets the comments after a vhdl element.
     * @param element the vhdl element
     * @param comments zero or more line comments
     */
    public static void setCommentsAfter(VhdlElement element, String... comments) {
        setComments(element, Arrays.asList(comments));
    }
}

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
package de.upb.hni.vmagic.annotation;

import java.util.List;

/**
 * Comment annotation (after a VhdlElement).
 * The comment annotations are used to store VHDL comments in before and after
 * a VhdlElement. Each String in the list of comments represents a single line
 * comment. The strings must not contain line breaks. The
 * {@link de.upb.hni.vmagic.util.Comments} utility class provides an easier
 * interface to set and get comments.
 *
 * @see de.upb.hni.vmagic.util.Comments
 */
public class CommentAnnotationAfter extends AbstractCommentAnnotation {

    /**
     * Creates a new comment annotation.
     * @param comments zero or more line comments
     */
    public CommentAnnotationAfter(String... comments) {
        super(comments);
    }

    /**
     * Creates a new comment annotation.
     * @param comments a list of line comments
     */
    public CommentAnnotationAfter(List<String> comments) {
        super(comments);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.upb.hni.vmagic.annotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract comment annotation.
 * The comment annotations are used to store VHDL comments in before and after
 * a VhdlElement. Each String in the list of comments represents a single line
 * comment. The strings must not contain line breaks. The
 * {@link de.upb.hni.vmagic.util.Comments} utility class provides an easier
 * interface to set and get comments.
 *
 * @see de.upb.hni.vmagic.util.Comments
 */
public abstract class AbstractCommentAnnotation {

    private final List<String> comments;

    /**
     * Creates a new comment annotation.
     * @param comments zero or more line comments
     */
    public AbstractCommentAnnotation(String... comments) {
        this.comments = Arrays.asList(comments);
    }

    /**
     * Creates a new comment annotation.
     * @param comments a list of line comments
     */
    public AbstractCommentAnnotation(List<String> comments) {
        this.comments = new ArrayList<String>(comments);
    }

    /**
     * Returns a modifiable list of line comments.
     * @return a list of line comments
     */
    public List<String> getComments() {
        return comments;
    }
}

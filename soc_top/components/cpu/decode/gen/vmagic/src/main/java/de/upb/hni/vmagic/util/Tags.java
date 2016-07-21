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

import de.upb.hni.vmagic.VhdlElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for handling tags.
 */
public class Tags {

    /**
     * Returns the list of tags in front of the element.
     * @param element the element
     * @return an unmodifiable list of tags
     */
    public static List<String> getTags(VhdlElement element) {
        List<String> tags = new ArrayList<String>();
        for (String comment : Comments.getComments(element)) {
            if (comment.startsWith("*")) {
                tags.add(comment.substring(1));
            }
        }

        return Collections.unmodifiableList(tags);
    }

    /**
     * Returns the value of a tag with the format "--name:value".
     * If there are multiple tags with the same name, the first one is returned.
     * @param element the element
     * @param name the name of the tag
     * @return the value of the tag or
     *         <code>null</code> if no tag with the given name was found
     */
    public static String getTag(VhdlElement element, String name) {
        for (String comment : Comments.getComments(element)) {
            if (comment.startsWith("*")) {
                String[] parts = comment.substring(1).split(":", 2);

                if (parts.length == 2 && parts[0].trim().equals(name)) {
                    return parts[1].trim();
                }
            }
        }

        return null;
    }

    //prevent instantiation
    private Tags() {
    }
}

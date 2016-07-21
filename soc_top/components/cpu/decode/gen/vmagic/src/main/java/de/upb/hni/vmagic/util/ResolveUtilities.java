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

import de.upb.hni.vmagic.DeclarativeRegion;
import de.upb.hni.vmagic.Scope;

/**
 * Resolve utilities.
 */
public class ResolveUtilities {

    private ResolveUtilities() {
    }

    /**
     * Resolves an path in a scope.
     * @param root the scope
     * @param path the path
     * @return the object or <code>null</code>
     */
    public static Object resolvePath(DeclarativeRegion root, String path) {
        String[] parts = path.split(":");
        Scope scope = getPathScope(root, parts);
        return (scope == null ? null : scope.resolve(parts[parts.length - 1]));
    }

    /**
     * Resolves a path in a scope.
     * @param <T> the type
     * @param root the scope
     * @param path the path
     * @param clazz the type
     * @return the object or <code>null</code>
     */
    public static <T> T resolvePath(DeclarativeRegion root, String path, Class<T> clazz) {
        String[] parts = path.split(":");
        Scope scope = getPathScope(root, parts);
        return (scope == null ? null : scope.resolve(parts[parts.length - 1], clazz));
    }

    private static Scope getPathScope(DeclarativeRegion root, String[] parts) {
        Scope scope = root.getScope();

        for (int i = 0; i < parts.length - 1; i++) {
            DeclarativeRegion r = scope.resolve(parts[i], DeclarativeRegion.class);
            if (r == null) {
                return null;
            }
            scope = r.getScope();
            if (scope == null) {
                return null;
            }
        }

        return scope;
    }
}

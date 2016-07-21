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

/**
 * Scope.
 */
public interface Scope {

    /**
     * Resolves the given identifier in this scope and its parent scopes.
     * @param identifier the identifier
     * @return the resolved object or <code>null</code>
     */
    public Object resolve(String identifier);

    /**
     * Resolves the given identifier in this scope and its parent scopes with
     * a specific type.
     * @param <T> the type
     * @param identifier the identifier
     * @param clazz the type
     * @return the resolved object or <code>null</code>
     */
    public <T> T resolve(String identifier, Class<T> clazz);

    /**
     * Resolves the given identifier in this scope.
     * @param identifier the identifier
     * @return the resolved object or <code>null</code>
     */
    public Object resolveLocal(String identifier);

    /**
     * Resolves the given identifier in this scope with a specific type.
     * @param <T> the type
     * @param identifier the identifier
     * @param clazz the type
     * @return the resolved object or <code>null</code>
     */
    public <T> T resolveLocal(String identifier, Class<T> clazz);
}

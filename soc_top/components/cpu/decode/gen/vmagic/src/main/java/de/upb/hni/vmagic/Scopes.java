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
 * Default implementations of the Scope interface.
 * This class is used to implement vMAGIC scope feature and isn't usually used by
 * the user of the library.
 */
public class Scopes {

    /**
     * Creates a scope.
     * @param parent the associated VhdlElement
     * @param list a list of resolvables
     * @return the created scope
     */
    public static Scope createScope(VhdlElement parent, Resolvable... list) {
        return new ResolvableListsScope(parent, list);
    }

    private Scopes() {
    }

    private abstract static class AbstractScope implements Scope {

        private final VhdlElement parent;

        public AbstractScope(VhdlElement parent) {
            this.parent = parent;
        }

        public <T> T resolveLocal(String identifier, Class<T> clazz) {
            Object o = resolveLocal(identifier);

            if (clazz.isInstance(o)) {
                //warning can be removed because the cast is checked by the surrounding if statement
                @SuppressWarnings("unchecked")
                T tmp = (T) o;
                return tmp;
            }

            return null;
        }

        public Object resolve(String identifier) {
            Object o = resolveLocal(identifier);

            if (o == null && getParentScope() != null) {
                return getParentScope().resolve(identifier);
            }

            return o;
        }

        public <T> T resolve(String identifier, Class<T> clazz) {
            Object o = resolve(identifier);

            if (clazz.isInstance(o)) {
                //warning can be removed because the cast is checked by the surrounding if statement
                @SuppressWarnings("unchecked")
                T tmp = (T) o;
                return tmp;
            }

            return null;
        }

        private Scope getParentScope() {
            if (parent != null && parent.getParent() != null) {
                return parent.getParent().getScope();
            } else {
                return null;
            }
        }
    }

    private static class ResolvableListsScope extends AbstractScope {

        private final Resolvable[] lists;

        public ResolvableListsScope(VhdlElement parent, Resolvable... lists) {
            super(parent);
            this.lists = lists;
        }

        @Override
        public Object resolveLocal(String identifier) {
            for (Resolvable list : lists) {
                Object o = list.resolve(identifier);
                if (o != null) {
                    return o;
                }
            }
            return null;
        }
    }
}

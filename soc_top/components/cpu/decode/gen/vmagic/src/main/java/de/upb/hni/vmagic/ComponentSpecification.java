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

import de.upb.hni.vmagic.declaration.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Component specification.
 * A component specification is used in component configurations and configuration specifications
 * to specify a list of component instances.
 */
public abstract class ComponentSpecification extends VhdlElement {

    private final Component component;

    private ComponentSpecification(Component component) {
        this.component = component;
    }

    /**
     * Creates a component specification.
     * @param component the component
     * @param labels a list of component labels
     * @return the created component sepecification
     */
    public static ComponentSpecification create(Component component, List<String> labels) {
        return new ListComponentSpecification(component, labels);
    }

    /**
     * Creates a component specification.
     * @param component the component
     * @param labels a list of component labels
     * @return the created component specification
     */
    public static ComponentSpecification create(Component component, String... labels) {
        return new ListComponentSpecification(component, Arrays.asList(labels));
    }

    /**
     * Creates a component sepecification for all instances.
     * @param component the component
     * @return the created component specification
     */
    public static ComponentSpecification createAll(Component component) {
        return new AllComponentSpecification(component);
    }

    /**
     * Creates a component specification for the other instances.
     * @param component the component
     * @return the created component specification
     */
    public static ComponentSpecification createOthers(Component component) {
        return new OthersComponentSpecification(component);
    }

    /**
     * Returns the type of this component specification.
     * @return the type
     */
    public abstract Type getType();

    /**
     * Returns the list of component labels.
     * @return a modifiable list of component labels or <code>null</code>
     *         if this component has no labels
     */
    public abstract List<String> getLabels();

    /**
     * Returns the component.
     * @return the component
     */
    public Component getComponent() {
        return component;
    }

    private static class ListComponentSpecification extends ComponentSpecification {

        private final List<String> labels;

        public ListComponentSpecification(Component component, List<String> labels) {
            super(component);
            this.labels = new ArrayList<String>(labels);
        }

        public List<String> getLabels() {
            return labels;
        }

        @Override
        public Type getType() {
            return Type.INSTANTIATION_LIST;
        }
    }

    private static class AllComponentSpecification extends ComponentSpecification {

        public AllComponentSpecification(Component component) {
            super(component);
        }

        @Override
        public Type getType() {
            return Type.ALL;
        }

        @Override
        public List<String> getLabels() {
            return null;
        }
    }

    private static class OthersComponentSpecification extends ComponentSpecification {

        public OthersComponentSpecification(Component component) {
            super(component);
        }

        @Override
        public Type getType() {
            return Type.OTHERS;
        }

        @Override
        public List<String> getLabels() {
            return null;
        }
    }

    /**
     * Component specification type.
     */
    public enum Type {

        /**
         * Component specification with list of labels.
         */
        INSTANTIATION_LIST,
        /**
         * Component specification that uses the OTHERS keyword.
         */
        OTHERS,
        /**
         * Component specification that uses the ALL keyword.
         */
        ALL
    }
}

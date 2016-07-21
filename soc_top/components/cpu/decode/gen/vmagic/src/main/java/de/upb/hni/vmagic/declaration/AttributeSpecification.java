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

package de.upb.hni.vmagic.declaration;

import de.upb.hni.vmagic.Signature;
import de.upb.hni.vmagic.VhdlElement;
import de.upb.hni.vmagic.expression.Expression;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Attribute specification.
 * An attribute specification is used to set an attribute of a named entity.
 *
 * @vmagic.example
 * Attribute attrib = new Attribute("ATTRIB", Standard.STRING);
 * AttributeSpecification s = new AttributeSpecification(attrib, EntityNameList.ALL,
 *  EntityClass.SIGNAL, new StringLiteral("value"));
 * ---
 * attribute ATTRIB of all : signal is "value";
 */
public class AttributeSpecification extends DeclarativeItem
        implements BlockDeclarativeItem, ConfigurationDeclarativeItem, EntityDeclarativeItem,
        PackageDeclarativeItem, ProcessDeclarativeItem, SubprogramDeclarativeItem {

    private Attribute attribute;
    private EntityNameList entities;
    private EntityClass entityClass;
    private Expression value;

    /**
     * Creates an attribute specification.
     * @param attribute the attribute
     * @param entities the list of entity names
     * @param entityClass the class of the entities
     * @param value the value
     */
    public AttributeSpecification(Attribute attribute, EntityNameList entities,
            EntityClass entityClass, Expression value) {
        this.attribute = attribute;
        this.entities = entities;
        this.entityClass = entityClass;
        this.value = value;
    }

    /**
     * Returns the specified attribute.
     * @return the attribute
     */
    public Attribute getAttribute() {
        return attribute;
    }

    /**
     * Sets the specified attribute.
     * @param attribute the attribute
     */
    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    /**
     * Returns the list of entities.
     * @return a list of entities
     */
    public EntityNameList getEntities() {
        return entities;
    }

    /**
     * Sets the list of entitites.
     * @param entities a list of entities
     */
    public void setEntities(EntityNameList entities) {
        this.entities = entities;
    }

    /**
     * Returns the entity class.
     * @return the entity class
     */
    public EntityClass getEntityClass() {
        return entityClass;
    }

    /**
     * Sets the entity class.
     * @param entityClass the entity class
     */
    public void setEntityClass(EntityClass entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Returns the value.
     * @return the value
     */
    public Expression getValue() {
        return value;
    }

    /**
     * Sets the value.
     * @param value the value
     */
    public void setValue(Expression value) {
        this.value = value;
    }

    @Override
    void accept(DeclarationVisitor visitor) {
        visitor.visitAttributeSpecification(this);
    }

    /**
     * Entity name list.
     */
    public static class EntityNameList extends VhdlElement {

        private final List<EntityDesignator> designators;
        /**
         * OTHERS.
         */
        public static final EntityNameList OTHERS = new EntityNameList(true) {
        };
        /**
         * ALL
         */
        public static final EntityNameList ALL = new EntityNameList(true) {
        };

        /**
         * Creates an empty entity name list.
         */
        public EntityNameList() {
            designators = new ArrayList<EntityDesignator>();
        }

        /**
         * Creates a entity name list.
         * @param designators a list of designators used to initialize the entity name list
         */
        public EntityNameList(List<EntityDesignator> designators) {
            this.designators = new ArrayList<EntityDesignator>(designators);
        }

        /**
         * Creates a entity name list.
         * @param designators a list of designators used to initialize the entity name list
         */
        public EntityNameList(EntityDesignator... designators) {
            this(Arrays.asList(designators));
        }

        private EntityNameList(boolean nullList) {
            if (nullList) {
                designators = null;
            } else {
                designators = new ArrayList<EntityDesignator>();
            }
        }

        /**
         * Returns the list of designators in this entity name list.
         * The method returns <code>null</code> if there is no list of designators for this type of
         * entity name list.
         * @return a list of designators or <code>null</code>
         */
        public List<EntityDesignator> getDesignators() {
            return designators;
        }

        /**
         * Creates a entity designator and adds it to this entity name list.
         * @param entityTag the tag of the designator
         * @return the created entity designator
         */
        public EntityDesignator createDesignator(String entityTag) {
            if (designators == null) {
                throw new IllegalStateException("cannot add designator to fixed entity name list");
            }

            EntityDesignator designator = new EntityDesignator(entityTag);
            designators.add(designator);

            return designator;
        }

        /**
         * Creates a entity designator with a signature and adds it to this entity name list.
         * @param entityTag the tag of the designator
         * @param signature the signature of the designator
         * @return the created designator
         */
        public EntityDesignator createDesignator(String entityTag, Signature signature) {
            if (designators == null) {
                throw new IllegalStateException("cannot add designator to fixed entity name list");
            }

            EntityDesignator designator = new EntityDesignator(entityTag, signature);
            designators.add(designator);

            return designator;
        }

        /**
         * Entity designator.
         */
        public static class EntityDesignator extends VhdlElement {

            //TODO: don't use String?
            private String entityTag;
            private Signature signature;

            /**
             * Creates a entity designator.
             * @param entityTag the tag of the designator
             */
            public EntityDesignator(String entityTag) {
                this.entityTag = entityTag;
            }

            /**
             * Creates a entity designator with a signature.
             * @param entityTag the tag
             * @param signature the signature
             */
            public EntityDesignator(String entityTag, Signature signature) {
                this.entityTag = entityTag;
                this.signature = signature;
            }

            /**
             * Returns the tag.
             * @return the tag
             */
            public String getEntityTag() {
                return entityTag;
            }

            /**
             * Sets the tag.
             * @param entityTag the tag
             */
            public void setEntityTag(String entityTag) {
                this.entityTag = entityTag;
            }

            /**
             * Returns the signature.
             * The function returns <code>null</code> if this designator has no signature.
             * @return the signature or <code>null</code>
             */
            public Signature getSignature() {
                return signature;
            }

            /**
             * Sets the signature.
             * Use <code>null</code> as the parameter to disable the signature.
             * @param signature the signature or <code>null</code>
             */
            public void setSignature(Signature signature) {
                this.signature = signature;
            }
        }
    }
}

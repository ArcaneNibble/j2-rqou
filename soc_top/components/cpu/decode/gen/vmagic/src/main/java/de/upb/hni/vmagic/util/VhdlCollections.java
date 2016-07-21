/*
 * Copyright 2009, 2010, 2011 University of Paderborn
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
import de.upb.hni.vmagic.LabeledElement;
import de.upb.hni.vmagic.NamedEntity;
import de.upb.hni.vmagic.VhdlElement;
import de.upb.hni.vmagic.declaration.DeclarativeItemMarker;
import de.upb.hni.vmagic.declaration.ObjectDeclaration;
import de.upb.hni.vmagic.literal.EnumerationLiteral;
import de.upb.hni.vmagic.literal.PhysicalLiteral;
import de.upb.hni.vmagic.object.VhdlObject;
import de.upb.hni.vmagic.object.VhdlObjectProvider;
import de.upb.hni.vmagic.type.EnumerationType;
import de.upb.hni.vmagic.type.PhysicalType;
import java.util.ArrayList;
import java.util.List;

/**
 * Vhdl collection utility class.
 */
public class VhdlCollections {

    private VhdlCollections() {
    }

    /**
     * Returns a list that contains all elements in another list which are
     * instances of the given class.
     * @param <T> the type of the elements that should be returned
     * @param <E> the type of the elements in the original list
     * @param list the original list
     * @param clazz the type of the elements that should be returned
     * @return a list
     */
    public static <T, E> List<T> getAll(List<E> list, Class<T> clazz) {
        List<T> result = new ArrayList<T>();

        for (E element : list) {
            if (clazz.isInstance(element)) {
                //it is safe to remove this warning because the cast is checked
                //by the surrounding if statement
                @SuppressWarnings("unchecked")
                T tmp = (T) element;

                result.add(tmp);
            }
        }

        return result;
    }

    /**
     * Returns an element by it's identifier.
     * @param <T> the searched type
     * @param <E> the list element type
     * @param list the list
     * @param clazz the searched type
     * @param identifier the identifier
     * @return the object or <code>null</code> if no matching element exists
     */
    public static <T extends NamedEntity, E> T getByIdentifier(
            List<E> list, Class<T> clazz, String identifier) {
        for (E element : list) {
            if (clazz.isInstance(element)) {
                //it is safe to remove this warning because the cast is checked
                //by the surrounding if statement
                @SuppressWarnings("unchecked")
                T tmp = (T) element;

                if (identifier.equalsIgnoreCase(tmp.getIdentifier())) {
                    return tmp;
                }
            }
        }

        return null;
    }

    /**
     * Creates a declaration list.
     * @param <E> the element type
     * @return the list
     */
    public static <E extends DeclarativeItemMarker> ResolvableList<E> createDeclarationList() {
        return new DeclarationList<E>();
    }

    /**
     * Creates a declaration list.
     * @param <E> the element type
     * @param list a list that is used to initialize the list
     * @return the list
     */
    public static <E extends DeclarativeItemMarker> ResolvableList<E> createDeclarationList(List<E> list) {
        return new DeclarationList<E>(list);
    }

    /**
     * Creates a list of VHDL objects.
     * @param <E> the element type
     * @return the list
     */
    public static <E extends VhdlObjectProvider<? extends VhdlObject>> ResolvableList<E> createVhdlObjectList() {
        return new VhdlObjectList<E>();
    }

    /**
     * Creates a list of VHDL objects.
     * @param <E> the element type
     * @param list a list that is used to initialize the list
     * @return the list
     */
    public static <E extends VhdlObjectProvider<? extends VhdlObject>> ResolvableList<E> createVhdlObjectList(List<E> list) {
        return new VhdlObjectList<E>(list);
    }

    /**
     * Creates a list of labeled elements.
     * @param <E> the element type
     * @param parent the parent
     * @return the list
     */
    public static <E extends LabeledElement> ResolvableList<E> createLabeledElementList(DeclarativeRegion parent) {
        return new LabeledElementList<E>(parent);
    }

    /**
     * Creates a list of labeled elements.
     * @param <E> the element type
     * @param parent the parent
     * @param list a list that is used to initialize the list
     * @return the list
     */
    public static <E extends LabeledElement> ResolvableList<E> createLabeledElementList(DeclarativeRegion parent, List<E> list) {
        return new LabeledElementList<E>(parent, list);
    }

    /**
     * Creates a list of named entities.
     * @param <E> the element type
     * @param parent the parent
     * @return the list
     */
    public static <E extends VhdlElement> ResolvableList<E> createNamedEntityList(DeclarativeRegion parent) {
        return new NamedEntityList<E>(parent);
    }

    /**
     * Creates a list of named entities.
     * @param <E> the element type
     * @param parent the parent
     * @param list a list that is used to initialize the list
     * @return the list
     */
    public static <E extends VhdlElement> ResolvableList<E> createNamedEntityList(DeclarativeRegion parent, List<E> list) {
        return new NamedEntityList<E>(parent, list);
    }

    /**
     * Declaration list.
     */
    //TODO: use map to resolve identifiers
    private static final class DeclarationList<E extends DeclarativeItemMarker>
            extends ForwardingList<E> implements ResolvableList<E> {

        private DeclarationList() {
            super(new ArrayList<E>());
        }

        private DeclarationList(List<E> list) {
            super(new ArrayList<E>(list));
        }

        @Override
        public Object resolve(String identifier) {
            for (E declaration : this) {
                if (declaration instanceof EnumerationType) {
                    EnumerationType type = (EnumerationType) declaration;
                    if (identifier.equalsIgnoreCase(type.getIdentifier())) {
                        return type;
                    }

                    //TODO: support overloading
                    for (EnumerationLiteral literal : type.getLiterals()) {
                        if (identifier.equalsIgnoreCase(literal.toString())) {
                            return literal;
                        }
                    }
                } else if (declaration instanceof PhysicalType) {
                    PhysicalType type = (PhysicalType) declaration;
                    if (identifier.equalsIgnoreCase(type.getIdentifier())) {
                        return type;
                    }

                    //TODO: don't use strings for the physical literals
                    if (identifier.equalsIgnoreCase(type.getPrimaryUnit())) {
                        return new PhysicalLiteral(type.getPrimaryUnit());
                    }

                    for (PhysicalType.Unit unit : type.getUnits()) {
                        if (identifier.equalsIgnoreCase(unit.getIdentifier())) {
                            return new PhysicalLiteral(unit.getIdentifier());
                        }
                    }
                } else if (declaration instanceof NamedEntity) {
                    NamedEntity identElement = (NamedEntity) declaration;
                    if (identifier.equalsIgnoreCase(identElement.getIdentifier())) {
                        return declaration;
                    }
                } else if (declaration instanceof ObjectDeclaration) {
                    ObjectDeclaration<?> objDecl = (ObjectDeclaration) declaration;
                    for (VhdlObject object : objDecl.getObjects()) {
                        if (object.getIdentifier().equalsIgnoreCase(identifier)) {
                            return object;
                        }
                    }
                }
            }

            return null;
        }
    }

    /**
     * VHDL object list.
     */
    private static final class VhdlObjectList<E extends VhdlObjectProvider<? extends VhdlObject>>
            extends ForwardingList<E> implements ResolvableList<E> {

        private VhdlObjectList() {
            super(new ArrayList<E>());
        }

        private VhdlObjectList(List<E> list) {
            super(new ArrayList<E>(list));
        }

        @Override
        public VhdlObject resolve(String identifier) {
            for (E provider : this) {
                for (VhdlObject object : provider.getVhdlObjects()) {
                    if (object.getIdentifier().equalsIgnoreCase(identifier)) {
                        return object;
                    }
                }
            }

            return null;
        }
    }

    /**
     * Labeled element list.
     */
    private static final class LabeledElementList<E extends LabeledElement>
            extends ForwardingList<E> implements ResolvableList<E> {

        private LabeledElementList(DeclarativeRegion parent) {
            super(ParentSetList.<E>create(parent));
        }

        private LabeledElementList(DeclarativeRegion parent, List<E> list) {
            super(ParentSetList.<E>create(parent, list));
        }

        @Override
        public E resolve(String identifier) {
            for (E element : this) {
                if (identifier.equalsIgnoreCase(element.getLabel())) {
                    return element;
                }
            }
            return null;
        }
    }

    /**
     * Named entity list.
     */
    private static final class NamedEntityList<E extends VhdlElement>
            extends ForwardingList<E> implements ResolvableList<E> {

        private NamedEntityList(DeclarativeRegion parent) {
            super(ParentSetList.<E>create(parent));
        }

        private NamedEntityList(DeclarativeRegion parent, List<E> list) {
            super(ParentSetList.<E>create(parent, list));
        }

        @Override
        public E resolve(String identifier) {
            for (E element : this) {
                if (element instanceof NamedEntity) {
                    NamedEntity namedEntity = (NamedEntity) element;
                    if (namedEntity.getIdentifier().equalsIgnoreCase(identifier)) {
                        return element;
                    }
                }
            }
            return null;
        }
    }
}

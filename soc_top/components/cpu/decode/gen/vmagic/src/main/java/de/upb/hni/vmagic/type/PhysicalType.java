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

package de.upb.hni.vmagic.type;

import de.upb.hni.vmagic.NamedEntity;
import de.upb.hni.vmagic.RangeProvider;
import de.upb.hni.vmagic.literal.AbstractLiteral;
import de.upb.hni.vmagic.literal.DecimalLiteral;
import java.util.ArrayList;
import java.util.List;

/**
 * Physical type.
 */
public class PhysicalType extends Type {

    private RangeProvider range;
    private String primaryUnit;
    private final List<Unit> units = new ArrayList<Unit>();

    /**
     * Creates a physical type.
     * @param identifier the identifier of this type
     * @param range the range
     * @param primaryUnit the primary unit identifier
     */
    public PhysicalType(String identifier, RangeProvider range, String primaryUnit) {
        super(identifier);
        this.range = range;
        this.primaryUnit = primaryUnit;
    }

    /**
     * Returns the identifier of the primary unit.
     * @return the identifier of the primary unit
     */
    public String getPrimaryUnit() {
        return primaryUnit;
    }

    /**
     * Sets the identifier of the primary unit.
     * @param primaryUnit the identifier of the primary unit
     */
    public void setPrimaryUnit(String primaryUnit) {
        this.primaryUnit = primaryUnit;
    }

    /**
     * Returns the range of this physical type.
     * @return the range
     */
    public RangeProvider getRange() {
        return range;
    }

    /**
     * Sets the range of this physical type.
     * @param range the range
     */
    public void setRange(RangeProvider range) {
        this.range = range;
    }

    /**
     * Returns the list of units.
     * @return a modifiable list of units
     */
    public List<Unit> getUnits() {
        return units;
    }

    /**
     * Creates a unit and adds it to this physical type.
     * @param identifier the unit's identifier
     * @param factor the factor
     * @param baseUnit the base unit
     * @return the created unit
     */
    public Unit createUnit(String identifier, AbstractLiteral factor, String baseUnit) {
        Unit unit = new Unit(identifier, factor, baseUnit);
        units.add(unit);

        return unit;
    }

    /**
     * Creates a unit with a integer factor and adds it to this physical type.
     * @param identifier the unit's identifier
     * @param factor the factor
     * @param baseUnit the base unit
     * @return the created unit
     */
    public Unit createUnit(String identifier, int factor, String baseUnit) {
        Unit unit = new Unit(identifier, new DecimalLiteral(factor), baseUnit);
        units.add(unit);

        return unit;
    }

    /**
     * Creates a unit without a factor and adds it to this physical type.
     * Units without a factor implicitly use 1 as a factor.
     * @param identifier the unit's identifier
     * @param baseUnit the base unit
     * @return the created unit
     */
    public Unit createUnit(String identifier, String baseUnit) {
        Unit unit = new Unit(identifier, null, baseUnit);
        units.add(unit);

        return unit;
    }

    @Override
    void accept(TypeVisitor visitor) {
        visitor.visitPhysicalType(this);
    }

    /**
     * A unit in a physical type.
     */
    public static class Unit implements NamedEntity {

        private String identifier;
        private AbstractLiteral factor;
        private String baseUnit;

        private Unit(String identifier, AbstractLiteral factor, String baseUnit) {
            this.identifier = identifier;
            this.factor = factor;
            this.baseUnit = baseUnit;
        }

        /**
         * Returns the base unit of this unit.
         * @return the base unit
         */
        public String getBaseUnit() {
            return baseUnit;
        }

        /**
         * Sets the base unit of this unit.
         * @param baseUnit the base unit
         */
        public void setBaseUnit(String baseUnit) {
            this.baseUnit = baseUnit;
        }

        /**
         * Returns the factor.
         * @return the factor
         */
        public AbstractLiteral getFactor() {
            return factor;
        }

        /**
         * Sets the factor.
         * @param factor the factor
         */
        public void setFactor(AbstractLiteral factor) {
            this.factor = factor;
        }

        /**
         * Returns the identifier of this unit.
         * @return the identifier
         */
        public String getIdentifier() {
            return identifier;
        }

        /**
         * Sets the identifier of this unit.
         * @param identifier the identifier
         */
        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }
    }
}

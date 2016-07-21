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

package de.upb.hni.vmagic.concurrent;

import de.upb.hni.vmagic.libraryunit.Configuration;

/**
 * Configuration instantiation.
 */
public class ConfigurationInstantiation extends AbstractComponentInstantiation {

    private Configuration configuration;

    /**
     * Creates a configuration instantiation.
     * @param label the label
     * @param configuration the instantiated configuration
     */
    public ConfigurationInstantiation(String label, Configuration configuration) {
        super(label);
        this.configuration = configuration;
    }

    /**
     * Returns the instantiated configuration.
     * @return the instantiated configuration
     */
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Sets the instantiated configuration
     * @param configuration the instantiated configuration
     */
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    void accept(ConcurrentStatementVisitor visitor) {
        visitor.visitConfigurationInstantiation(this);
    }
}

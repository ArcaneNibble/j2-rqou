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

package de.upb.hni.vmagic.configuration;

import de.upb.hni.vmagic.libraryunit.UseClause;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for block configurations.
 */
public abstract class AbstractBlockConfiguration extends ConfigurationItem {

    private final List<UseClause> useClauses = new ArrayList<UseClause>();
    private final List<ConfigurationItem> configurationItems = new ArrayList<ConfigurationItem>();

    /**
     * Creates an empty block configuration.
     */
    public AbstractBlockConfiguration() {
    }

    /**
     * Returns the use clauses in this block configuration.
     * @return a modifiable list of use clauses
     */
    public List<UseClause> getUseClauses() {
        return useClauses;
    }

    /**
     * Returns the configuration items in this block configuration.
     * @return a modifiable list of configuration items
     */
    public List<ConfigurationItem> getConfigurationItems() {
        return configurationItems;
    }
}

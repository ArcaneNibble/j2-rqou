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

import java.util.List;

/**
 * Configuration visitor.
 */
public class ConfigurationVisitor {

    /**
     * Visits a configuration item.
     * No visit method is called if the parameter equals <code>null</code>.
     * @param item the configuration item or <code>null</code>
     */
    public void visit(ConfigurationItem item) {
        if (item != null) {
            item.accept(this);
        }
    }

    /**
     * Visits a list of configuration items.
     * <code>null</code> items in the list are ignored.
     * The list parameter must not be <code>null</code>.
     * @param items the configuration items
     */
    public void visit(List<? extends ConfigurationItem> items) {
        for (ConfigurationItem item : items) {
            if (item != null) {
                item.accept(this);
            }
        }
    }

    /**
     * Visits an architecture configuration.
     * @param configuration the architecture configuration
     */
    protected void visitArchitectureConfiguration(ArchitectureConfiguration configuration) {
    }

    /**
     * Visits a block statement configuration.
     * @param configuration the block statement configuration
     */
    protected void visitBlockStatementConfiguration(BlockStatementConfiguration configuration) {
    }

    /**
     * Visits a component configuration.
     * @param configuration the component configuration
     */
    protected void visitComponentConfiguration(ComponentConfiguration configuration) {
    }

    /**
     * Visits a generate statement configuration.
     * @param configuration the generate statement configuration
     */
    protected void visitGenerateStatementConfiguration(GenerateStatementConfiguration configuration) {
    }
}

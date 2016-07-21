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

import de.upb.hni.vmagic.concurrent.AbstractGenerateStatement;

/**
 * Generate statement configuration.
 */
//TODO: support index specification
public class GenerateStatementConfiguration extends AbstractBlockConfiguration {

    private AbstractGenerateStatement generateStatement;

    /**
     * Creates a generate statement configuration.
     * @param generateStatement the configured generate statement
     */
    public GenerateStatementConfiguration(AbstractGenerateStatement generateStatement) {
        this.generateStatement = generateStatement;
    }

    /**
     * Returns the configured generate statement.
     * @return the configured generate statement
     */
    public AbstractGenerateStatement getGenerateStatement() {
        return generateStatement;
    }

    /**
     * Sets the configured generate statement.
     * @param generateStatement the configured generate statement
     */
    public void setGenerateStatement(AbstractGenerateStatement generateStatement) {
        this.generateStatement = generateStatement;
    }

    @Override
    void accept(ConfigurationVisitor visitor) {
        visitor.visitGenerateStatementConfiguration(this);
    }
}

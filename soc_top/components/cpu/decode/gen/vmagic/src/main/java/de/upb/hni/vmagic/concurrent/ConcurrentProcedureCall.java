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

import de.upb.hni.vmagic.AssociationElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Concurrent procedure call statement.
 */
//TODO: don't use string for procedure name
public class ConcurrentProcedureCall extends EntityStatement {

    private String procedure;
    private final List<AssociationElement> parameters;

    /**
     * Creates a procedure call.
     * @param procedure the called procedure
     */
    public ConcurrentProcedureCall(String procedure) {
        this.procedure = procedure;
        this.parameters = new ArrayList<AssociationElement>();
    }

    /**
     * Creates a procedure call with a list of parameters.
     * @param procedure the called procedure
     * @param parameters the call parameters
     */
    public ConcurrentProcedureCall(String procedure, List<AssociationElement> parameters) {
        this.procedure = procedure;
        this.parameters = new ArrayList<AssociationElement>(parameters);
    }

    /**
     * Creates a procedure call with parameters.
     * @param procedure the called procedure
     * @param parameters the call parameters
     */
    public ConcurrentProcedureCall(String procedure, AssociationElement... parameters) {
        this(procedure, Arrays.asList(parameters));
    }

    /**
     * Returns the parameters.
     * @return a modifiable list of association elements
     */
    public List<AssociationElement> getParameters() {
        return parameters;
    }

    /**
     * Returns the called procedure.
     * @return the procedure
     */
    public String getProcedure() {
        return procedure;
    }

    /**
     * Sets the called procedure.
     * @param procedure the procedure
     */
    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    @Override
    void accept(ConcurrentStatementVisitor visitor) {
        visitor.visitConcurrentProcedureCall(this);
    }
}

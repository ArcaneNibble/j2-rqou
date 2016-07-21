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

import de.upb.hni.vmagic.expression.Expression;

/**
 * Signal assignment delay mechanism.
 */
public abstract class DelayMechanism extends VhdlElement {

    /**
     * Transport delay mechanism.
     */
    public static final DelayMechanism TRANSPORT = new DelayMechanism() {};
    
    /**
     * Inertial delay mechanism.
     */
    public static final DelayMechanism INERTIAL = new DelayMechanism() {};

    /**
     * Creates a reject inertial delay mechanism.
     * @param time the pulse rejection limit
     * @return the created delay mechanism.
     */
    public static DelayMechanism REJECT_INERTIAL(Expression time) {
        return new RejectInertialImpl(time);
    }

    /**
     * Returns the pulse rejection limit.
     * @return the pulse rejection limit or 
     *         <code>null</code> for transport and inertial delay mechanism
     */
    public Expression getPulseRejectionLimit() {
        return null;
    }

    /**
     * Prevent subclassing.
     */
    private DelayMechanism() {
    }

    private static class RejectInertialImpl extends DelayMechanism {

        private final Expression time;

        /**
         * Creates a reject inertial delay mechanism.
         * @param time the puls rejection limit
         */
        public RejectInertialImpl(Expression time) {
            this.time = time;
        }

        @Override
        public Expression getPulseRejectionLimit() {
            return time;
        }
    }
}

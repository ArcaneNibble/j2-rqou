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

import de.upb.hni.vmagic.VhdlElement;
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.object.Signal;
import de.upb.hni.vmagic.type.SubtypeIndication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Disconnection specification.
 */
public class DisconnectionSpecification extends DeclarativeItem
        implements BlockDeclarativeItem, EntityDeclarativeItem, PackageDeclarativeItem {

    private SignalList signals;
    //TODO: use type_mark
    private SubtypeIndication type;
    private Expression after;

    /**
     * Creates a disconnection specification.
     * @param signals a list of guarded signals
     * @param type the type of the signals
     * @param after the disconnection delay
     */
    public DisconnectionSpecification(SignalList signals, SubtypeIndication type, Expression after) {
        this.signals = signals;
        this.type = type;
        this.after = after;
    }

    /**
     * Returns the disconnection delay.
     * @return the delay
     */
    public Expression getAfter() {
        return after;
    }

    /**
     * Sets the disconnection delay.
     * @param after the delay
     */
    public void setAfter(Expression after) {
        this.after = after;
    }

    /**
     * Returns the list of signals.
     * @return a list of signals
     */
    public SignalList getSignals() {
        return signals;
    }

    /**
     * Sets the list of signals.
     * @param signals a list of signals
     */
    public void setSignals(SignalList signals) {
        this.signals = signals;
    }

    /**
     * Returns the type of the signals.
     * @return the type
     */
    public SubtypeIndication getType() {
        return type;
    }

    /**
     * Sets the type of the signals.
     * @param type the type
     */
    public void setType(SubtypeIndication type) {
        this.type = type;
    }

    @Override
    void accept(DeclarationVisitor visitor) {
        visitor.visitDisconnectionSpecification(this);
    }

    /**
     * Signal list for disconnection specification.
     *
     */
    public static class SignalList extends VhdlElement {

        private final List<Signal> signals;
        /**
         * ALL.
         */
        public static final SignalList ALL = new SignalList(true) {
        };
        /**
         * OTHERS.
         */
        public static final SignalList OTHERS = new SignalList(true) {
        };

        /**
         * Creates a signal list.
         * @param signals a list of signals
         */
        public SignalList(List<Signal> signals) {
            this.signals = new ArrayList<Signal>(signals);
        }

        /**
         * Creates a signal list.
         * @param signals a list of signals
         */
        public SignalList(Signal... signals) {
            this(Arrays.asList(signals));
        }

        /**
         * Creates a signal list.
         * @param signals a list of signals
         */
        private SignalList(boolean nullList) {
            if (nullList) {
                signals = null;
            } else {
                signals = new ArrayList<Signal>();
            }
        }

        /**
         * Returns the list of signals in this signal list.
         * This method returns <code>null</code> if this type signal list containt no signals.
         * @return the list of signals or <code>null</code>
         */
        public List<Signal> getSignals() {
            return signals;
        }
    }
}

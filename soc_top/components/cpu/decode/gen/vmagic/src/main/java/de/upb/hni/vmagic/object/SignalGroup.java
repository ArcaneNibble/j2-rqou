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

package de.upb.hni.vmagic.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Group of signals.
 */
//TODO: check for equal types
public class SignalGroup extends VhdlObjectGroup<Signal> {

    private final List<Signal> signals;

    /**
     * Creates a group of signals.
     * @param signals a list of signals
     */
    public SignalGroup(List<Signal> signals) {
        this.signals = new ArrayList<Signal>(signals);
    }

    /**
     * Creates a group of signals.
     * @param signals a variable number of signals
     */
    public SignalGroup(Signal... signals) {
        this(Arrays.asList(signals));
    }

    /**
     * Returns the signals in this group.
     * @return a modifiable list of signals
     */
    @Override
    public List<Signal> getElements() {
        return signals;
    }

    @Override
    public List<Signal> getVhdlObjects() {
        return Collections.unmodifiableList(signals);
    }
}

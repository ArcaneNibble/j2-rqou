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
 * Waveform element.
 */
public class WaveformElement {

    private Expression value;
    private Expression after;

    /**
     * Creates a waveform element.
     * @param value the value
     */
    public WaveformElement(Expression value) {
        this.value = value;
    }

    /**
     * Creates a waveform element with a delay.
     * @param value the value
     * @param after the delay
     */
    public WaveformElement(Expression value, Expression after) {
        this.value = value;
        this.after = after;
    }

    /**
     * Returns the delay of this waveform element.
     * @return the delay
     */
    public Expression getAfter() {
        return after;
    }

    /**
     * Sets the delay of this waveform element.
     * @param after the delay
     */
    public void setAfter(Expression after) {
        this.after = after;
    }

    /**
     * Returns the value of this waveform element.
     * @return the value
     */
    public Expression getValue() {
        return value;
    }

    /**
     * Sets the value of this waveform element.
     * @param value the value
     */
    public void setValue(Expression value) {
        this.value = value;
    }
}

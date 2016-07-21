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

package de.upb.hni.vmagic.statement;

import de.upb.hni.vmagic.DelayMechanism;
import de.upb.hni.vmagic.WaveformElement;
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.object.SignalAssignmentTarget;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Signal assignment.
 *
 * @vmagic.example
 * Signal target = new Signal("TARGET", Standard.BIT);
 * SignalAssignment assignment = new SignalAssignment(target, Standard.BIT_0);
 * ---
 * TARGET <= '0';
 */
public class SignalAssignment extends SequentialStatement {

    private SignalAssignmentTarget target;
    private final List<WaveformElement> waveform = new ArrayList<WaveformElement>();
    private DelayMechanism delayMechanism;

    /**
     * Creates a signal assignment.
     * @param target the signal assignement target
     * @param waveformElements the waveform
     */
    public SignalAssignment(SignalAssignmentTarget target, WaveformElement... waveformElements) {
        this(target, Arrays.asList(waveformElements));
    }

    /**
     * Creates a signal assignment.
     * @param target the signal assignement target
     * @param waveformElements the waveform
     */
    public SignalAssignment(SignalAssignmentTarget target, List<WaveformElement> waveformElements) {
        this.target = target;
        this.waveform.addAll(waveformElements);
    }

    /**
     * Creates a signal assignement.
     * @param target the signal assignment target
     * @param value the assigned value
     */
    public SignalAssignment(SignalAssignmentTarget target, Expression value) {
        this.target = target;
        this.waveform.add(new WaveformElement(value));
    }

    /**
     * Returns the signal assignment target.
     * @return the target
     */
    public SignalAssignmentTarget getTarget() {
        return target;
    }

    /**
     * Sets the signal assignement target.
     * @param target the target
     */
    public void setTarget(SignalAssignmentTarget target) {
        this.target = target;
    }

    /**
     * Returns the delay mechanism.
     * @return the delay mechanism or <code>null</code> if no delay mechanism is set
     */
    public DelayMechanism getDelayMechanism() {
        return delayMechanism;
    }

    /**
     * Sets the delay mechanism.
     * @param delayMechanism the delay mechanism or <code>null</code> to remove the delay mechanism
     */
    public void setDelayMechanism(DelayMechanism delayMechanism) {
        this.delayMechanism = delayMechanism;
    }

    /**
     * Retutns the waveform.
     * @return a modifiable list of waveform elements
     */
    public List<WaveformElement> getWaveform() {
        return waveform;
    }

    @Override
    void accept(SequentialStatementVisitor visitor) {
        visitor.visitSignalAssignment(this);
    }
}

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

package de.upb.hni.vmagic.concurrent;

import de.upb.hni.vmagic.DelayMechanism;
import de.upb.hni.vmagic.WaveformElement;
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.object.SignalAssignmentTarget;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Conditional signal assignment.
 */
public class ConditionalSignalAssignment extends AbstractPostponableConcurrentStatement {

    private SignalAssignmentTarget target;
    private final List<ConditionalWaveformElement> conditionalWaveforms;
    private DelayMechanism delayMechanism;
    boolean guarded;

    /**
     * Creates a conditional signal assignment.
     * @param target the target of this signal assignment
     * @param conditionalWaveforms the assigned waveform
     */
    public ConditionalSignalAssignment(SignalAssignmentTarget target,
            ConditionalWaveformElement... conditionalWaveforms) {
        this(target, Arrays.asList(conditionalWaveforms));
    }

    /**
     * Creates a conditional signal assignment.
     * @param target the target of this signal assignment
     * @param conditionalWaveforms the assigned waveform
     */
    public ConditionalSignalAssignment(SignalAssignmentTarget target,
            List<ConditionalWaveformElement> conditionalWaveforms) {
        this.target = target;
        this.conditionalWaveforms = new ArrayList<ConditionalWaveformElement>(conditionalWaveforms);
    }

    /**
     * Creates a conditional signal assignment.
     * @param target the target of this signal assignment
     * @param value the assigned value
     */
    public ConditionalSignalAssignment(SignalAssignmentTarget target, Expression value) {
        this.target = target;
        this.conditionalWaveforms = new ArrayList<ConditionalWaveformElement>();

        WaveformElement element = new WaveformElement(value);
        this.conditionalWaveforms.add(new ConditionalWaveformElement(Arrays.asList(element)));
    }

    /**
     * Returns the target of this conditional signal assignment.
     * @return the target
     */
    public SignalAssignmentTarget getTarget() {
        return target;
    }

    /**
     * Sets the target of this conditional signal assignment.
     * @param target the target
     */
    public void setTarget(SignalAssignmentTarget target) {
        this.target = target;
    }

    /**
     * Returns the conditional waveforms.
     * @return a modifiable list of conditional waveform elements
     */
    public List<ConditionalWaveformElement> getConditionalWaveforms() {
        return conditionalWaveforms;
    }

    /**
     * Returns the delay mechanism.
     * @return the delay mechanism or <code>null</code> if no delay mechanism is set
     */
    public DelayMechanism getDelayMechanism() {
        return delayMechanism;
    }

    /**
     * Sets the delay mechanism
     * @param delayMechanism the delay mechanism or <code>null</code> to remove the delay mechanism
     */
    public void setDelayMechanism(DelayMechanism delayMechanism) {
        this.delayMechanism = delayMechanism;
    }

    /**
     * Returns if this conditional signal assignement is guarded.
     * @return <code>true</code>, if the assignment is guarded
     */
    public boolean isGuarded() {
        return guarded;
    }

    /**
     * Sets if this conditional signal assignment is guarded.
     * @param guarded <code>true</code>, if this assignment should be guarded
     */
    public void setGuarded(boolean guarded) {
        this.guarded = guarded;
    }

    @Override
    void accept(ConcurrentStatementVisitor visitor) {
        visitor.visitConditionalSignalAssignment(this);
    }

    /**
     * Conditional waveform element.
     */
    public static class ConditionalWaveformElement {

        private final List<WaveformElement> waveform;
        private Expression condition;

        /**
         * Creates a conditional waveform element.
         * @param waveform the waveform
         */
        public ConditionalWaveformElement(List<WaveformElement> waveform) {
            this.waveform = new ArrayList<WaveformElement>(waveform);
        }

        /**
         * Creates a conditional waveform element with a condition.
         * @param waveform the waveform
         * @param condition the condition
         */
        public ConditionalWaveformElement(List<WaveformElement> waveform, Expression condition) {
            this.waveform = new ArrayList<WaveformElement>(waveform);
            this.condition = condition;
        }

        /**
         * Returns the condition of this conditional waveform element.
         * @return the condition
         */
        public Expression getCondition() {
            return condition;
        }

        /**
         * Sets the condition of this conditional waveform element.
         * @param condition the condition
         */
        public void setCondition(Expression condition) {
            this.condition = condition;
        }

        /**
         * Returns the list of waveform elements in this conditional waveform element.
         * @return the list of waveform elements
         */
        public List<WaveformElement> getWaveform() {
            return waveform;
        }
    }
}

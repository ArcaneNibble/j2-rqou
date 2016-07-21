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

import de.upb.hni.vmagic.Choice;
import de.upb.hni.vmagic.DelayMechanism;
import de.upb.hni.vmagic.WaveformElement;
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.object.SignalAssignmentTarget;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Selected signal assignment.
 */
public class SelectedSignalAssignment extends AbstractPostponableConcurrentStatement {

    private Expression expression;
    private SignalAssignmentTarget target;
    private boolean guarded;
    private DelayMechanism delayMechanism;
    private final List<SelectedWaveform> selectedWaveforms = new ArrayList<SelectedWaveform>();

    /**
     * Creates a selected signal assignment.
     * @param expression the assigned expression
     * @param target the assignment target
     */
    public SelectedSignalAssignment(Expression expression, SignalAssignmentTarget target) {
        this.expression = expression;
        this.target = target;
    }

    /**
     * Returns the assigned expression.
     * @return the assigned expression
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Sets the assigned expression.
     * @param expression the assigned epxression
     */
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Returns the target of this selected signal assignment.
     * @return the target
     */
    public SignalAssignmentTarget getTarget() {
        return target;
    }

    /**
     * Sets the target of this selected signal assignment.
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
     * Sets the delay mechanism
     * @param delayMechanism the delay mechanism or <code>null</code> to remove the delay mechanism
     */
    public void setDelayMechanism(DelayMechanism delayMechanism) {
        this.delayMechanism = delayMechanism;
    }

    /**
     * Returns if this selected signal assignement is guarded.
     * @return <code>true</code>, if this assignment is guarded
     */
    public boolean isGuarded() {
        return guarded;
    }

    /**
     * Sets if this selected signal assignment is guarded.
     * @param guarded <code>true</code>, if this assignment should be guarded
     */
    public void setGuarded(boolean guarded) {
        this.guarded = guarded;
    }

    /**
     * Returns the selected waveforms.
     * @return a modifiable list of selected waveforms
     */
    public List<SelectedWaveform> getSelectedWaveforms() {
        return selectedWaveforms;
    }

    @Override
    void accept(ConcurrentStatementVisitor visitor) {
        visitor.visitSelectedSignalAssignment(this);
    }

    /**
     * Selected waveform.
     */
    public static class SelectedWaveform {

        private final List<WaveformElement> waveform;
        private final List<Choice> choices;

        /**
         * Creates a selected waveform.
         * @param waveform the waveform
         * @param choices the choices
         */
        public SelectedWaveform(Expression waveform, Choice... choices) {
            this(waveform, Arrays.asList(choices));
        }

        /**
         * Creates a selected waveform with a list of choices.
         * @param waveform the waveform
         * @param choices a list of choices
         */
        public SelectedWaveform(Expression waveform, List<Choice> choices) {
            this(Collections.singletonList(new WaveformElement(waveform)), choices);
        }

        /**
         * Creates a selected waveform with a list of waveform element and choices.
         * @param waveform a list of waveform elements
         * @param choices a list of choices
         */
        public SelectedWaveform(List<WaveformElement> waveform, List<Choice> choices) {
            this.waveform = new ArrayList<WaveformElement>(waveform);
            this.choices = new ArrayList<Choice>(choices);
        }

        /**
         * Returns the waveform.
         * @return a modifiable list of waveform elements
         */
        public List<WaveformElement> getWaveform() {
            return waveform;
        }

        /**
         * Returns the choices.
         * @return a modifiable list of choices
         */
        public List<Choice> getChoices() {
            return choices;
        }
    }
}

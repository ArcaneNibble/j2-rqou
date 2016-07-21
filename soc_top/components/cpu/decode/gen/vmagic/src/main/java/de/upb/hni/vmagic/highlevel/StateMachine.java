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

package de.upb.hni.vmagic.highlevel;

import de.upb.hni.vmagic.Choice;
import de.upb.hni.vmagic.Choices;
import de.upb.hni.vmagic.WaveformElement;
import de.upb.hni.vmagic.concurrent.AbstractProcessStatement;
import de.upb.hni.vmagic.object.Signal;
import de.upb.hni.vmagic.concurrent.ConcurrentStatement;
import de.upb.hni.vmagic.declaration.BlockDeclarativeItem;
import de.upb.hni.vmagic.declaration.ProcessDeclarativeItem;
import de.upb.hni.vmagic.declaration.SignalDeclaration;
import de.upb.hni.vmagic.literal.EnumerationLiteral;
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.statement.IfStatement;
import de.upb.hni.vmagic.statement.SequentialStatement;
import de.upb.hni.vmagic.statement.SignalAssignment;
import de.upb.hni.vmagic.type.EnumerationType;
import de.upb.hni.vmagic.statement.CaseStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * State machine.
 */
public class StateMachine {

    private String stateSignalIdentifier;
    private final List<State> states = new ArrayList<State>();
    private State startState;
    private final List<SequentialStatement> combinatorialStatements =
            new ArrayList<SequentialStatement>();
    private final StateMachineProcess process;
    private final Register register;
    private List<BlockDeclarativeItem> declarations;
    private final List<ConcurrentStatement> statements;
    private final EnumerationType enumerationType;
    private Signal nextStateSignal;
    private Signal currentStateSignal;
    private Signal clock;
    private Signal reset;
    //FIXME: make configurable
    private static final String NEXT_PREFIX = "NEXT_";
    private static final String CURRENT_PREFIX = "CURRENT_";
    private static final String TYPE_SUFFIX = "_TYPE";

    /**
     * Creates a state machine.
     * @param identifier the identifier
     * @param stateSignalIdentifier the identifier of the state signals
     * @param clock the clock signal
     * @param reset the reset signal
     */
    public StateMachine(String identifier, String stateSignalIdentifier, Signal clock, Signal reset) {
        this.clock = clock;
        this.reset = reset;

        process = new StateMachineProcess();
        process.setLabel(identifier);

        enumerationType = new EnumerationType(stateSignalIdentifier + TYPE_SUFFIX);

        setStateSignalIdentifierHelper(stateSignalIdentifier);
        register = new StateMachineRegister();
        statements = Arrays.<ConcurrentStatement>asList(process, register);
    }

    private void setStateSignalIdentifierHelper(String stateSignalIdentifier) {
        this.stateSignalIdentifier = stateSignalIdentifier;
        nextStateSignal = new Signal(NEXT_PREFIX + stateSignalIdentifier,
                enumerationType);
        currentStateSignal = new Signal(CURRENT_PREFIX + stateSignalIdentifier,
                enumerationType);

        process.updateSignals();

        declarations = Arrays.<BlockDeclarativeItem>asList(
                enumerationType,
                new SignalDeclaration(nextStateSignal, currentStateSignal));

        for (State state : states) {
            if (state instanceof IdentifierStateImpl) {
                ((IdentifierStateImpl) state).updateStateChange();
            }
        }
    }

    /**
     * Returns the identifier.
     * @return the identifier
     */
    public String getIdentifier() {
        return process.getLabel();
    }

    /**
     * Sets the identifier.
     * @param identifier the identifier
     */
    public void setIdentifier(String identifier) {
        process.setLabel(identifier);
    }

    /**
     * Returns the identifier of the state signals.
     * @return the identifier
     */
    public String getStateSignalIdentifier() {
        return stateSignalIdentifier;
    }

    /**
     * Sets the identifier of the state signals.
     * @param stateSignalIdentifier the identifier
     */
    public void setStateSignalIdentifier(String stateSignalIdentifier) {
        setStateSignalIdentifierHelper(stateSignalIdentifier);
    }

    /**
     * Returns a list of states.
     * @return a modifiable list of states
     */
    public List<State> getStates() {
        return states;
    }

    /**
     * Returns the sensitivity list.
     * @return the sensitivity list
     */
    public List<Signal> getSensitivityList() {
        return process.getSensitivityList();
    }

    /**
     * Returns the combinatorial statement.
     * @return a list of combinatorial statemetns
     */
    public List<SequentialStatement> getCombinatorialStatements() {
        return combinatorialStatements;
    }

    /**
     * Returns the start state of this state machine.
     * @return the start state
     */
    public State getStartState() {
        if (startState == null && !states.isEmpty()) {
            setStartState(states.get(0));
        }

        return startState;
    }

    /**
     * Sets the start state.
     * @param startState the start state
     */
    public void setStartState(State startState) {
        this.startState = startState;
    }

    /**
     * Returns the signal that contains the current state.
     * @return the current state signal
     */
    public Signal getCurrentStateSignal() {
        return currentStateSignal;
    }

    /**
     * Returns the signal that contains the next state.
     * @return the next state signal
     */
    public Signal getNextStateSignal() {
        return nextStateSignal;
    }

    /**
     * Creates a new state and adds it to this state machine.
     * @param identifier the states identifier
     * @return the created state
     */
    public State createState(String identifier) {
        State state = new IdentifierStateImpl(identifier);
        states.add(state);
        return state;
    }

    /**
     * Creates a new others state and adds it to this state machine.
     * @return the created state
     */
    public State createOthersState() {
        State state = new OthersStateImpl();
        states.add(state);
        return state;
    }

    /**
     * Returns the declaration.
     * @return a list of declarations
     */
    public List<BlockDeclarativeItem> getDeclarations() {
        return declarations;
    }

    /**
     * Returns the statements.
     * @return a list of statements
     */
    public List<ConcurrentStatement> getStatements() {
        return statements;
    }

    /**
     * Returns the statements before the case statement.
     * @return a modifiable list of sequential statements
     */
    public List<SequentialStatement> getStatementsBefore() {
        return process.getStatementsBefore();
    }

    /**
     * Returns the statements after the case statement.
     * @return a modifiable list of sequential statements
     */
    public List<SequentialStatement> getStatementsAfter() {
        return process.getStatementsAfter();
    }

    /**
     * Returns the clock signal.
     * @return the clock signal
     */
    public Signal getClock() {
        return clock;
    }

    /**
     * Sets the clock signal.
     * @param clock the clock signal
     */
    public void setClock(Signal clock) {
        this.clock = clock;
    }

    /**
     * Returns the reset signal.
     * @return the reset signal
     */
    public Signal getReset() {
        return reset;
    }

    /**
     * Sets the reset signal.
     * @param reset the reset signal
     */
    public void setReset(Signal reset) {
        this.reset = reset;
    }

    /**
     * State in a state machine.
     */
    public interface State {

        /**
         * Returns the identifier of this state.
         * @return the identifier
         */
        public String getIdentifier();

        /**
         * Sets the identifier of this state.
         * @param identifier the identifeir
         */
        public void setIdentifier(String identifier);

        /**
         * Returns the enumeration literal associated with this state.
         * @return the enumeration literal
         */
        public EnumerationLiteral getLiteral();

        /**
         * Returns the choice.
         * @return the choice
         */
        public Choice getChoice();

        /**
         * Returns the statements in this state.
         * @return a modifiable list of squential statements
         */
        public List<SequentialStatement> getStatements();

        /**
         * Creates an unconditional state change.
         * @return the state change
         */
        //TODO: change return type to signal assignment
        public SequentialStatement createStateChange();

        /**
         * Creates a conditional state change.
         * @param condition the condition
         * @return the state change
         */
        //TODO: change return type to if statement -> allowes user to add else part
        public SequentialStatement createStateChange(Expression condition);
    }

    private abstract class AbstractStateImpl implements State {

        private final List<SequentialStatement> statements =
                new ArrayList<SequentialStatement>();

        public List<SequentialStatement> getStatements() {
            return statements;
        }
    }

    private final class IdentifierStateImpl extends AbstractStateImpl {

        private EnumerationLiteral literal;
        private SignalAssignment stateChange;

        public IdentifierStateImpl(String identifier) {
            setIdentifier(identifier);
        }

        public String getIdentifier() {
            return getLiteral().toString();
        }

        public void setIdentifier(String identifier) {
            enumerationType.getLiterals().remove(literal);
            literal = enumerationType.createLiteral(identifier);
            if (stateChange == null) {
                stateChange = new SignalAssignment(getNextStateSignal(), getLiteral());
            } else {
                updateStateChange();
            }
        }

        protected void setLiteral(EnumerationLiteral literal) {
        }

        public EnumerationLiteral getLiteral() {
            return literal;
        }

        public SequentialStatement createStateChange() {
            return stateChange;
        }

        public IfStatement createStateChange(Expression condition) {
            IfStatement statement = new IfStatement(condition);
            statement.getStatements().add(stateChange);

            return statement;
        }

        public Choice getChoice() {
            return literal;
        }

        void updateStateChange() {
            stateChange.setTarget(getNextStateSignal());

            //TODO: simplify
            stateChange.getWaveform().clear();
            stateChange.getWaveform().add(new WaveformElement(getLiteral()));
        }
    }

    private class OthersStateImpl extends AbstractStateImpl {

        public OthersStateImpl() {
        }

        public String getIdentifier() {
            return "others";
        }

        public void setIdentifier(String identifier) {
            throw new UnsupportedOperationException();
        }

        public SequentialStatement createStateChange() {
            throw new UnsupportedOperationException();
        }

        public SequentialStatement createStateChange(Expression condition) {
            throw new UnsupportedOperationException();
        }

        public EnumerationLiteral getLiteral() {
            throw new UnsupportedOperationException();
        }

        public Choice getChoice() {
            return Choices.OTHERS;
        }
    }

    private class StateMachineRegister extends Register {

        public StateMachineRegister() {
            super(nextStateSignal, currentStateSignal, clock, reset);
            setResetType(ResetType.ASYNCHRONOUS);
            setResetLevel(ResetLevel.LOW);
        }

//TODO: find other method to set reset expression before writing
//        public InternalVhdlWriter writeVhdl(InternalVhdlWriter writer) throws IOException {
//            setResetExpression(getStartState().getLiteral());
//            return super.writeVhdl(writer);
//        }
    }

    private class StateMachineProcess extends AbstractProcessStatement {

        private final List<Signal> sensitivityList = new ArrayList<Signal>();
        private final CaseStatement caseStatement;
        private final List<SequentialStatement> caseStatementList;
        private final List<SequentialStatement> statementsBefore =
                new ArrayList<SequentialStatement>();
        private final List<SequentialStatement> statementsAfter =
                new ArrayList<SequentialStatement>();
        private final List<SequentialStatement> statements;

        public StateMachineProcess() {
            caseStatement = new CaseStatement(getCurrentStateSignal());
            caseStatementList = Arrays.<SequentialStatement>asList(caseStatement);
            statements = new ProxyList<SequentialStatement>(statementsBefore,
                    caseStatementList, statementsAfter);
        }

        public void updateSignals() {
            caseStatement.setExpression(getCurrentStateSignal());
        }

        public void updateStates() {
            caseStatement.getAlternatives().clear();

            for (StateMachine.State state : getStates()) {
                CaseStatement.Alternative alternative =
                        caseStatement.createAlternative(state.getChoice());
                alternative.getStatements().addAll(state.getStatements());
            }
        }

        @Override
        public List<Signal> getSensitivityList() {
            return sensitivityList;
        }

        @Override
        public List<ProcessDeclarativeItem> getDeclarations() {
            return Collections.emptyList();
        }

        @Override
        public List<SequentialStatement> getStatements() {
            updateStates();
            return statements;
        }

        public List<SequentialStatement> getStatementsAfter() {
            return statementsAfter;
        }

        public List<SequentialStatement> getStatementsBefore() {
            return statementsBefore;
        }
    }
}

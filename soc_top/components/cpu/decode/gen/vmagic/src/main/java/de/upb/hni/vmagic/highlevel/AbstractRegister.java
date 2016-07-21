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

import de.upb.hni.vmagic.builtin.StdLogic1164;
import de.upb.hni.vmagic.concurrent.AbstractProcessStatement;
import de.upb.hni.vmagic.declaration.ProcessDeclarativeItem;
import de.upb.hni.vmagic.expression.Equals;
import de.upb.hni.vmagic.expression.Expression;
import de.upb.hni.vmagic.expression.Expressions;
import de.upb.hni.vmagic.statement.IfStatement;
import de.upb.hni.vmagic.statement.SequentialStatement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Abstract register or group.
 */
abstract class AbstractRegister extends AbstractProcessStatement {

    public AbstractRegister() {
    }

    public AbstractRegister(String label) {
        super(label);
    }

    abstract Register getFirstRegister();

    abstract void addClockAssignments(List<SequentialStatement> statements);

    abstract void addResetAssignments(List<SequentialStatement> statements);

    @Override
    public List<ProcessDeclarativeItem> getDeclarations() {
        return Collections.emptyList();
    }

    @Override
    public List<SequentialStatement> getStatements() {
        Register reg = getFirstRegister();
        if (reg == null) {
            return Collections.emptyList();
        }

        Expression clkCondition = Expressions.risingEdge(reg.getClock());
        IfStatement statement;

        if (reg.getReset() == null) {
            statement = new IfStatement(clkCondition);
            addClockAssignments(statement.getStatements());
        } else {
            Expression resetActive;
            if (reg.getResetLevel() == Register.ResetLevel.LOW) {
                resetActive = StdLogic1164.STD_LOGIC_0;
            } else {
                resetActive = StdLogic1164.STD_LOGIC_1;
            }
            Expression resetCondition = new Equals(reg.getReset(), resetActive);

            if (reg.getResetType() == Register.ResetType.ASYNCHRONOUS) {
                statement = new IfStatement(resetCondition);
                addResetAssignments(statement.getStatements());

                IfStatement.ElsifPart clkPart = statement.createElsifPart(clkCondition);
                addClockAssignments(clkPart.getStatements());
            } else {
                statement = new IfStatement(clkCondition);

                IfStatement innerIf = new IfStatement(resetCondition);
                addResetAssignments(innerIf.getStatements());
                addClockAssignments(innerIf.getElseStatements());

                statement.getStatements().add(innerIf);
            }
        }

        return Arrays.<SequentialStatement>asList(statement);
    }
}

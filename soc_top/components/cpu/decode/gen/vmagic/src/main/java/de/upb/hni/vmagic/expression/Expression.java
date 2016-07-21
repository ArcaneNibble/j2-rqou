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

package de.upb.hni.vmagic.expression;

import de.upb.hni.vmagic.Choice;
import de.upb.hni.vmagic.type.SubtypeIndication;

/**
 * Abstract expression.
 */
public abstract class Expression<T extends Expression> extends Choice<T> {

    /**
     * Returns the type of this expression.
     * @return the type of this expression
     */
    public abstract SubtypeIndication getType();

    /**
     * Returns the precedence of this expression.
     * @return the precedence, higher values mean higher precedence
     * @see ExpressionPrecedences
     */
    //TODO: make package private?
    public abstract int getPrecedence();

    /**
     * Used to implement the visitor pattern.
     * @param visitor the visitor
     */
    abstract void accept(ExpressionVisitor visitor);

    @Override
    public abstract T copy();
}

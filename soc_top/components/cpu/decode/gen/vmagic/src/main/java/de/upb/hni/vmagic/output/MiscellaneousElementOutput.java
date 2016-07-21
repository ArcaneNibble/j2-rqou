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

package de.upb.hni.vmagic.output;

import de.upb.hni.vmagic.AssociationElement;
import de.upb.hni.vmagic.ComponentSpecification;
import de.upb.hni.vmagic.DelayMechanism;
import de.upb.hni.vmagic.Range;
import de.upb.hni.vmagic.RangeAttributeName;
import de.upb.hni.vmagic.Signature;
import de.upb.hni.vmagic.SubtypeDiscreteRange;
import de.upb.hni.vmagic.WaveformElement;
import de.upb.hni.vmagic.declaration.Subtype;
import de.upb.hni.vmagic.object.Constant;
import de.upb.hni.vmagic.object.Signal;
import de.upb.hni.vmagic.object.VhdlObjectProvider;
import de.upb.hni.vmagic.type.IndexSubtypeIndication;
import de.upb.hni.vmagic.type.RangeSubtypeIndication;
import de.upb.hni.vmagic.type.ResolvedSubtypeIndication;
import de.upb.hni.vmagic.type.Type;
import de.upb.hni.vmagic.type.UnresolvedType;
import java.util.List;

/**
 * Output module for elements that are not handled by a visitor.
 */
public interface MiscellaneousElementOutput {

    /**
     * Outputs a delay mechanism.
     * @param delayMechanism the delay mechanism
     */
    public void delayMechanism(DelayMechanism delayMechanism);

    /**
     * Outputs a generic clause.
     * @param generic the generic clause
     */
    public void generic(List<VhdlObjectProvider<Constant>> generic);

    /**
     * Outputs a port clause.
     * @param port the port clause
     */
    public void port(List<VhdlObjectProvider<Signal>> port);

    /**
     * Outputs a generic map.
     * @param genericMap the generic map
     */
    public void genericMap(List<AssociationElement> genericMap);

    /**
     * Outputs a port map.
     * @param portMap the port map
     */
    public void portMap(List<AssociationElement> portMap);

    /**
     * Outputs the parameters of a procedure call.
     * @param parameters the parameters
     */
    public void procedureCallParameters(List<AssociationElement> parameters);

    /**
     * Outputs the parameters of a concurrent procedure call.
     * @param parameters the parameters
     */
    public void concurrentProcedureCallParameters(List<AssociationElement> parameters);

    /**
     * Outputs the parameters of a function call.
     * @param parameters the paramters
     */
    public void functionCallParameters(List<AssociationElement> parameters);

    /**
     * Outputs a signature.
     * @param signature the signature
     */
    public void signature(Signature signature);

    /**
     * Outputs a waveform.
     * @param waveform the waveform
     */
    public void waveform(List<WaveformElement> waveform);

    /**
     * Outputs a range.
     * @param range the range
     */
    public void range(Range range);

    /**
     * Outputs a range attribue name.
     * @param range the range attribute name
     */
    public void rangeAttributeName(RangeAttributeName range);

    /**
     * Outputs a subtype discrete range.
     * @param range the subtype discrete range
     */
    public void subtypeDiscreteRange(SubtypeDiscreteRange range);

    /**
     * Outputs a OTHERS choice.
     */
    public void choiceOthers();

    /**
     * Outputs an index subtype indication.
     * @param subtype the index subtype indication
     */
    public void indexSubtypeIndication(IndexSubtypeIndication subtype);

    /**
     * Outputs a range subtype indication.
     * @param subtype the range subtype indication
     */
    public void rangeSubtypeIndication(RangeSubtypeIndication subtype);

    /**
     * Outputs a resolved subtype indication.
     * @param subtype the resolved subtype indication
     */
    public void resolvedSubtypeIndication(ResolvedSubtypeIndication subtype);

    /**
     * Outputs a type subtype indication.
     * @param subtype the type
     */
    public void typeSubtypeIndication(Type subtype);

    /**
     * Outputs a subtype subtype indication.
     * @param subtype the subtype
     */
    public void subtypeSubtypeIndication(Subtype subtype);

    /**
     * Outputs an unresolved type subtype indication.
     * @param subtype the unresolved type
     */
    public void unresolvedTypeSubtypeIndication(UnresolvedType subtype);

    /**
     * Outputs an ALL component specification.
     * @param specification the component specification
     */
    public void allComponentSpecification(ComponentSpecification specification);

    /**
     * Outputs an OTHERS component specification.
     * @param specification the component specification
     */
    public void othersComponentSpecification(ComponentSpecification specification);

    /**
     * Outputs an instantiation list component specification.
     * @param specification the component specification
     */
    public void instantiationListComponentSpecification(ComponentSpecification specification);
}

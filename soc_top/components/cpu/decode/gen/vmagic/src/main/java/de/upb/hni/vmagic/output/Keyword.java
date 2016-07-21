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

/**
 * VHDL keywords.
 */
enum Keyword implements OutputEnum {

    /** Access. */
    ACCESS,
    /** After. */
    AFTER,
    /** Alias. */
    ALIAS,
    /** All. */
    ALL,
    /** Architecture. */
    ARCHITECTURE,
    /** Array. */
    ARRAY,
    /** Assert. */
    ASSERT,
    /** Attribute. */
    ATTRIBUTE,
    /** Begin. */
    BEGIN,
    /** Block. */
    BLOCK,
    /** Body. */
    BODY,
    /** Buffer. */
    BUFFER,
    /** Bus. */
    BUS,
    /** Case. */
    CASE,
    /** Component. */
    COMPONENT,
    /** Configuration. */
    CONFIGURATION,
    /** Constant. */
    CONSTANT,
    /** Disconnect. */
    DISCONNECT,
    /** Downto. */
    DOWNTO,
    /** Else. */
    ELSE,
    /** Elsif. */
    ELSIF,
    /** End. */
    END,
    /** Entity. */
    ENTITY,
    /** Exit. */
    EXIT,
    /** File. */
    FILE,
    /** For. */
    FOR,
    /** Function. */
    FUNCTION,
    /** Generate. */
    GENERATE,
    /** Generic. */
    GENERIC,
    /** Group. */
    GROUP,
    /** Guarded. */
    GUARDED,
    /** If. */
    IF,
    /** Impure. */
    IMPURE,
    /** Inertial. */
    INERTIAL,
    /** In. */
    IN,
    /** Inout. */
    INOUT,
    /** Is. */
    IS,
    /** Label. */
    LABEL,
    /** Library. */
    LIBRARY,
    /** Linkage. */
    LINKAGE,
    /** Literal. */
    LITERAL,
    /** Loop. */
    LOOP,
    /** Map. */
    MAP,
    /** New. */
    NEW,
    /** Next. */
    NEXT,
    /** Null. */
    NULL,
    /** Of. */
    OF,
    /** On. */
    ON,
    /** Open. */
    OPEN,
    /** Others. */
    OTHERS,
    /** Out. */
    OUT,
    /** Package. */
    PACKAGE,
    /** Port. */
    PORT,
    /** Postponed. */
    POSTPONED,
    /** Procedure. */
    PROCEDURE,
    /** Process. */
    PROCESS,
    /** Pure. */
    PURE,
    /** Range. */
    RANGE,
    /** Record. */
    RECORD,
    /** Register. */
    REGISTER,
    /** Reject. */
    REJECT,
    /** Rem. */
    REM,
    /** Report. */
    REPORT,
    /** Return. */
    RETURN,
    /** Select. */
    SELECT,
    /** Severity. */
    SEVERITY,
    /** Shared. */
    SHARED,
    /** Signal. */
    SIGNAL,
    /** Subtype. */
    SUBTYPE,
    /** Then. */
    THEN,
    /** To. */
    TO,
    /** Transport. */
    TRANSPORT,
    /** Type. */
    TYPE,
    /** Unaffected. */
    UNAFFECTED,
    /** Units. */
    UNITS,
    /** Until. */
    UNTIL,
    /** Use. */
    USE,
    /** Variable. */
    VARIABLE,
    /** Wait. */
    WAIT,
    /** When. */
    WHEN,
    /** While. */
    WHILE,
    /** With. */
    WITH;
    private final String lowerCase;
    private final String upperCase;

    Keyword() {
        this.lowerCase = toString().toLowerCase();
        this.upperCase = toString();
    }

    /**
     * Return a lower case version of this keyword.
     * @return a lower case string
     */
    public String getLowerCase() {
        return lowerCase;
    }

    /**
     * Return a uppercase version of this keyword.
     * @return a upper case string
     */
    public String getUpperCase() {
        return upperCase;
    }
}

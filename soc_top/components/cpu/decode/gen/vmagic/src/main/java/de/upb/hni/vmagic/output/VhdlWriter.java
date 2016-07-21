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

import de.upb.hni.vmagic.NamedEntity;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Allows the usage of java.io.Writer for VHDL output.
 */
class VhdlWriter {

    private static final String NULL = "null";
    private final Writer out;
    private final VhdlCodeFormat format;
    private boolean firstAppend = true;
    private int indentationLevel = 0;
    private boolean inAlignBlock;
    private int lineAlignPosition;
    private int alignPosition;
    private List<Line> lines = new ArrayList<Line>();
    private StringBuilder currentLine = new StringBuilder(128);

    /**
     * Creates a vhdl writer adapter.
     * @param out the base writer
     * @param format the code format
     */
    public VhdlWriter(Writer out, VhdlCodeFormat format) {
        this.out = out;
        this.format = format;
    }

    private void handleIndentation() throws IOException {
        if (firstAppend) {
            for (int i = 0; i < indentationLevel; i++) {
                out.append(format.getIndentationString());
            }
            firstAppend = false;
        }
    }

    /**
     * Appends a string.
     * @param s the string
     * @return this writer
     * @throws IOException if an IO error occurred
     */
    public VhdlWriter append(String s) {
        if (inAlignBlock) {
            currentLine.append(s);
        } else {
            try {
                handleIndentation();
                out.append(s);
            } catch (IOException ex) {
                //TODO: report exception
            }
        }

        return this;
    }

    /**
     * Appends an character.
     * @param c the character
     * @return this writer
     * @throws IOException if an IO error occurred
     */
    public VhdlWriter append(char c) {
        if (inAlignBlock) {
            currentLine.append(c);
        } else {
            try {
                handleIndentation();
                out.append(c);
            } catch (IOException ex) {
                //TODO: report exception
            }
        }

        return this;
    }

    /**
     * Appends an output enum.
     * @param value the enum
     * @return this writer
     * @throws IOException if an IO error occurred
     */
    public VhdlWriter append(OutputEnum value) {
        if (value == null) {
            append(NULL);
        } else {
            if (format.isUpperCaseKeywords()) {
                append(value.getUpperCase());
            } else {
                append(value.getLowerCase());
            }
        }

        return this;
    }

    /**
     * Appends two output enums separated by a space character.
     * @param value1 the first enum
     * @param value2 the second enum
     * @return this writer
     * @throws IOException if an IO error occurred
     */
    public VhdlWriter append(OutputEnum value1, OutputEnum value2) {
        append(value1);
        append(' ');
        append(value2);

        return this;
    }

    /**
     * Appends a list of strings separated by a delimiter.
     * @param strings a list of strings
     * @param delimiter the delimiter
     * @return this writer
     * @throws IOException if an IO error occurred
     */
    public VhdlWriter appendStrings(List<String> strings, String delimiter) {
        if (strings == null || delimiter == null) {
            append(NULL);
        } else {
            boolean first = true;
            for (String str : strings) {
                if (first) {
                    first = false;
                } else {
                    append(delimiter);
                }
                append(str);
            }
        }

        return this;
    }

    /**
     * Appends the identifiers of a list of named entities.
     * @param elements a list of named entities
     * @param delimiter the delimiter
     * @return this writer
     * @throws IOException if an IO error occurred
     */
    public VhdlWriter appendIdentifiers(List<? extends NamedEntity> elements, String delimiter) {
        if (elements == null || delimiter == null) {
            append(NULL);
        } else {
            boolean first = true;
            for (NamedEntity element : elements) {
                if (first) {
                    first = false;
                } else {
                    append(delimiter);
                }
                appendIdentifier(element);
            }
        }

        return this;
    }

    /**
     * Appends a list of output enums.
     * @param elements the enums
     * @param delimiter the delimiter
     * @return the writer
     * @throws IOException if an IO error occurred
     */
    public VhdlWriter appendOutputEnums(List<? extends OutputEnum> elements, String delimiter) {
        if (elements == null || delimiter == null) {
            append(NULL);
        } else {
            boolean first = true;
            for (OutputEnum element : elements) {
                if (first) {
                    first = false;
                } else {
                    append(delimiter);
                }
                append(element);
            }
        }

        return this;
    }

    /**
     * Appends the identifier of a named entity.
     * @param namedEntity the named entity
     * @return this writer
     * @throws IOException if an IO error occurred
     */
    public VhdlWriter appendIdentifier(NamedEntity namedEntity) {
        if (namedEntity != null) {
            append(namedEntity.getIdentifier());
        } else {
            append(NULL);
        }

        return this;
    }

    /**
     * Appends a line break.
     * @return this writer
     * @throws IOException if an IO error occurred
     */
    public VhdlWriter newLine() {
        if (inAlignBlock) {
            Line l = new Line(currentLine.toString(), lineAlignPosition);
            lines.add(l);
            currentLine = new StringBuilder(100);
            lineAlignPosition = -1;
        } else {
            try {
                out.append(format.getLineSeparator());
            } catch (IOException ex) {
                //TODO: report exception
            }
        }

        firstAppend = true;
        return this;
    }

    /**
     * Increases the indentation level.
     * If the current line already contains non whitespace characters the current line
     * is indented, otherwise the indentation is increased in the next line.
     * @return the writer
     */
    public VhdlWriter indent() {
        indentationLevel++;

        return this;
    }

    /**
     * Reduces the indentation level.
     * @return the writer
     * @throws IllegalStateException if the indentation level is 0
     */
    public VhdlWriter dedent() {
        if (indentationLevel > 0) {
            indentationLevel--;
        } else {
            throw new IllegalStateException("too many dedents");
        }

        return this;
    }

    /**
     * Begins an alignment block.
     * @return the writer
     * @throws IOException if an IO error occured
     */
    public VhdlWriter beginAlign() {
        if (format.isAlign()) {
            alignPosition = 0;
            lineAlignPosition = -1;
            inAlignBlock = true;
            lines.clear();
        }

        return this;
    }

    private void appendSpaces(int count) {
        for (int i = 0; i < count; i++) {
            append(' ');
        }
    }

    /**
     * Ends an alignment block.
     * @return the writer
     * @throws IOException if an IO error occured
     */
    public VhdlWriter endAlign() {
        boolean newLineAfterLast = true;

        inAlignBlock = false;

        if (currentLine.length() > 0) {
            newLine();
            newLineAfterLast = false;
        }

        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);

            int position = line.getAlignPosition();
            if (position >= 0) {
                append(line.getText().substring(0, position));
                appendSpaces(alignPosition - position);
                append(line.getText().substring(position));
            } else {
                append(line.getText());
            }

            if (newLineAfterLast || i != lines.size() - 1) {
                newLine();
            }
        }

        return this;
    }

    /**
     * Inserts an alignement marker.
     * @return the writer
     * @throws IOException if an IO error occured
     */
    public VhdlWriter align() {
        lineAlignPosition = currentLine.length();
        if (lineAlignPosition > alignPosition) {
            alignPosition = lineAlignPosition;
        }

        return this;
    }

    /**
     * Returns the code format.
     * @return the code format
     */
    public VhdlCodeFormat getFormat() {
        return format;
    }

    private static class Line {

        private final String text;
        private final int alignPosition;

        public Line(String text, int alignPosition) {
            this.text = text;
            this.alignPosition = alignPosition;
        }

        public int getAlignPosition() {
            return alignPosition;
        }

        public String getText() {
            return text;
        }
    }
}

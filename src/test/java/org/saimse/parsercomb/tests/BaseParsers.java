package org.saimse.parsercomb.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import org.saimse.parsercomb.*;
import org.saimse.parsercomb.util.*;

public class BaseParsers {
    @Test
    void testParseBy() throws BadParseException {
        Pair<String, Character> result = new ParseBy(Character::isLowerCase).parse("asdf");
        assertEquals(result, new Pair<>("sdf", 'a'), "(Un)parsed results should be \"sdf\"/'a'");
    }

    @Test
    void testUnparseBy() {
        assertThrows(BadParseException.class, () -> new ParseBy(Character::isLowerCase).parse("ASDF"));
    }

    @Test
    void testParseChar() throws BadParseException {
        Pair<String, Character> result = new CharParser('a').parse("asdf");
        assertEquals(result, new Pair<>("sdf", 'a'), "(Un)parsed results should be \"sdf\"/'a'");
    }

    @Test
    void testUnparseChar() {
        assertThrows(BadParseException.class, () -> new CharParser('a').parse("ASDF"));
    }

    @Test
    void testParseString() throws BadParseException {
        Pair<String, String> result = new StringParser("function").parse("function add();");
        assertEquals(result, new Pair<>(" add();", "function"), "(Un)parsed results should be \" add();\"/\"function\"");
    }

    @Test
    void testUnparseString() {
        assertThrows(BadParseException.class, () -> new StringParser("defun").parse("(lambda)"));
    }

    @Test
    void testParseInteger() throws BadParseException {
        Pair<String, Integer> result = new IntegerParser().parse("1132 number");
        assertEquals(result, new Pair<>(" number", 1132), "(Un)parsed results should be \" number\"/1132");
    }

    @Test
    void testUnparseInteger() {
        assertThrows(BadParseException.class, () -> new IntegerParser().parse("twelve"));
    }
}

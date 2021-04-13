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

    @Test
    void testParseFloat() throws BadParseException {
        Pair<String, Float> result = new FloatParser().parse("11.32 number");
        assertEquals(result, new Pair<>(" number", 11.32f), "(Un)parsed results should be \" number\"/11.32");
    }

    @Test
    void testUnparseFloat() {
        assertThrows(BadParseException.class, () -> new FloatParser().parse("165"));
    }

    @Test
    void testParseNumber() throws BadParseException {
        Pair<String, Float> result1 = new NumberParser().parse("11.32 number");
        Pair<String, Float> result2 = new NumberParser().parse("1132 number");
        assertEquals(result1, new Pair<>(" number", 11.32f), "(Un)parsed results should be \" number\"/11.32");
        assertEquals(result2, new Pair<>(" number", 1132f), "(Un)parsed results should be \" number\"/1132");
    }

    @Test
    void testUnparseNumber() {
        assertThrows(BadParseException.class, () -> new NumberParser().parse("a.165"));
    }
}

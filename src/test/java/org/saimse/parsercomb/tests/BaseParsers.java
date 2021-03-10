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
}

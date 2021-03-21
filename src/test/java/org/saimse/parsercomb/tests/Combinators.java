package org.saimse.parsercomb.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import org.saimse.parsercomb.*;
import org.saimse.parsercomb.combinators.*;
import org.saimse.parsercomb.util.*;

import java.util.List;

import static org.saimse.parsercomb.util.FunctorMap.fmap;

public class Combinators {
    @Test
    void testOr() throws BadParseException {
        Parser<Character> lowercaseParser = new ParseBy(Character::isLowerCase);
        Parser<Character> BParser = new CharParser('B');

        Pair<String, Character> result = new Or<>(BParser, lowercaseParser).parse("asdf");
        assertEquals(result, new Pair<>("sdf", 'a'), "(Un)parsed results should be \"sdf\"/'a'");
    }

    @Test
    void testFmap() throws BadParseException {
        class LowercaseOnly {
            final Character lowercaseCharacter;
            public LowercaseOnly(Character lowercaseCharacter) {
                this.lowercaseCharacter = lowercaseCharacter;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                LowercaseOnly that = (LowercaseOnly) o;
                return lowercaseCharacter.equals(that.lowercaseCharacter);
            }
        }

        Parser<LowercaseOnly> fmappedConstructor = fmap(c -> new LowercaseOnly(c), new ParseBy(Character::isLowerCase));

        Pair<String, LowercaseOnly> result = fmappedConstructor.parse("asdf");
        assertEquals(result, new Pair<>("sdf", new LowercaseOnly('a')), "(Un)parsed results should be \"sdf\"/'a'");
    }

    @Test
    void testSome() throws BadParseException {
        Parser<Character> lowercaseParser = new ParseBy(Character::isLowerCase);
        Parser<List<Character>> lowercaseSpanParser = new Some<>(lowercaseParser);

        Pair<String, List<Character>> empty = lowercaseSpanParser.parse("Hello");
        Pair<String, List<Character>> full = lowercaseSpanParser.parse("hellO");

        assertEquals(empty.a, "Hello");
        assertEquals(empty.b.size(), 0);

        assertEquals(full.a, "O");
        assertEquals(full.b.size(), 4);
    }

    @Test
    void testMany() throws BadParseException {
        Parser<Character> lowercaseParser = new ParseBy(Character::isLowerCase);
        Parser<List<Character>> lowercaseSpanParser = new Many<>(lowercaseParser);

        assertThrows(BadParseException.class, () -> lowercaseSpanParser.parse("Hello"));

        Pair<String, List<Character>> result = lowercaseSpanParser.parse("hellO");
        assertEquals(result.a, "O");
        assertEquals(result.b.size(), 4);
    }

    @Test
    void testSomeTill() throws BadParseException {
        Parser<Character> lowercaseParser = new ParseBy(Character::isLowerCase);
        Parser<Character> periodParser = new CharParser('.');

        Parser<List<Character>> wordParser = new SomeTill<>(lowercaseParser, periodParser);

        assertThrows(BadParseException.class, () -> wordParser.parse("Hello."));

        Pair<String, List<Character>> empty = wordParser.parse(".");
        assertEquals(empty.a, "");
        assertEquals(empty.b.size(), 0);

        Pair<String, List<Character>> word = wordParser.parse("hello.");
        assertEquals(word.a, "");
        assertEquals(word.b.size(), 5);
    }

    @Test
    void testManyTill() throws BadParseException {
        Parser<Character> lowercaseParser = new ParseBy(Character::isLowerCase);
        Parser<Character> periodParser = new CharParser('.');

        Parser<List<Character>> wordParser = new ManyTill<>(lowercaseParser, periodParser);

        assertThrows(BadParseException.class, () -> wordParser.parse("Hello."));
        assertThrows(BadParseException.class, () -> wordParser.parse("."));

        Pair<String, List<Character>> word = wordParser.parse("hello.");
        assertEquals(word.a, "");
        assertEquals(word.b.size(), 5);
    }
}

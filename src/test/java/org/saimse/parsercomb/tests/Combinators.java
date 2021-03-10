package org.saimse.parsercomb.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.saimse.parsercomb.*;
import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.util.*;
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
}

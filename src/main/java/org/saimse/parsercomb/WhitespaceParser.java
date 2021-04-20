package org.saimse.parsercomb;

import org.saimse.parsercomb.combinators.Some;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

public class WhitespaceParser implements Parser<List<Character>>{
    @Override
    public Pair<String, List<Character>> parse(String input) throws BadParseException {
        return new Some<>(new ParseBy(Character::isWhitespace)).parse(input);
    }
}

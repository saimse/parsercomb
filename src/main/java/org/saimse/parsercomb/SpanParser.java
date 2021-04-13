package org.saimse.parsercomb;

import org.saimse.parsercomb.combinators.Many;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;
import org.saimse.parsercomb.util.Predicate;

import java.util.List;

public class SpanParser implements Parser<List<Character>> {
    Predicate<Character> predicate;

    public SpanParser(Predicate<Character> predicate) {
        this.predicate = predicate;
    }

    @Override
    public Pair<String, List<Character>> parse(String input) throws BadParseException {
        return new Many<>(new ParseBy(predicate)).parse(input);
    }
}

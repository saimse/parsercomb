package org.saimse.parsercomb;

import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;
import org.saimse.parsercomb.util.Predicate;

public class ParseBy implements Parser<Character> {
    Predicate<Character> predicate;

    public ParseBy(Predicate<Character> predicate) {
        this.predicate = predicate;
    }

    @Override
    public Pair<String, Character> parse(String input) throws BadParseException {
        if(predicate.predicate(input.charAt(0)))
            return new Pair<>(input.substring(1), input.charAt(0));

        throw new BadParseException();
    }
}

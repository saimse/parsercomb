package org.saimse.parsercomb.combinators;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class Or<A> implements Parser<A> {
    Parser<A>[] parsers;

    public Or(Parser<A> ...parsers) {
        this.parsers = parsers;
    }

    @Override
    public Pair<String, A> parse(String input) throws BadParseException {
        for (Parser<A> p : parsers) {
            try {
                return p.parse(input);
            } catch (Exception ignored) {}
        }
        throw new BadParseException();
    }
}

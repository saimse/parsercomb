package org.saimse.parsercomb.combinators;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Some<A> implements Parser<List<A>> {
    protected final Parser<A> parser;

    public Some(Parser<A> parser) {
        this.parser = parser;
    }

    @Override
    public Pair<String, List<A>> parse(String input) throws BadParseException {
        List<A> as = new ArrayList<>();

        String leftover = input;
        try {
            while(leftover.length() > 0) {
                Pair<String, A> result = parser.parse(leftover);
                leftover = result.a;
                as.add(result.b);
            }
        } catch (BadParseException ignored) {}

        return new Pair<>(leftover, as);
    }
}

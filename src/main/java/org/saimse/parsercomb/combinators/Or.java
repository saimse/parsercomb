package org.saimse.parsercomb.combinators;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class Or<A> extends BinaryHomogenousCombinator<A> {
    public Or(Parser<A> l, Parser<A> r) {
        super(l, r);
    }

    @Override
    public Pair<String, A> parse(String input) throws BadParseException {
        try {
            return l.parse(input);
        } catch (Exception e) {
            return r.parse(input);
        }
    }
}

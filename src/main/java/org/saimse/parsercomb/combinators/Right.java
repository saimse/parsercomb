package org.saimse.parsercomb.combinators;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class Right<A, B> extends BinaryHeterogeneousCombinator<A, B> {
    public Right(Parser<A> l, Parser<B> r) {
        super(l, r);
    }

    @Override
    public Pair<String, B> parse(String input) throws BadParseException {
        Pair<String, A> tempL = l.parse(input);
        return r.parse(tempL.a);
    }
}

package org.saimse.parsercomb.combinators;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class Left<A, B> extends BinaryHeterogeneousCombinator<B, A> {
    public Left(Parser<A> l, Parser<B> r) {
        super(r, l);
    }

    @java.lang.Override
    public Pair<String, A> parse(String input) throws BadParseException {
        Pair<String, A> tempL = r.parse(input);
        Pair<String, B> tempR = l.parse(tempL.a);
        return new Pair<String, A>(tempR.a, tempL.b);
    }
}

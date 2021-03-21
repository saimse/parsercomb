package org.saimse.parsercomb.combinators;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class And<A, B> implements Parser<Pair<A, B>> {
    protected Parser<A> l;
    protected Parser<B> r;

    public And(Parser<A> l, Parser<B> r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public Pair<String, Pair<A, B>> parse(String input) throws BadParseException {
        Pair<String, A> tempL = l.parse(input);
        Pair<String, B> tempR = r.parse(tempL.a);
        return new Pair<>(tempR.a, new Pair<>(tempL.b, tempR.b));
    }
}

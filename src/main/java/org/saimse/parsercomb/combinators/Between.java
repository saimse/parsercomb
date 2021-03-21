package org.saimse.parsercomb.combinators;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class Between<L, A, R> implements Parser<A> {
    protected Parser<L> l;
    protected Parser<A> a;
    protected Parser<R> r;

    public Between(Parser<L> l, Parser<A> a, Parser<R> r) {
        this.l = l;
        this.a = a;
        this.r = r;
    }

    @Override
    public Pair<String, A> parse(String input) throws BadParseException {
        return new Right<>(l, new Left<>(a, r)).parse(input);
    }
}

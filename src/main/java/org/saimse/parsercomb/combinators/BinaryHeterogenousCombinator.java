package org.saimse.parsercomb.combinators;

import org.saimse.parsercomb.Parser;

public abstract class BinaryHeterogenousCombinator<A, B> implements Parser<A> {
    protected final Parser<A> l;
    protected final Parser<B> r;

    public BinaryHeterogenousCombinator(Parser<A> l, Parser<B> r) {
        this.l = l;
        this.r = r;
    }
}

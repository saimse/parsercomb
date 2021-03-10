package org.saimse.parsercomb.combinators;

import org.saimse.parsercomb.Parser;

public abstract class BinaryHomogenousCombinator<A> implements Parser<A> {
    protected Parser<A> l, r;

    public BinaryHomogenousCombinator(Parser<A> l, Parser<A> r) {
        this.l = l;
        this.r = r;
    }
}

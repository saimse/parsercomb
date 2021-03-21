package org.saimse.parsercomb.combinators;

import org.saimse.parsercomb.Parser;

public abstract class BinaryHeterogeneousCombinator<A, B> implements Parser<B> {
    protected Parser<A> l;
    protected Parser<B> r;

    public BinaryHeterogeneousCombinator(Parser<A> l, Parser<B> r){
        this.l = l;
        this.r = r;
    }
}

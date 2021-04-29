package org.saimse.parsercomb.combinators;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class LeftToken<A, B> extends BinaryHeterogeneousCombinator<B, A> {
    public LeftToken(Parser<A> l, Parser<B> r) {
        super(r, l);
    }

    @Override
    public Pair<String, A> parse(String input) throws BadParseException {
        return new Left<>(r, new Left<>(new WhitespaceParser(), l)).parse(input);
    }
}

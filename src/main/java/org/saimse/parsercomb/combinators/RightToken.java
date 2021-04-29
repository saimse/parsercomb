package org.saimse.parsercomb.combinators;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class RightToken<A, B> extends BinaryHeterogeneousCombinator<A, B> {
    public RightToken(Parser<A> l, Parser<B> r) {
        super(l, r);
    }

    @Override
    public Pair<String, B> parse(String input) throws BadParseException {
        return new Right<>(l, new Right<>(new WhitespaceParser(), r)).parse(input);
    }
}

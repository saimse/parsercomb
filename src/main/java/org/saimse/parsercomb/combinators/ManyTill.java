package org.saimse.parsercomb.combinators;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

public class ManyTill<A, B> implements Parser<List<A>> {
    Parser<A> many;
    Parser<B> till;

    public ManyTill(Parser<A> l, Parser<B> r) {
        many = l; till = r;
    }

    @Override
    public Pair<String, List<A>> parse(String input) throws BadParseException {
        Pair<String, List<A>> result = new Many<>(many).parse(input);
        Pair<String, B> tillResult = till.parse(result.a);
        return new Pair<>(tillResult.a, result.b);
    }
}

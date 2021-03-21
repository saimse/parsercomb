package org.saimse.parsercomb.combinators;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

public class SomeTill<A, B> implements Parser<List<A>> {
    Parser<A> some;
    Parser<B> till;

    public SomeTill(Parser<A> l, Parser<B> r) {
        some = l; till = r;
    }

    @Override
    public Pair<String, List<A>> parse(String input) throws BadParseException {
        Pair<String, List<A>> result = new Some<>(some).parse(input);
        Pair<String, B> tillResult = till.parse(result.a);
        return new Pair<>(tillResult.a, result.b);
    }
}

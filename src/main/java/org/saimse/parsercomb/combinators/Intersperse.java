package org.saimse.parsercomb.combinators;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Intersperse<A, B> implements Parser<List<A>> {
    public final Parser<A> l;
    public final Parser<B> r;

    public Intersperse(Parser<A> l, Parser<B> r) {
        this.l = l; this.r = r;
    }

    @Override
    public Pair<String, List<A>> parse(String input) throws BadParseException {
        List<A> items = new ArrayList<>();
        String leftover = input;
        try {
            for(;;) {
                Pair<String, A> item = l.parse(leftover);
                items.add(item.b);
                leftover = r.parse(item.a).a;
            }
        } catch (BadParseException ignored) {}
        return new Pair<>(leftover, items);
    }
}

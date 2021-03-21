package org.saimse.parsercomb.combinators;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

public class Many<A> implements Parser<List<A>> {
    protected final Parser<List<A>> some;

    public Many(Parser<A> parser) {
        some = new Some<>(parser);
    }

    @Override
    public Pair<String, List<A>> parse(String input) throws BadParseException {
        Pair<String, List<A>> result = some.parse(input);
        if(result.b.size() == 0) throw new BadParseException();
        else return result;
    }
}

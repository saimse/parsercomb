package org.saimse.parsercomb;

import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import static org.saimse.parsercomb.util.FunctorMap.fmap;

public class NumberParser implements Parser<Float> {
    @Override
    public Pair<String, Float> parse(String input) throws BadParseException {
        return new Or<>(fmap(Float::new, new IntegerParser()), new FloatParser()).parse(input);
    }
}

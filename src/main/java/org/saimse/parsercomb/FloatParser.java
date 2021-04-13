package org.saimse.parsercomb;

import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

public class FloatParser implements Parser<Float> {
    @Override
    public Pair<String, Float> parse(String input) throws BadParseException {
        Pair<String, Integer> whole = new IntegerParser(true).parse(input);
        Pair<String, Character> tmp = new CharParser('.').parse(whole.a);
        Pair<String, List<Character>> decimal = new SpanParser(Character::isDigit).parse(tmp.a);

        float build = 0.f; int exponent = 1;
        for (Character digit : decimal.b) build += Float.parseFloat(digit.toString()) / (exponent *= 10);

        return new Pair<>(decimal.a, whole.b + build);
    }
}

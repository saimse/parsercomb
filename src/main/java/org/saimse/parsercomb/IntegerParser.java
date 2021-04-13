package org.saimse.parsercomb;

import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

public class IntegerParser implements Parser<Integer> {
    @Override
    public Pair<String, Integer> parse(String input) throws BadParseException {
        Pair<String, List<Character>> res = new SpanParser(Character::isDigit).parse(input);

        Integer build = 0;
        for (Character digit : res.b) {
            build += Integer.parseInt(digit.toString());
            build *= 10;
        }

        return new Pair<>(res.a, build/10);
    }
}

package org.saimse.parsercomb;

import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

public class IntegerParser implements Parser<Integer> {
    private final boolean trailingDot;

    public IntegerParser() { this.trailingDot = false; }

    public IntegerParser(boolean trailingDot) {
        this.trailingDot = trailingDot;
    }

    @Override
    public Pair<String, Integer> parse(String input) throws BadParseException {
        Pair<String, List<Character>> res = new SpanParser(Character::isDigit).parse(input);

        Pair<String, Character> trailing = new Pair<>("",' ');
        try { trailing = new CharParser('.').parse(res.a); }
        catch (BadParseException ignored) {}
        if(!trailingDot && trailing.b.equals('.')) throw new BadParseException();

        Integer build = 0;
        for (Character digit : res.b) {
            build += Integer.parseInt(digit.toString());
            build *= 10;
        }

        return new Pair<>(res.a, build/10);
    }
}

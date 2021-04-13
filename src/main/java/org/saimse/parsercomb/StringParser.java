package org.saimse.parsercomb;

import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class StringParser implements Parser<String> {
    private final String str;

    public StringParser(String str) { this.str = str; }

    @Override
    public Pair<String, String> parse(String input) throws BadParseException {
        String rest = input;
        StringBuilder build = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            Pair<String, Character> res = new CharParser(str.charAt(i)).parse(rest);
            rest = res.a; build.append(res.b);
        }

        return new Pair<>(rest, build.toString());
    }
}

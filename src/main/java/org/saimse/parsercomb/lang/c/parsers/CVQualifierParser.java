package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.SpanParser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class CVQualifierParser implements Parser<Pair<Boolean, Boolean>> {
    @Override
    public Pair<String, Pair<Boolean, Boolean>> parse(String input) throws BadParseException {
        String crest = input;
        boolean c = false;
        try {
            Pair<String, String> res = new Left<>(new StringParser("const"), new SpanParser(Character::isWhitespace)).parse(input);
            crest = res.a; c = true;
        } catch (BadParseException ignored) {}

        String vrest = crest;
        boolean v = false;
        try {
            Pair<String, String> res = new Left<>(new StringParser("volatile"), new SpanParser(Character::isWhitespace)).parse(crest);
            vrest = res.a; v = true;
        } catch (BadParseException ignored) {}

        return new Pair<>(vrest, new Pair<>(c, v));
    }
}

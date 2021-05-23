package org.saimse.parsercomb.lang.c.parsers.literals;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.SpanParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.Right;
import org.saimse.parsercomb.lang.c.expressions.Literal;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

public class StringLiteralParser implements Parser<Literal> {
    @Override
    public Pair<String, Literal> parse(String input) throws BadParseException {
        Pair<String, List<Character>> string = new Right<>(new CharParser('"'),
                new Left<>(new SpanParser(a -> !a.equals('"')), new Left<>(new CharParser('"'), new WhitespaceParser()))).parse(input);
        Literal literal = new Literal(string.b.stream().map(c -> Character.toString(c)).reduce((acc, s) -> acc + s).get());
        return new Pair<>(string.a, literal);
    }
}

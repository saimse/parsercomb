package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.ParseBy;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.SpanParser;
import org.saimse.parsercomb.lang.c.primitives.Char;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class Identifier implements Parser<String> {
    @Override
    public Pair<String, String> parse(String input) throws BadParseException {
        Pair<String, Character> first = new ParseBy(Character::isJavaIdentifierStart).parse(input);
        Pair<String, List<Character>> rest = new SpanParser(Character::isJavaIdentifierPart).parse(first.a);

        String identifier = first.b.toString() + rest.b.stream().map(Object::toString).collect(Collectors.joining());
        return new Pair<>(rest.a, identifier);
    }
}

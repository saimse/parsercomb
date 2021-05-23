package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.*;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.lang.c.primitives.Char;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class Identifier implements Parser<String> {
    @Override
    public Pair<String, String> parse(String input) throws BadParseException {
        Pair<String, Character> first = new ParseBy(Character::isJavaIdentifierStart).parse(input);

        try {
            Pair<String, List<Character>> rest = new Left<>(new SpanParser(Character::isJavaIdentifierPart), new WhitespaceParser()).parse(first.a);
            String identifier = first.b.toString() + rest.b.stream().map(Object::toString).collect(Collectors.joining());
            return new Pair<>(rest.a, identifier);
        } catch (BadParseException ignored) {
            Pair<String, List<Character>> rest = new WhitespaceParser().parse(first.a);
            return new Pair<>(rest.a, first.b.toString());
        }
    }
}

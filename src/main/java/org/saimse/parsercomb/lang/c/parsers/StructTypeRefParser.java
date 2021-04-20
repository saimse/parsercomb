package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.lang.c.StructTypeRef;
import org.saimse.parsercomb.lang.c.Type;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class StructTypeRefParser implements Parser<Type> {
    @Override
    public Pair<String, Type> parse(String input) throws BadParseException {
        Pair<String, Pair<Boolean, Boolean>> cv = new CVQualifierParser().parse(input);
        Pair<String, String> struct = new Left<>(new StringParser("struct"), new WhitespaceParser()).parse(cv.a);
        Pair<String, String> identifier = new Identifier().parse(struct.a);
        return new Pair<>(identifier.a, new StructTypeRef(identifier.b, cv.b.a, cv.b.b));
    }
}

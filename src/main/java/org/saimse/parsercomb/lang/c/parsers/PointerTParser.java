package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.lang.c.Type;
import org.saimse.parsercomb.lang.c.primitives.PointerT;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class PointerTParser implements Parser<Type> {
    @Override
    public Pair<String, Type> parse(String input) throws BadParseException {
        Pair<String, Type> base_t = new Or<>(new StructTypeRefParser(), new PrimitiveTypeParser()).parse(input);
        Pair<String, Character> asterisk = new Left<>(new CharParser('*'), new WhitespaceParser()).parse(base_t.a);
        Pair<String, Pair<Boolean, Boolean>> cv = new CVQualifierParser().parse(asterisk.a);
        return new Pair<>(cv.a, new PointerT(base_t.b, cv.b.a, cv.b.b));
    }
}

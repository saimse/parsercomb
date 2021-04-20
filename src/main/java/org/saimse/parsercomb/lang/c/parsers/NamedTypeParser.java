package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.lang.c.NamedType;
import org.saimse.parsercomb.lang.c.Type;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class NamedTypeParser implements Parser<Type> {
    @Override
    public Pair<String, Type> parse(String input) throws BadParseException {
        Pair<String, Pair<Boolean, Boolean>> cv = new CVQualifierParser().parse(input);
        Pair<String, String> name = new Identifier().parse(cv.a);
        return new Pair<>(name.a, new NamedType(name.b, cv.b.a, cv.b.b));
    }
}

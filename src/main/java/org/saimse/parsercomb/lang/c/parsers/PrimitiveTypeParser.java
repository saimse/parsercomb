package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.combinators.Some;
import org.saimse.parsercomb.lang.c.Type;
import org.saimse.parsercomb.lang.c.primitives.*;
import org.saimse.parsercomb.lang.c.primitives.Double;
import org.saimse.parsercomb.lang.c.primitives.Float;
import org.saimse.parsercomb.lang.c.primitives.Integer;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

import static org.saimse.parsercomb.util.FunctorMap.fmap;

public class PrimitiveTypeParser implements Parser<Type> {
    @Override
    public Pair<String, Type> parse(String input) throws BadParseException {
        Pair<String, Pair<Boolean, Boolean>> cv = new CVQualifierParser().parse(input);

        String urest = cv.a; boolean u = false;
        try {
            Pair<String, String> unsigned = new Left<>(new StringParser("unsigned"), new WhitespaceParser()).parse(cv.a);
            urest = unsigned.a; u = true;
        } catch (BadParseException ignored) {}

        Pair<String, List<String>> longs = new Some<>(new Left<>(new StringParser("long"), new WhitespaceParser())).parse(urest);
        if(longs.b.size() > 2) throw new BadParseException();

        PrimitiveType.LONG_T long_t = PrimitiveType.LONG_T.NO_LONG_T;
        if(longs.b.size() == 1) long_t = PrimitiveType.LONG_T.LONG_T;
        if(longs.b.size() == 2) long_t = PrimitiveType.LONG_T.LONG_LONG_T;

        Pair<String, String> typename = new Left<>(
                new Or<>(new StringParser("int"), new Or<>(
                         new StringParser("float"), new Or<>(
                         new StringParser("double"), new Or<>(
                         new StringParser("char"),
                         // Might default to int, if unsigned or long are present
                         fmap(a -> "", new WhitespaceParser()))))),
                new WhitespaceParser()).parse(longs.a);
        PrimitiveType type;

        switch (typename.b) {
            case "int": type = new Integer(cv.b.a, cv.b.b, u, long_t); break;
            case "float": type = new Float(cv.b.a, cv.b.b, u, long_t); break;
            case "double": type = new Double(cv.b.a, cv.b.b, u, long_t); break;
            case "char": type = new Char(cv.b.a, cv.b.b, u, long_t); break;
            default:
                if(u || long_t != PrimitiveType.LONG_T.NO_LONG_T)
                    type = new Integer(cv.b.a, cv.b.b, u, long_t);
                else throw new BadParseException();
                break;
        }

        return new Pair<>(typename.a, type);
    }
}

package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.*;
import org.saimse.parsercomb.lang.c.Type;
import org.saimse.parsercomb.lang.c.primitives.PointerT;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

public class TypeParser implements Parser<Type> {
    @Override
    public Pair<String, Type> parse(String input) throws BadParseException {
        Pair<String, Type> non_ptr
                = new Or<>(new PrimitiveTypeParser(),
                           new StructTypeRefParser(),
                           new NamedTypeParser()).parse(input);

        Right<Character, Pair<Boolean, Boolean>> asteriskAndCV =
                new Right<>(new Left<>(new CharParser('*'), new WhitespaceParser()), new CVQualifierParser());

        Pair<String, List<Pair<Boolean, Boolean>>> ptrs = new Some<>(asteriskAndCV).parse(non_ptr.a);

        Type curr_t = non_ptr.b;
        for (Pair<Boolean, Boolean> cv : ptrs.b) curr_t = new PointerT(curr_t, cv.a, cv.b);

        return new Pair<>(ptrs.a, curr_t);
    }
}

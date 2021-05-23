package org.saimse.parsercomb.lang.c.parsers.literals;

import org.saimse.parsercomb.*;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.Right;
import org.saimse.parsercomb.lang.c.expressions.Literal;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

import static org.saimse.parsercomb.util.FunctorMap.fmap;

public class CharLiteralParser implements Parser<Literal> {
    @Override
    public Pair<String, Literal> parse(String input) throws BadParseException {
        return fmap(Literal::new, new Right<>(new CharParser('\''),
                new Left<>(new ParseBy(a -> true), new Left<>(new CharParser('\''), new WhitespaceParser())))).parse(input);
    }
}

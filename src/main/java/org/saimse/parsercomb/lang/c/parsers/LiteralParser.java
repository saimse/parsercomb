package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.FloatParser;
import org.saimse.parsercomb.IntegerParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.expressions.Literal;
import org.saimse.parsercomb.lang.c.parsers.literals.CharLiteralParser;
import org.saimse.parsercomb.lang.c.parsers.literals.StringLiteralParser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import static org.saimse.parsercomb.util.FunctorMap.fmap;

public class LiteralParser implements Parser<Expression> {
    @Override
    public Pair<String, Expression> parse(String input) throws BadParseException {
        Parser<Literal> fpParser = new Left<>(fmap(f -> new Literal(new Double(f)), new FloatParser()), new WhitespaceParser());
        Parser<Literal> intParser = new Left<>(fmap(Literal::new, new IntegerParser()), new WhitespaceParser());
        Pair<String, Literal> literal = new Or<>(fpParser, intParser, new StringLiteralParser(), new CharLiteralParser()).parse(input);
        return new Pair<>(literal.a, literal.b);
    }
}

package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.*;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.expressions.FunCall;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

public class FunCallParser implements Parser<Expression> {
    @Override
    public Pair<String, Expression> parse(String input) throws BadParseException {
        Pair<String, String> funName = new Left<>(new Identifier(), new WhitespaceParser()).parse(input);
        Pair<String, List<Expression>> args = new RightToken<>(new CharParser('('), new LeftToken<>(new Intersperse<>(
                new Left<>(new Or<>(new ExpressionParser(), new ExpressionWithPrimaryParser()), new WhitespaceParser()),
                new Left<>(new CharParser(','), new WhitespaceParser())
        ), new Left<>(new CharParser(')'), new WhitespaceParser()))).parse(funName.a);
        return new Pair<>(args.a, new FunCall(new org.saimse.parsercomb.lang.c.expressions.Identifier(funName.b), args.b));
    }
}

package org.saimse.parsercomb.lang.c.parsers.operators;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.LeftToken;
import org.saimse.parsercomb.combinators.RightToken;
import org.saimse.parsercomb.lang.c.Type;
import org.saimse.parsercomb.lang.c.expressions.CastOp;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.expressions.UnaryOp;
import org.saimse.parsercomb.lang.c.parsers.ExpressionParser;
import org.saimse.parsercomb.lang.c.parsers.TypeParser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class CastParser implements Parser<UnaryOp> {
    @Override
    public Pair<String, UnaryOp> parse(String input) throws BadParseException {
        Pair<String, Type> type = new RightToken<>(new CharParser('('), new LeftToken<>(new TypeParser(), new Left<>(new CharParser(')'), new WhitespaceParser()))).parse(input);
        Pair<String, Expression> expr = new ExpressionParser().parse(type.a);
        return new Pair<>(expr.a, new CastOp(type.b, expr.b));
    }
}
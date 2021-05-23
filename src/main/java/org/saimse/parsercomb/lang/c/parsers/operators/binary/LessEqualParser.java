package org.saimse.parsercomb.lang.c.parsers.operators.binary;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.*;
import org.saimse.parsercomb.lang.c.expressions.BinaryOp;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.parsers.ExpressionParser;
import org.saimse.parsercomb.lang.c.parsers.ExpressionWithPrimaryParser;
import org.saimse.parsercomb.lang.c.parsers.PrimaryExpressionParser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class LessEqualParser implements Parser<Expression> {
    @java.lang.Override
    public Pair<String, Expression> parse(String input) throws BadParseException {
        Pair<String, Pair<Expression, Expression>> op = new And<>(new LeftToken<>(new Or<>(new PrimaryExpressionParser(), new RightToken<>(new CharParser('('), new LeftToken<>(new ExpressionWithPrimaryParser(), new LeftToken<>(new CharParser(')'), new WhitespaceParser())))), new Left<>(new StringParser("<=")
                , new WhitespaceParser())), new Left<>(new Or<>(new ExpressionParser(), new ExpressionWithPrimaryParser()), new WhitespaceParser())).parse(input);
        return new Pair<>(op.a, new BinaryOp(BinaryOp.OP.LESS_EQ, op.b.a, op.b.b));
    }
}

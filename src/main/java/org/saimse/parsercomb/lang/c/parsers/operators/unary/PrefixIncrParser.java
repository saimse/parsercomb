package org.saimse.parsercomb.lang.c.parsers.operators.unary;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.combinators.RightToken;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.expressions.UnaryOp;
import org.saimse.parsercomb.lang.c.parsers.ExpressionWithPrimaryParser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class PrefixIncrParser implements Parser<Expression> {
    @Override
    public Pair<String, Expression> parse(String input) throws BadParseException {
        Pair<String, Expression> op = new RightToken<>(new StringParser("++"), new ExpressionWithPrimaryParser()).parse(input);
        return new Pair<>(op.a, new UnaryOp(UnaryOp.OP.PREFIX_INCR, op.b));
    }
}
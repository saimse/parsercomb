package org.saimse.parsercomb.lang.c.parsers.operators;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.LeftToken;
import org.saimse.parsercomb.combinators.RightToken;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.expressions.UnaryOp;
import org.saimse.parsercomb.lang.c.parsers.ExpressionParser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class SuffixDecrParser implements Parser<UnaryOp> {
    @Override
    public Pair<String, UnaryOp> parse(String input) throws BadParseException {
        Pair<String, Expression> op = new LeftToken<>(new ExpressionParser(), new Left<>(new StringParser("--"), new WhitespaceParser())).parse(input);
        return new Pair<>(op.a, new UnaryOp(UnaryOp.OP.POSTFIX_DECR, op.b));
    }
}

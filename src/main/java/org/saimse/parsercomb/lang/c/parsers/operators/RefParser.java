package org.saimse.parsercomb.lang.c.parsers.operators;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.combinators.RightToken;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.expressions.UnaryOp;
import org.saimse.parsercomb.lang.c.parsers.ExpressionParser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class RefParser implements Parser<UnaryOp> {
    @Override
    public Pair<String, UnaryOp> parse(String input) throws BadParseException {
        Pair<String, Expression> op = new RightToken<>(new StringParser("&"), new ExpressionParser()).parse(input);
        return new Pair<>(op.a, new UnaryOp(UnaryOp.OP.REF, op.b));
    }
}
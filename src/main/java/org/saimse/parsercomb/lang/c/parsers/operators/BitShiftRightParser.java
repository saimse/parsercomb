package org.saimse.parsercomb.lang.c.parsers.operators;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.And;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.LeftToken;
import org.saimse.parsercomb.lang.c.expressions.BinaryOp;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.parsers.ExpressionParser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class BitShiftRightParser implements Parser<BinaryOp> {
    @java.lang.Override
    public Pair<String, BinaryOp> parse(String input) throws BadParseException {
        Pair<String, Pair<Expression, Expression>> op = new And(new LeftToken<>(new ExpressionParser(), new Left<>(new StringParser(">>")
                , new WhitespaceParser())), new Left(new ExpressionParser(), new WhitespaceParser())).parse(input);
        return new Pair<>(op.a, new BinaryOp(BinaryOp.OP.BIT_SHIFT_RIGHT, op.b.a, op.b.b));
    }
}

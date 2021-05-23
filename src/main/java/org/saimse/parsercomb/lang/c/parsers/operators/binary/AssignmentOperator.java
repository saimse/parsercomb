package org.saimse.parsercomb.lang.c.parsers.operators.binary;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.And;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.LeftToken;
import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.lang.c.expressions.BinaryOp;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.parsers.ExpressionParser;
import org.saimse.parsercomb.lang.c.parsers.ExpressionSansAssignParser;
import org.saimse.parsercomb.lang.c.parsers.ExpressionWithPrimaryParser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class AssignmentOperator implements Parser<Expression> {
    @java.lang.Override
    public Pair<String, Expression> parse(String input) throws BadParseException {
        Pair<String, Pair<Expression, Expression>> op = new And<>(new LeftToken<>(new Or<>(new SubscriptParser(), new MemberArrowAccessParser(), new MemberAccessParser(), new ExpressionSansAssignParser(), new ExpressionWithPrimaryParser()), new Left<>(new StringParser("=")
                , new WhitespaceParser())), new Left<>(new Or<>(new ExpressionSansAssignParser(), new ExpressionWithPrimaryParser()), new WhitespaceParser())).parse(input);
        return new Pair<>(op.a, new BinaryOp(BinaryOp.OP.ASSIGN, op.b.a, op.b.b));
    }
}

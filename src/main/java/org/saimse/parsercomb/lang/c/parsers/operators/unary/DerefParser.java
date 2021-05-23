package org.saimse.parsercomb.lang.c.parsers.operators.unary;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.LeftToken;
import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.combinators.RightToken;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.expressions.UnaryOp;
import org.saimse.parsercomb.lang.c.parsers.ExpressionParser;
import org.saimse.parsercomb.lang.c.parsers.ExpressionWithPrimaryParser;
import org.saimse.parsercomb.lang.c.parsers.PrimaryExpressionParser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class DerefParser implements Parser<Expression> {
    @Override
    public Pair<String, Expression> parse(String input) throws BadParseException {
        Pair<String, Expression> op = new RightToken<>(new StringParser("*"), new RightToken<>(new CharParser('('), new LeftToken<>(new Or<>(new ExpressionParser()), new LeftToken<>(new CharParser(')'), new WhitespaceParser())))).parse(input);
        return new Pair<>(op.a, new UnaryOp(UnaryOp.OP.DEREF, op.b));
    }
}
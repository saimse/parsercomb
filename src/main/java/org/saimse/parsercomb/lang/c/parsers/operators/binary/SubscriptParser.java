package org.saimse.parsercomb.lang.c.parsers.operators.binary;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.LeftToken;
import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.combinators.RightToken;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.expressions.Subscript;
import org.saimse.parsercomb.lang.c.parsers.ExpressionWithPrimaryParser;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

public class SubscriptParser implements Parser<Expression> {
    @Override
    public Pair<String, Expression> parse(String input) throws BadParseException {
        Pair<String, Expression> l = new LeftToken<>(new Or<>(new MemberArrowAccessParser(), new MemberAccessParser(), new ExpressionWithPrimaryParser()), new RightToken<>(new CharParser('['), new WhitespaceParser())).parse(input);
        Pair<String, Expression> r = new LeftToken<>(new Or<>(new MemberArrowAccessParser(), new MemberAccessParser(), new ExpressionWithPrimaryParser()), new LeftToken<>(new CharParser(']'), new WhitespaceParser())).parse(l.a);
        return new Pair<>(r.a, new Subscript(l.b, r.b));
    }
}

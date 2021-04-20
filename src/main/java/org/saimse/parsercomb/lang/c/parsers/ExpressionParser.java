package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.util.Pair;

public class ExpressionParser implements Parser<Expression> {
    public Pair<String, Expression> parse(String input) {
        return new Pair<>(input, new Expression());
    }
}
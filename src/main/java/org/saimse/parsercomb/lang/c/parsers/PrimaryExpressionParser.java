package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import static org.saimse.parsercomb.util.FunctorMap.fmap;

public class PrimaryExpressionParser implements Parser<Expression> {
    @Override
    public Pair<String, Expression> parse(String input) throws BadParseException {
        return new Or<>(new LiteralParser(), fmap(org.saimse.parsercomb.lang.c.expressions.Identifier::new, new Identifier())).parse(input);
    }
}

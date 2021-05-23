package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.LeftToken;
import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.combinators.RightToken;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class ExpressionWithPrimaryParser implements Parser<Expression> {
    @Override
    public Pair<String, Expression> parse(String input) throws BadParseException {
        return new Or<>(new PrimaryExpressionParser(),
                new RightToken<>(new CharParser('('), new LeftToken<>(new Or<>(new PrimaryExpressionParser()), new LeftToken<>(new CharParser(')'), new WhitespaceParser())))
        ).parse(input);
    }
}

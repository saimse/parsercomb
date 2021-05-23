package org.saimse.parsercomb.lang.c.parsers.operators.binary;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.And;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.LeftToken;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.expressions.MemberAccess;
import org.saimse.parsercomb.lang.c.parsers.ExpressionWithPrimaryParser;
import org.saimse.parsercomb.lang.c.parsers.Identifier;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class MemberAccessParser implements Parser<Expression> {
    @Override
    public Pair<String, Expression> parse(String input) throws BadParseException {
        Pair<String, Pair<Expression, String>> op = new And<>(new LeftToken<>(new ExpressionWithPrimaryParser(), new Left<>(new StringParser(".")
                , new WhitespaceParser())), new Left<>(new Identifier(), new WhitespaceParser())).parse(input);
        return new Pair<>(op.a, new MemberAccess(op.b.a, op.b.b));
    }
}

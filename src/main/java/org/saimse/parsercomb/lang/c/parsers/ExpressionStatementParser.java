package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.LeftToken;
import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.statements.ExpressionStatement;
import org.saimse.parsercomb.lang.c.statements.Statement;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import static org.saimse.parsercomb.util.FunctorMap.fmap;

public class ExpressionStatementParser implements Parser<Statement> {
    @Override
    public Pair<String, Statement> parse(String input) throws BadParseException {
        return fmap(s -> (Statement)new ExpressionStatement(s), new LeftToken<>(new Or<>( new ExpressionParser()), new Left<>(new CharParser(';'), new WhitespaceParser()))).parse(input);
    }
}

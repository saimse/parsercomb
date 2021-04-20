package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.Right;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.statements.IfOnlyStatement;
import org.saimse.parsercomb.lang.c.statements.Statement;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class IfOnlyStatementParser implements Parser<Statement> {
    public Pair<String, Statement> parse(String input) throws BadParseException {
        Pair<String, Expression> condition =
                new Right<>(new StringParser("if"), new Right<>(
                        new WhitespaceParser(), new Right<>(
                        new CharParser('('),    new Left<>(
                        new ExpressionParser(), new Left<>(
                        new CharParser(')'), new WhitespaceParser()))))).parse(input);

        Pair<String, Statement> body = new StatementParser().parse(condition.a);
        return new Pair<>(body.a, new IfOnlyStatement(condition.b, body.b));
    }
}

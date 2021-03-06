package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.LeftToken;
import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.combinators.RightToken;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.statements.Statement;
import org.saimse.parsercomb.lang.c.statements.WhileLoop;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class WhileLoopParser implements Parser<Statement> {
    static private class DoWhileParser implements Parser<Statement> {
        @Override
        public Pair<String, Statement> parse(String input) throws BadParseException {
            Pair<String, Statement> body = new RightToken<>(new StringParser("do"), new StatementParser()).parse(input);
            Pair<String, Expression> condition =
                new RightToken<>(new StringParser("while"),
                    new RightToken<>(new CharParser('('),
                    new LeftToken<>(new ExpressionWithPrimaryParser(),
                    new LeftToken<>(new CharParser(')'),
                            new Left<>(new CharParser(';'), new WhitespaceParser()))))).parse(body.a);

            return new Pair<>(condition.a, new WhileLoop(true, condition.b, body.b));
        }
    }

    private static class WhileParser implements Parser<Statement> {
        @Override
        public Pair<String, Statement> parse(String input) throws BadParseException {
            Pair<String, Expression> condition =
                    new RightToken<>(new StringParser("while"),
                        new RightToken<>(new CharParser('('),
                        new LeftToken<>(new ExpressionWithPrimaryParser(),
                        new LeftToken<>(new CharParser(')'),
                            new Left<>(new CharParser(';'), new WhitespaceParser()))))).parse(input);
            Pair<String, Statement> body = new StatementParser().parse(condition.a);
            return new Pair<>(body.a, new WhileLoop(false, condition.b, body.b));
        }
    }

    @Override
    public Pair<String, Statement> parse(String input) throws BadParseException {
        return new Or<>(new DoWhileParser(), new WhileParser()).parse(input);
    }
}

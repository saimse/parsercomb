package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.LeftToken;
import org.saimse.parsercomb.combinators.RightToken;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.statements.ForLoop;
import org.saimse.parsercomb.lang.c.statements.Statement;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class ForLoopParser implements Parser<Statement> {
    @Override
    public Pair<String, Statement> parse(String input) throws BadParseException {
        Pair<String, Expression> init =
                new RightToken<>(new StringParser("for"),
                    new RightToken<>(new CharParser('('),
                    new LeftToken<>(new ExpressionParser(),
                            new Left<>(new CharParser(';'), new WhitespaceParser()))))
                        .parse(input);
        Pair<String, Expression> condition =
                new LeftToken<>(new ExpressionParser(),
                    new Left<>(new CharParser(';'), new WhitespaceParser())).parse(init.a);
        Pair<String, Expression> increment =
                new LeftToken<>(new ExpressionParser(),
                    new LeftToken<>(new CharParser(';'),
                            new Left<>(new CharParser(')'), new WhitespaceParser()))).parse(condition.a);
        Pair<String, Statement> body = new StatementParser().parse(increment.a);

        return new Pair<>(body.a, new ForLoop(init.b, condition.b, increment.b, body.b));
    }
}

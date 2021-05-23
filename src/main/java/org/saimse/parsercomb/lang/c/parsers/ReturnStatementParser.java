package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.LeftToken;
import org.saimse.parsercomb.combinators.RightToken;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.statements.ReturnStatement;
import org.saimse.parsercomb.lang.c.statements.Statement;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class ReturnStatementParser implements Parser<Statement> {
    @Override
    public Pair<String, Statement> parse(String input) throws BadParseException {
        Pair<String, Expression> parse = new RightToken<>(new StringParser("return"), new LeftToken<>(new ExpressionWithPrimaryParser(), new Left<>(new CharParser(';'), new WhitespaceParser()))).parse(input);
        return new Pair<>(parse.a, new ReturnStatement(parse.b));
    }
}

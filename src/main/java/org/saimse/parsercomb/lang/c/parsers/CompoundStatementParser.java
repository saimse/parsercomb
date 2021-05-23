package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.LeftToken;
import org.saimse.parsercomb.combinators.RightToken;
import org.saimse.parsercomb.combinators.Some;
import org.saimse.parsercomb.lang.c.statements.CompoundStatement;
import org.saimse.parsercomb.lang.c.statements.Statement;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

public class CompoundStatementParser implements Parser<Statement> {
    @Override
    public Pair<String, Statement> parse(String input) throws BadParseException {
        Pair<String, List<Statement>> parse = new RightToken<>(new CharParser('{'), new LeftToken<>(
                new Some<>(new Left<>(new StatementParser(), new WhitespaceParser()))
                , new Left<>(new CharParser('}'), new WhitespaceParser()))).parse(input);
        return new Pair<>(parse.a, new CompoundStatement(parse.b));
    }
}

package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.lang.c.statements.Statement;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class StatementParser implements Parser<Statement> {
    public Pair<String, Statement> parse(String input) throws BadParseException {
        return new IfOnlyStatementParser().parse(input);
    }
}

package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.lang.c.statements.Statement;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import static org.saimse.parsercomb.util.FunctorMap.fmap;

public class StatementParser implements Parser<Statement> {
    public Pair<String, Statement> parse(String input) throws BadParseException {
        return new Or<>(new CompoundStatementParser(), new ExpressionStatementParser(), new ReturnStatementParser(), fmap(d -> d, new VarDeclParser()), new IfStatementParser(), new WhileLoopParser(), new ForLoopParser()).parse(input);
    }
}

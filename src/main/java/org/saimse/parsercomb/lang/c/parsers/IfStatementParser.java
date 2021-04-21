package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.Many;
import org.saimse.parsercomb.combinators.Right;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.statements.IfStatement;
import org.saimse.parsercomb.lang.c.statements.Statement;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

import static org.saimse.parsercomb.util.FunctorMap.fmap;

public class IfStatementParser implements Parser<Statement> {
    @Override
    public Pair<String, Statement> parse(String input) throws BadParseException {
        Pair<String, IfStatement.IfOnlyStatement> start = new IfOnlyStatementParser().parse(input);
        Pair<String, List<IfStatement.IfElseIfStatement>> elseIfs = new Many<>(new IfElseIfStatementParser()).parse(start.a);

        Pair<String, IfStatement.IfElseStatement> end;
        try {
            end = new IfElseStatementParser().parse(elseIfs.a);
        } catch (BadParseException ignored) {
            end = fmap(a -> (IfStatement.IfElseStatement) null, new WhitespaceParser()).parse(elseIfs.a);
        }

        return new Pair<>(end.a, new IfStatement(start.b, elseIfs.b.toArray(new IfStatement.IfElseIfStatement[elseIfs.b.size()]), end.b));
    }

    public static class IfElseIfStatementParser implements Parser<IfStatement.IfElseIfStatement> {
        public Pair<String, IfStatement.IfElseIfStatement> parse(String input) throws BadParseException {
            Pair<String, Expression> condition =
                    new Right<>(new StringParser("if"), new Right<>(
                            new WhitespaceParser(), new Right<>(
                            new StringParser("else"), new Right<>(
                            new WhitespaceParser(), new Right<>(
                            new CharParser('('), new Left<>(
                            new ExpressionParser(), new Left<>(
                            new CharParser(')'), new WhitespaceParser()))))))).parse(input);

            Pair<String, Statement> body = new StatementParser().parse(condition.a);
            return new Pair<>(body.a, new IfStatement.IfElseIfStatement(condition.b, body.b));
        }
    }

    public static class IfElseStatementParser implements Parser<IfStatement.IfElseStatement> {
        public Pair<String, IfStatement.IfElseStatement> parse(String input) throws BadParseException {
            Pair<String, String> condition =
                    new Left<>(new StringParser("else"), new WhitespaceParser()).parse(input);

            Pair<String, Statement> body = new StatementParser().parse(condition.a);
            return new Pair<>(body.a, new IfStatement.IfElseStatement(body.b));
        }
    }

    public static class IfOnlyStatementParser implements Parser<IfStatement.IfOnlyStatement> {
        public Pair<String, IfStatement.IfOnlyStatement> parse(String input) throws BadParseException {
            Pair<String, Expression> condition =
                    new Right<>(new StringParser("if"), new Right<>(
                            new WhitespaceParser(), new Right<>(
                            new CharParser('('), new Left<>(
                            new ExpressionParser(), new Left<>(
                            new CharParser(')'), new WhitespaceParser()))))).parse(input);

            Pair<String, Statement> body = new StatementParser().parse(condition.a);
            return new Pair<>(body.a, new IfStatement.IfOnlyStatement(condition.b, body.b));
        }
    }
}

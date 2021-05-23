package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.*;
import org.saimse.parsercomb.lang.c.Type;
import org.saimse.parsercomb.lang.c.decls.Declaration;
import org.saimse.parsercomb.lang.c.decls.FunDecl;
import org.saimse.parsercomb.lang.c.statements.Statement;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

public class FunDeclParser implements Parser<Declaration> {
    @Override
    public Pair<String, Declaration> parse(String input) throws BadParseException {
        Pair<String, Type> ret = new TypeParser().parse(input);
        Pair<String, String> name = new Identifier().parse(ret.a);
        Pair<String, List<Pair<Type, String>>> params = new RightToken<>(new CharParser('('), new LeftToken<>(

                new Intersperse<>(
                        new And<>(new Left<>(new TypeParser(), new WhitespaceParser()),
                                new Left<>(new Identifier(), new WhitespaceParser())),

                        new Left<>(new CharParser(','), new WhitespaceParser())
                )

                , new Left<>(new CharParser(')'), new WhitespaceParser()))).parse(name.a);

        Pair<String, Statement> body = new CompoundStatementParser().parse(params.a);
        return new Pair<>(body.a, new FunDecl(ret.b, name.b, params.b, body.b));
    }
}

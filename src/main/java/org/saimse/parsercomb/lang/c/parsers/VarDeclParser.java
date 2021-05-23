package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.LeftToken;
import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.combinators.RightToken;
import org.saimse.parsercomb.lang.c.Type;
import org.saimse.parsercomb.lang.c.decls.Declaration;
import org.saimse.parsercomb.lang.c.decls.VarDecl;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class VarDeclParser implements Parser<Declaration> {
    @Override
    public Pair<String, Declaration> parse(String input) throws BadParseException {
        Pair<String, Type> type = new Left<>(new TypeParser(), new WhitespaceParser()).parse(input);
        Pair<String, String> name = new Left<>(new Identifier(), new WhitespaceParser()).parse(type.a);

        Pair<String, Expression> setval = null;
        try {
            setval = new RightToken<>(new CharParser('='), new Or<>(new ExpressionParser(), new ExpressionWithPrimaryParser())).parse(name.a);
        } catch (BadParseException ignored) {}

        String rest = (setval == null) ? name.a : setval.a;

        Pair<String, Character> parse = new Left<>(new CharParser(';'), new WhitespaceParser()).parse(rest);

        return new Pair<>(parse.a, new VarDecl(type.b, name.b, (setval == null) ? null : setval.b));
    }
}

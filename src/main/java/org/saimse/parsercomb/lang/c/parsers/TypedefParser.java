package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.LeftToken;
import org.saimse.parsercomb.combinators.RightToken;
import org.saimse.parsercomb.lang.c.Type;
import org.saimse.parsercomb.lang.c.decls.Declaration;
import org.saimse.parsercomb.lang.c.decls.StructDecl;
import org.saimse.parsercomb.lang.c.decls.Typedef;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.Objects;

public class TypedefParser implements Parser<Declaration> {
    @Override
    public Pair<String, Declaration> parse(String input) throws BadParseException {
        Pair<String, Type> from_t = null;
        Pair<String, Declaration> from_struct = null;
        try {
            from_struct = new RightToken<>(new StringParser("typedef"), new Left<>(new StructDeclParser(false), new WhitespaceParser())).parse(input);
        } catch (BadParseException ignored) {
            from_t = new RightToken<>(new StringParser("typedef"), new Left<>(new TypeParser(), new WhitespaceParser())).parse(input);
        }
        Pair<String, String> to = new LeftToken<>(new Identifier(), new Left<>(new CharParser(';'), new WhitespaceParser())).parse(from_t == null ? Objects.requireNonNull(from_struct).a : from_t.a);
        return new Pair<>(to.a, from_t != null ? new Typedef(from_t.b, to.b) : new Typedef((StructDecl)from_struct.b, to.b));
    }
}

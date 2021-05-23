package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.CharParser;
import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.StringParser;
import org.saimse.parsercomb.WhitespaceParser;
import org.saimse.parsercomb.combinators.Left;
import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.combinators.Right;
import org.saimse.parsercomb.combinators.Some;
import org.saimse.parsercomb.lang.c.Type;
import org.saimse.parsercomb.lang.c.decls.Declaration;
import org.saimse.parsercomb.lang.c.statements.Statement;
import org.saimse.parsercomb.lang.c.decls.StructDecl;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

import static org.saimse.parsercomb.util.FunctorMap.fmap;

public class StructDeclParser implements Parser<Declaration> {
    private class StructFieldDeclParser implements Parser<StructDecl.StructFieldDecl> {
        @Override
        public Pair<String, StructDecl.StructFieldDecl> parse(String input) throws BadParseException {
            Pair<String, Type> type = new TypeParser().parse(input);
            Pair<String, String> name = new Left<>(new Identifier(),
                    new Left<>(new WhitespaceParser(),
                            new Left<>(new CharParser(';'), new WhitespaceParser()))).parse(type.a);
            return new Pair<>(name.a, new StructDecl.StructFieldDecl(type.b, name.b));
        }
    }

    private final boolean semicolon;

    public StructDeclParser() {
        this.semicolon = false;
    }

    public StructDeclParser(boolean semicolon) {
        this.semicolon = semicolon;
    }

    @Override
    public Pair<String, Declaration> parse(String input) throws BadParseException {
        Pair<String, String> suffix =
        new Right<>(new StringParser("struct"),
        new Right<>(new WhitespaceParser(),
        // nop sled into identifier; or null if anonymous
        new Left<>(new Or<>(new Identifier(), fmap(a->null,new WhitespaceParser())),
        new Left<>(new WhitespaceParser(),
        new Left<>(new CharParser('{'),
                   new WhitespaceParser()))))).parse(input);

        Pair<String, List<StructDecl.StructFieldDecl>> fields = new Some<>(new StructFieldDeclParser()).parse(suffix.a);

        Pair<String, Character> rest = new Left<>(new CharParser('}'),
                semicolon ? new Left<>(new WhitespaceParser(), new CharParser(';')) : new WhitespaceParser()).parse(fields.a);

        return new Pair<>(rest.a, new StructDecl(suffix.b, fields.b));
    }
}

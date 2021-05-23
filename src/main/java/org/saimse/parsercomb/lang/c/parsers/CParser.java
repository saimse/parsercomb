package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.combinators.Some;
import org.saimse.parsercomb.lang.c.decls.Declaration;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

public class CParser implements Parser<List<Declaration>> {
    @Override
    public Pair<String, List<Declaration>> parse(String input) throws BadParseException {
        return new Some<>(new Or<>(new StructDeclParser(), new FunDeclParser(), new TypedefParser(), new VarDeclParser())).parse(input);
    }
}

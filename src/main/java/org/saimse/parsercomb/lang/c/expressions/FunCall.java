package org.saimse.parsercomb.lang.c.expressions;

import java.util.List;

public class FunCall extends Expression {
    public final Identifier fun;
    public final List<Expression> vals;

    public FunCall(Identifier fun, List<Expression> vals) {
        this.fun = fun;
        this.vals = vals;
    }
}

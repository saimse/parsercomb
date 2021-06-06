package org.saimse.parsercomb.lang.c.expressions;

import java.util.List;

public class FunCall extends Expression {
    public final Identifier fun;
    public final List<Expression> vals;

    public FunCall(Identifier fun, List<Expression> vals) {
        this.fun = fun;
        this.vals = vals;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(fun.toString());
        s.append(" (");
        for (Expression currVal : vals) {
            s.append(currVal.toString());
            s.append(", ");
        }
        if (s.length() != 1)
        s.delete(s.length() - 2, s.length());
        s.append(")");
        return s.toString();
    }
}

package org.saimse.parsercomb.lang.c.statements;

import org.saimse.parsercomb.lang.c.expressions.Expression;

public class ReturnStatement extends Statement {
    public final Expression retval;

    public ReturnStatement(Expression retval) {
        this.retval = retval;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("return ");
        s.append(retval.toString());
        return s.toString();
    }
}

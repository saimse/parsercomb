package org.saimse.parsercomb.lang.c.statements;

import org.saimse.parsercomb.lang.c.expressions.Expression;

public class ExpressionStatement extends Statement {
    public final Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }
}

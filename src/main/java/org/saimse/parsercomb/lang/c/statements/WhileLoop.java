package org.saimse.parsercomb.lang.c.statements;

import org.saimse.parsercomb.lang.c.expressions.Expression;

public class WhileLoop extends Statement {
    public final boolean isDo;
    public final Expression condition;
    public final Statement body;

    public WhileLoop(boolean isDo, Expression condition, Statement body) {
        this.isDo = isDo;
        this.condition = condition;
        this.body = body;
    }
}

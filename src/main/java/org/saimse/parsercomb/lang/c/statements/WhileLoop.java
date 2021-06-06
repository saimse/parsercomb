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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (isDo) {
            s.append("do ");
            s.append(body.toString());
            s.append(" while (");
            s.append(condition.toString());
            s.append(");");
        }
        else {
            s.append("while (");
            s.append(condition.toString());
            s.append(" ");
            s.append(body.toString());
        }
        return s.toString();
    }
}

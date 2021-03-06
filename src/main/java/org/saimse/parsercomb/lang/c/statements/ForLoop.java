package org.saimse.parsercomb.lang.c.statements;

import org.saimse.parsercomb.lang.c.expressions.Expression;

public class ForLoop extends Statement {
    public final Expression init, condition, increment;
    public final Statement body;

    public ForLoop(Expression init, Expression condition, Expression increment, Statement body) {
        this.init = init;
        this.condition = condition;
        this.increment = increment;
        this.body = body;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("for ( ");
        s.append(init.toString());
        s.append("; ");
        s.append(condition.toString());
        s.append("; ");
        s.append(increment.toString());
        s.append(") ");
        s.append(body.toString());
        return s.toString();
    }
}

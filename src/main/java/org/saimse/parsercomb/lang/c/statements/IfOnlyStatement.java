package org.saimse.parsercomb.lang.c.statements;

import org.saimse.parsercomb.lang.c.expressions.Expression;

public class IfOnlyStatement extends Statement{
    Expression condition;
    Statement body;

    public IfOnlyStatement(Expression condition, Statement body) {
        this.condition = condition;
        this.body = body;
    }
}

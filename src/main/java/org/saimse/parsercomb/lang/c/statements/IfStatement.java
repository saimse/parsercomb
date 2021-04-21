package org.saimse.parsercomb.lang.c.statements;

import org.saimse.parsercomb.lang.c.expressions.Expression;

public class IfStatement extends Statement {
    public final IfOnlyStatement start;
    public final IfElseIfStatement[] elseIfStatements;
    public final IfElseStatement end;

    public IfStatement(IfOnlyStatement start, IfElseIfStatement[] elseIfStatements, IfElseStatement end) {
        this.start = start;
        this.elseIfStatements = elseIfStatements;
        this.end = end;
    }

    public static class IfElseIfStatement extends IfOnlyStatement {
        public IfElseIfStatement(Expression condition, Statement body) {
            super(condition, body);
        }
    }

    public static class IfElseStatement extends Statement {
        public final Statement body;

        public IfElseStatement(Statement body) {
            this.body = body;
        }
    }

    public static class IfOnlyStatement extends Statement{
        public final Expression condition;
        public final Statement body;

        public IfOnlyStatement(Expression condition, Statement body) {
            this.condition = condition;
            this.body = body;
        }
    }
}

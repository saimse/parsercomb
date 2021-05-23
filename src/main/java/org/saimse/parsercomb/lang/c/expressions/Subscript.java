package org.saimse.parsercomb.lang.c.expressions;

public class Subscript extends Expression {
    public final Expression l, r;

    public Subscript(Expression l, Expression r) {
        this.l = l;
        this.r = r;
    }
}

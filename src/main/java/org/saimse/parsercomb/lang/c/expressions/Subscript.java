package org.saimse.parsercomb.lang.c.expressions;

public class Subscript extends Expression {
    public final Expression l, r;

    public Subscript(Expression l, Expression r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(l.toString());
        s.append("[");
        s.append(r.toString());
        s.append("]");
        return s.toString();
    }
}

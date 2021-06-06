package org.saimse.parsercomb.lang.c.expressions;

public class MemberArrowAccess extends Expression {
    public final Expression lvalue;
    public final String identifier;

    public MemberArrowAccess(Expression lvalue, String identifier) {
        this.lvalue = lvalue;
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(lvalue.toString());
        s.append("->");
        s.append(identifier);
        return s.toString();
    }
}

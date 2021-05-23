package org.saimse.parsercomb.lang.c.expressions;

public class MemberAccess extends Expression {
    public final Expression lvalue;
    public final String identifier;

    public MemberAccess(Expression lvalue, String identifier) {
        this.lvalue = lvalue;
        this.identifier = identifier;
    }
}

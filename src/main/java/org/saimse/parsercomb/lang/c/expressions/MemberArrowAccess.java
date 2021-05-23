package org.saimse.parsercomb.lang.c.expressions;

public class MemberArrowAccess extends Expression {
    public final Expression lvalue;
    public final String identifier;

    public MemberArrowAccess(Expression lvalue, String identifier) {
        this.lvalue = lvalue;
        this.identifier = identifier;
    }
}

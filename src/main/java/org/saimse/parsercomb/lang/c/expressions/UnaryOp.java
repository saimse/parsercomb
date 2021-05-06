package org.saimse.parsercomb.lang.c.expressions;

public class UnaryOp extends Expression {
    public enum OP { PREFIX_DECR, PREFIX_INCR, POSTFIX_DECR, POSTFIX_INCR, DEREF, REF, PLUS, MIN, LOG_NOT, BIT_NOT, CAST }

    public final OP operator;
    public final Expression value;

    public UnaryOp(OP operator, Expression value) {
        this.operator = operator;
        this.value = value;
    }
}

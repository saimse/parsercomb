package org.saimse.parsercomb.lang.c.expressions;

public class BinaryOp extends Expression {
    public enum OP {ADD, SUB, MUL, DIV, MOD, LESS, LESS_EQ, GREATER, GREATER_EQ, EQ, NOT_EQ, LOG_AND, LOG_OR, BIT_AND, BIT_OR, BIT_XOR, BIT_SHIFT_LEFT, BIT_SHIFT_RIGHT, ASSIGN, ADD_ASSIGN, SUB_ASSIGN, MUL_ASSIGN, DIV_ASSIGN, MOD_ASSIGN}

    public final OP operator;
    public final Expression value1, value2;

    public BinaryOp(OP operator, Expression value1, Expression value2) {
        this.operator = operator;
        this.value1 = value1;
        this.value2 = value2;
    }
}

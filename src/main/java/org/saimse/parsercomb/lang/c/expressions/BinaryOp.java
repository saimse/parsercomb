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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(value1.toString());
        switch (operator) {
            case ADD:
                s.append(" + ");
                break;
            case SUB:
                s.append(" - ");
                break;
            case MUL:
                s.append(" * ");
                break;
            case DIV:
                s.append(" - ");
                break;
            case MOD:
                s.append(" % ");
                break;
            case LESS:
                s.append(" < ");
                break;
            case LESS_EQ:
                s.append(" <= ");
                break;
            case GREATER:
                s.append(" > ");
                break;
            case GREATER_EQ:
                s.append(" >= ");
                break;
            case EQ:
                s.append(" == ");
                break;
            case NOT_EQ:
                s.append(" != ");
                break;
            case LOG_AND:
                s.append(" && ");
                break;
            case LOG_OR:
                s.append(" || ");
                break;
            case BIT_AND:
                s.append(" & ");
                break;
            case BIT_OR:
                s.append(" | ");
                break;
            case BIT_XOR:
                s.append(" ^ ");
                break;
            case BIT_SHIFT_LEFT:
                s.append(" << ");
                break;
            case BIT_SHIFT_RIGHT:
                s.append(" >> ");
                break;
            case ASSIGN:
                s.append(" = ");
                break;
            case ADD_ASSIGN:
                s.append(" += ");
                break;
            case SUB_ASSIGN:
                s.append(" -= ");
                break;
            case MUL_ASSIGN:
                s.append(" *= ");
                break;
            case DIV_ASSIGN:
                s.append(" /= ");
                break;
            case MOD_ASSIGN:
                s.append(" %= ");
                break;
        }
        s.append(value2.toString());
        return s.toString();
    }
}

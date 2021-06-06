package org.saimse.parsercomb.lang.c.expressions;

public class UnaryOp extends Expression {
    public enum OP { PREFIX_DECR, PREFIX_INCR, POSTFIX_DECR, POSTFIX_INCR, DEREF, REF, PLUS, MIN, LOG_NOT, BIT_NOT, CAST }

    public final OP operator;
    public final Expression value;

    public UnaryOp(OP operator, Expression value) {
        this.operator = operator;
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        switch (operator) {
            case PREFIX_DECR:
                s.append("--");
                s.append(value.toString());
                break;
            case PREFIX_INCR:
                s.append("++");
                s.append(value.toString());
                break;
            case POSTFIX_DECR:
                s.append(value.toString());
                s.append("--");
                break;
            case POSTFIX_INCR:
                s.append(value.toString());
                s.append("++");
                break;
            case DEREF:
                s.append("*");
                s.append(value.toString());
                break;
            case REF:
                s.append("&");
                s.append(value.toString());
                break;
            case PLUS:
                s.append("+");
                s.append(value.toString());
                break;
            case MIN:
                s.append("-");
                s.append(value.toString());
                break;
            case LOG_NOT:
                s.append("!");
                s.append(value.toString());
                break;
            case BIT_NOT:
                s.append("~");
                s.append(value.toString());
        }
        return s.toString();
    }
}

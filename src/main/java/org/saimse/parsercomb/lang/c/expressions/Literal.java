package org.saimse.parsercomb.lang.c.expressions;

public class Literal extends Expression {
    private final String str;
    private final Integer integer;
    private final Double fp;
    private final Character ch;

    enum Type { STR, INT, FP, CHAR };

    public final Type type;

    public Literal(String str) {
        this.type = Type.STR;
        this.str = str;
        this.integer = null;
        this.fp = null;
        this.ch = null;
    }

    public Literal(Integer integer) {
        this.type = Type.INT;
        this.integer = integer;
        this.str = null;
        this.fp = null;
        this.ch = null;
    }

    public Literal(Double fp) {
        this.type = Type.FP;
        this.fp = fp;
        this.integer = null;
        this.str = null;
        this.ch = null;
    }

    public Literal(Character ch) {
        this.type = Type.CHAR;
        this.ch = ch;
        this.fp = null;
        this.integer = null;
        this.str = null;
    }
}

package org.saimse.parsercomb.lang.c.expressions;

import org.saimse.parsercomb.lang.c.Type;

public class CastOp extends UnaryOp {
    public final Type cast_t;

    public CastOp(Type cast_t, Expression value) {
        super(OP.CAST, value);
        this.cast_t = cast_t;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("(");
        s.append(cast_t.getFullyQualifiedTypeName());
        s.append(")");
        s.append(value.toString());
        return s.toString();
    }
}

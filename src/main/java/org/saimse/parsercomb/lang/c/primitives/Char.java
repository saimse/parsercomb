package org.saimse.parsercomb.lang.c.primitives;

public class Char extends PrimitiveType {
    public Char(boolean const_t, boolean volatile_t, boolean is_unsigned, LONG_T long_t) {
        super("char", const_t, volatile_t, is_unsigned, long_t);
    }
}

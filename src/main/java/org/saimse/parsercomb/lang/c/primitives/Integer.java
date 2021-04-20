package org.saimse.parsercomb.lang.c.primitives;

public class Integer extends PrimitiveType {
    public Integer(boolean const_t, boolean volatile_t, boolean is_unsigned, LONG_T long_t) {
        super("int", const_t, volatile_t, is_unsigned, long_t);
    }
}

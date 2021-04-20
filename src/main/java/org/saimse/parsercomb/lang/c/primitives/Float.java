package org.saimse.parsercomb.lang.c.primitives;

public class Float extends PrimitiveType {
    public Float(boolean const_t, boolean volatile_t, boolean is_unsigned, LONG_T long_t) {
        super("float", const_t, volatile_t, is_unsigned, long_t);
    }
}

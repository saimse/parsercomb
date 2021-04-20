package org.saimse.parsercomb.lang.c.primitives;

public class Double extends PrimitiveType {
    public Double(boolean const_t, boolean volatile_t, boolean is_unsigned, LONG_T long_t) {
        super("double", const_t, volatile_t, is_unsigned, long_t);
    }
}

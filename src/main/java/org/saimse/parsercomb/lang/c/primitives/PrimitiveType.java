package org.saimse.parsercomb.lang.c.primitives;

import org.saimse.parsercomb.lang.c.Type;

public abstract class PrimitiveType extends Type {
    public enum LONG_T { NO_LONG_T, LONG_T, LONG_LONG_T };

    public final boolean is_unsigned;
    public final LONG_T long_t;

    @Override
    public String getFullyQualifiedTypeName() {
        StringBuilder stringBuilder = new StringBuilder();
        if(is_unsigned) stringBuilder.append("unsigned ");
        switch (long_t) {
            case LONG_LONG_T: stringBuilder.append("long ");
            case LONG_T: stringBuilder.append("long ");
        }
        stringBuilder.append(name);
        return stringBuilder.toString();
    }

    public PrimitiveType(String name, boolean const_t, boolean volatile_t, boolean is_unsigned, LONG_T long_t) {
        super(name, const_t, volatile_t);
        this.is_unsigned = is_unsigned;
        this.long_t = long_t;
    }
}

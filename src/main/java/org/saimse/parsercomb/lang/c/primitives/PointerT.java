package org.saimse.parsercomb.lang.c.primitives;

import org.saimse.parsercomb.lang.c.Type;

public class PointerT extends Type {
    public final Type ptr_t;

    @Override
    public String getFullyQualifiedTypeName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ptr_t.getFullyQualifiedTypeName());
        stringBuilder.append("* ");
        if(volatile_t) stringBuilder.append("volatile");
        if(const_t) stringBuilder.append("const");
        return stringBuilder.toString();
    }

    public PointerT(Type ptr_t, boolean const_t, boolean volatile_t) {
        super(ptr_t.name, const_t, volatile_t);
        this.ptr_t = ptr_t;
    }
}

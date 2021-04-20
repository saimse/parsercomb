package org.saimse.parsercomb.lang.c;

public abstract class Type {
    public final String name;
    public final boolean const_t, volatile_t;

    public String getFullyQualifiedTypeName() {
        StringBuilder stringBuilder = new StringBuilder();
        if(volatile_t) stringBuilder.append("volatile ");
        if(const_t) stringBuilder.append("const ");
        stringBuilder.append(name);
        return stringBuilder.toString();
    }

    public Type(String name, boolean const_t, boolean volatile_t) {
        this.name = name;
        this.const_t = const_t;
        this.volatile_t = volatile_t;
    }
}

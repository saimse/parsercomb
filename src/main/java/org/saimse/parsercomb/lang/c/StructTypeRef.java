package org.saimse.parsercomb.lang.c;

public class StructTypeRef extends Type {
    @Override
    public String getFullyQualifiedTypeName() {
        StringBuilder stringBuilder = new StringBuilder();
        if(volatile_t) stringBuilder.append("volatile ");
        if(const_t) stringBuilder.append("const ");
        stringBuilder.append("struct ");
        stringBuilder.append(name);
        return stringBuilder.toString();
    }

    public StructTypeRef(String name, boolean const_t, boolean volatile_t) {
        super(name, const_t, volatile_t);
    }
}

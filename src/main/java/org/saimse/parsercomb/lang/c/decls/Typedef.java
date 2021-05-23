package org.saimse.parsercomb.lang.c.decls;

import org.saimse.parsercomb.lang.c.Type;

public class Typedef extends Declaration {
    public Typedef(Type from, String to) {
        this.from = from;
        this.fromStruct = null;
        this.to = to;
    }

    public Typedef(StructDecl fromStruct, String to) {
        this.fromStruct = fromStruct;
        this.from = null;
        this.to = to;
    }

    public final Type from;
    public final StructDecl fromStruct;
    public final String to;
}

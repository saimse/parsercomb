package org.saimse.parsercomb.lang.c.statements;

import org.saimse.parsercomb.lang.c.Type;

import java.util.List;

public class StructDecl extends Statement {
    public static class StructFieldDecl {
        Type type; String name;

        public StructFieldDecl(Type type, String name) {
            this.type = type;
            this.name = name;
        }
    }

    public final String name;
    public final List<StructFieldDecl> fields;

    public StructDecl(String name, List<StructFieldDecl> fields) {
        this.name = name;
        this.fields = fields;
    }
}

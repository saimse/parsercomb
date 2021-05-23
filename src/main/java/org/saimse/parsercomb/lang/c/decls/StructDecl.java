package org.saimse.parsercomb.lang.c.decls;

import org.saimse.parsercomb.lang.c.Type;
import org.saimse.parsercomb.lang.c.statements.Statement;

import java.util.List;

public class StructDecl extends Declaration {
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

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

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder(type.getFullyQualifiedTypeName());
            s.append(" ");
            s.append(name);
            return s.toString();
        }
    }

    public final String name;
    public final List<StructFieldDecl> fields;

    public StructDecl(String name, List<StructFieldDecl> fields) {
        this.name = name;
        this.fields = fields;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("struct ");
        s.append(name);
        s.append(" {");
        s.append('\n');
        for (StructFieldDecl sfd : fields) {
            s.append('\t');
            s.append(sfd.toString());
            s.append(';');
            s.append('\n');
        }
        s.append("};");
        return s.toString();
    }
}

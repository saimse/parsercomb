package org.saimse.parsercomb.lang.c.decls;

import org.saimse.parsercomb.lang.c.Type;
import org.saimse.parsercomb.lang.c.statements.Statement;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

// Not a real statement; it has statement-like qualities,
// however it cannot appear (in C) where other statements can appear
public class FunDecl extends Declaration {
    public final Type ret_t;
    public final String name;
    public final List<Pair<Type, String>> params;
    public final Statement body;

    public FunDecl(Type ret_t, String name, List<Pair<Type, String>> params, Statement body) {
        this.ret_t = ret_t;
        this.name = name;
        this.params = params;
        this.body = body;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(ret_t.getFullyQualifiedTypeName());
        s.append(" ");
        s.append(name);
        s.append("(");
        for (Pair<Type, String> currPair : params) {
            s.append(currPair.a.getFullyQualifiedTypeName());
            s.append(" ");
            s.append(currPair.b);
            s.append(", ");
        }
        s.delete((s.length() - 2), s.length());
        s.append(")");
        s.append(body.toString());
        return s.toString();
    }
}

package org.saimse.parsercomb.lang.c.statements;

import org.saimse.parsercomb.lang.c.Type;
import org.saimse.parsercomb.util.Pair;

import java.util.List;

// Not a real statement; it has statement-like qualities,
// however it cannot appear (in C) where other statements can appear
public class FunDecl {
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
}

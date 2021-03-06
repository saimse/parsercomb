package org.saimse.parsercomb.lang.c.decls;

import org.saimse.parsercomb.lang.c.Type;
import org.saimse.parsercomb.lang.c.expressions.Expression;

public class VarDecl extends Declaration {
    public final Type type;
    public final String identifier;
    public final Expression expression;

    public VarDecl(Type type, String identifier, Expression expression) {
        this.type = type;
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(type.getFullyQualifiedTypeName());
        s.append(" ");
        s.append(identifier);
        if (expression != null) {
            s.append(" = ");
            s.append(expression.toString());
        }
        return s.toString();
    }
}

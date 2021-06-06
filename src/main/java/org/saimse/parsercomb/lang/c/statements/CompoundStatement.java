package org.saimse.parsercomb.lang.c.statements;

import java.util.List;

public class CompoundStatement extends Statement {
    public final List<Statement> statements;

    public CompoundStatement(List<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{" + '\n');
        for (Statement cs : statements) {
            s.append("\t");
            s.append(cs.toString());
            s.append(';');
        }
        s.append("}");
        return s.toString();
    }
}

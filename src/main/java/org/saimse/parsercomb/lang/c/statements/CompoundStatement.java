package org.saimse.parsercomb.lang.c.statements;

import java.util.List;

public class CompoundStatement extends Statement {
    public final List<Statement> statements;

    public CompoundStatement(List<Statement> statements) {
        this.statements = statements;
    }
}

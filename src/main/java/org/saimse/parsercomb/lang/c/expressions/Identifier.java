package org.saimse.parsercomb.lang.c.expressions;

public class Identifier extends Expression {
    public final String identifier;

    public Identifier(String identifier) {
        this.identifier = identifier;
    }
}

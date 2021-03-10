package org.saimse.parsercomb;

import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public interface GenericParser<P, A> {
    public Pair<P, A> parse(P input) throws BadParseException;
}

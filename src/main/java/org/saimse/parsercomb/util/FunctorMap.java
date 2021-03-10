package org.saimse.parsercomb.util;

import org.saimse.parsercomb.Parser;

public class FunctorMap {
    public interface Coerce<B, A> {
        public B coerce(A value);
    }
    public static <B, A> Parser<B> fmap(Coerce<B, A> f, Parser<A> fa) {
        return input -> {
            Pair<String, A> result = fa.parse(input);
            return new Pair<>(result.a, f.coerce(result.b));
        };
    };
}

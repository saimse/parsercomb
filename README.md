# parsercomb

A tiny self-contained parser combinator library in Java 8:

```java
import org.saimse.parsercomb.*;
import org.saimse.parsercomb.util.*;
import org.saimse.parsercomb.combinators.Or;

class MyParser implements Parser<MyObject> {
    @Override
    public Pair<String, MyObject> parse(String input) throws BadParseException {
        throw new BadParseException();
    }
}

class Main {
    public static void main(String[] args) throws BadParseException {
        Pair<String, Character> result =
                new Or<>(new CharParser('_'), new ParseBy(Character::isLowercase))
                        .parse("_ABC");
        // result.a is the unparsed remains
        // result.b is the parsed result
    }
}
```
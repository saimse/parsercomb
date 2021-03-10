package org.saimse.parsercomb;

public class CharParser extends ParseBy {
    public CharParser(Character character) {
        super(c -> c.equals(character));
    }
}

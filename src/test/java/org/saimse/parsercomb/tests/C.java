package org.saimse.parsercomb.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import org.saimse.parsercomb.lang.c.parsers.StructDeclParser;
import org.saimse.parsercomb.lang.c.parsers.TypeParser;
import org.saimse.parsercomb.lang.c.statements.Statement;
import org.saimse.parsercomb.util.*;

public class C {
    @Test
    void typeParser() {
        assertDoesNotThrow(() -> new TypeParser().parse("const char*"));
        assertDoesNotThrow(() -> new TypeParser().parse("const constchar**"));
        assertDoesNotThrow(() -> new TypeParser().parse("struct constchar* volatile **"));
        assertDoesNotThrow(() -> new TypeParser().parse("struct mystruct_t"));
        assertDoesNotThrow(() -> new TypeParser().parse("unsigned long long int"));
        assertDoesNotThrow(() -> new TypeParser().parse("unsigned long*"));
    }

    @Test
    void structDecl() throws BadParseException {
        assertDoesNotThrow(() -> new StructDeclParser().parse("struct {unsigned long*test; size_t len;  };"));
        assertDoesNotThrow(() -> new StructDeclParser().parse("struct ono_moje {unsigned long*test; size_t len;  };"));
    }
}

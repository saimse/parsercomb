package org.saimse.parsercomb.tests;

import org.junit.jupiter.api.Test;
import org.saimse.parsercomb.lang.c.decls.Declaration;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.parsers.*;
import org.saimse.parsercomb.lang.c.parsers.operators.binary.AssignmentOperator;
import org.saimse.parsercomb.lang.c.parsers.operators.binary.EqualsParser;
import org.saimse.parsercomb.lang.c.parsers.operators.binary.MemberArrowAccessParser;
import org.saimse.parsercomb.lang.c.parsers.operators.unary.DerefParser;
import org.saimse.parsercomb.lang.c.statements.Statement;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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

    @Test
    void ifc() throws IOException, BadParseException {
//        Pair<String, Statement> c = new IfStatementParser().parse("if(var == NULL) return NULL;");
        Pair<String, Expression> c = new EqualsParser().parse("data->data == NULL;");
        Pair<String, Statement> c2 = new IfStatementParser().parse("if(data->data == NULL) {\n" +
                "\t\tfree(data);\n" +
                "\t\treturn NULL;\n" +
                "\t}");
        System.out.println(c2.toString());
    }

    @Test
    void forc() throws BadParseException {
        Pair<String, Expression> parse3 = new ExpressionParser().parse("data->data + i");
        Pair<String, Expression> parse2 = new ExpressionParser().parse("data->data[i]");
        Pair<String, Expression> parse1 = new ExpressionParser().parse("data->data[i] = buf[i]");
        Pair<String, Statement> parse = new ForLoopParser().parse("for(i = 0; i < data->_data_sz; i++)\n" +
                "\t\tdata->data[i] = buf[i];");
        System.out.println(parse);
    }

    @Test
    void memberarr() throws IOException, BadParseException {
        Pair<String, Expression> c = new AssignmentOperator().parse("data->data = malloc(data->_data_sz);");
        Pair<String, Expression> c2 = new AssignmentOperator().parse("data->_data_sz = len - 4;");
        System.out.println(c.toString());
    }

    @Test
    void c() throws IOException, BadParseException {
        String input = new String(Files.readAllBytes(Paths.get("test.c")));
        Pair<String, List<Declaration>> c = new CParser().parse(input);
        for (Declaration declaration : c.b) {
            System.out.println(declaration);
        }
    }
}

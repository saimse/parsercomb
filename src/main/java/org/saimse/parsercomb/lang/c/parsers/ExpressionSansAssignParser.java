package org.saimse.parsercomb.lang.c.parsers;

import org.saimse.parsercomb.Parser;
import org.saimse.parsercomb.combinators.Or;
import org.saimse.parsercomb.lang.c.expressions.Expression;
import org.saimse.parsercomb.lang.c.parsers.operators.binary.*;
import org.saimse.parsercomb.lang.c.parsers.operators.unary.*;
import org.saimse.parsercomb.util.BadParseException;
import org.saimse.parsercomb.util.Pair;

public class ExpressionSansAssignParser implements Parser<Expression> {
    public Pair<String, Expression> parse(String input) throws BadParseException {
        Parser<Expression> expr = new Or<>(
                new SuffixIncrParser(), new SuffixDecrParser(), new FunCallParser(), new SubscriptParser(),
                /* arr[], ., -> */ new MemberAccessParser(), new MemberArrowAccessParser(),
//                fmap(org.saimse.parsercomb.lang.c.expressions.Identifier::new, new org.saimse.parsercomb.lang.c.parsers.Identifier()),
                new PrefixIncrParser(), new PrefixDecrParser(), new UnaryPlusParser(), new UnaryMinusParser(), new LogicalNotParser(), new BitwiseNotParser(), new CastParser(), new DerefParser(), new RefParser(),
                new MultiplicationParser(), new DivisionParser(), new ModuloParser(),
                new AdditionParser(), new SubstractionParser(),
                new BitShiftLeftParser(), new BitShiftRightParser(),
                new LessParser(), new LessEqualParser(), new GreaterParser(), new GreaterEqualParser(),
                new EqualsParser(), new NotEqualParser(),
                new BitAndParser(),
                new BitXorParser(),
                new BitOrParser(),
                new LogicalAndParser(),
                new LogicalOrParser()
                /* ?: ternary */);
        return expr.parse(input);
    }
}
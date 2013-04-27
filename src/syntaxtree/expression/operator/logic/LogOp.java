package syntaxtree.expression.operator.logic;

import syntaxtree.expression.operator.Operator;

/**
 * User: Havard
 * Date: 13.03.13
 * Time: 11:29
 */
public class LogOp extends Operator {

    public LogOp(String val) {
        super(val);
        super.type = "bool";
    }

    @Override
    public String printAst() {
        return "LOG_OP " + super.printAst();
    }
}

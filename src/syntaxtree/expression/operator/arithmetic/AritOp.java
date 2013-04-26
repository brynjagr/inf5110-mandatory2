package syntaxtree.expression.operator.arithmetic;

import syntaxtree.expression.operator.Operator;

/**
 * User: Havard
 * Date: 13.03.13
 * Time: 11:21
 */
public class AritOp extends Operator {

    public AritOp(String val) {
        super(val);
    }

    @Override
    public String printAst() {
        return "ARIT_OP " + super.printAst();
    }
}

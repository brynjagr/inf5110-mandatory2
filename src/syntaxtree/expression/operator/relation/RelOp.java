package syntaxtree.expression.operator.relation;

import syntaxtree.expression.operator.Operator;

/**
 * User: Havard
 * Date: 13.03.13
 * Time: 11:37
 */
public class RelOp extends Operator {

    public RelOp(String val) {
        super(val);
    }

    @Override
    public String printAst() {
        return "REL_OP " + super.printAst();
    }
}

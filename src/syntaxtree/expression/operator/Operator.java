package syntaxtree.expression.operator;

/**
 * User: Havard
 * Date: 09.03.13
 * Time: 18:22
 */
public class Operator {

    protected String val;
    protected String type;

    public Operator(String val) {
        this.val = val;
        this.type = "";
    }

    public String printAst() {
        return val + "\n";
    }

    public String getOperandType() {
        return this.type;
    }
}

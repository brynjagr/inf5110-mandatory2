package syntaxtree.expression.operator;

/**
 * User: Havard
 * Date: 09.03.13
 * Time: 18:22
 */
public class Operator {

    protected String val;

    public Operator(String val) {
        this.val = val;
    }

    public String printAst() {
        return val + "\n";
    }
}

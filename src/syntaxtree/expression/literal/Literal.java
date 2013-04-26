package syntaxtree.expression.literal;

import syntaxtree.expression.Expression;

public abstract class Literal extends Expression {

    protected String val;

    public Literal(String val) {
	    this.val = val;
    }
    
    public abstract String printAst();
}

package syntaxtree.statement;

import syntaxtree.SyntaxUnit;
import bytecode.CodeProcedure;

/**
 * Created with IntelliJ IDEA.
 * User: Havard
 * Date: 10.03.13
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
public abstract class Stmt extends SyntaxUnit{

    public String getName() {
        return "";
    }

    public void generateInnerCode(CodeProcedure proc) {
	throw new UnsupportedOperationException(this + ": Need to implement this method...");
    }
}

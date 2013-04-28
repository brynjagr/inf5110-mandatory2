package syntaxtree.expression;

import error.NotAVariableError;
import error.VariableNotDeclaredError;
import syntaxtree.SyntaxUnit;
import syntaxtree.expression.variable.Variable;
import bytecode.CodeProcedure;

/**
 * User: Havard
 * Date: 09.03.13
 * Time: 18:14
 */
public abstract class Expression extends SyntaxUnit {

    protected String type;

    public abstract String printAst();

    public abstract void checkWhetherVariable() throws NotAVariableError;

    public String getType() throws VariableNotDeclaredError {
        return type;
    }

    public Variable getVariable() throws NotAVariableError {
        throw new NotAVariableError(this.type);
    }

    public String getName() {
	throw new UnsupportedOperationException();
    }

    public void generateInnerCode(CodeProcedure proc) {
	throw new UnsupportedOperationException(this + ": Need to implement this method...");
    }

    public void generateStoreCode(CodeProcedure proc) {
	throw new UnsupportedOperationException();
    }
}

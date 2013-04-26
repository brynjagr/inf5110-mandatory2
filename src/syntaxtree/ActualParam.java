package syntaxtree;

import bytecode.CodeFile;
import error.*;
import symboltable.SymbolTable;
import syntaxtree.expression.Expression;

/**
 * Created with IntelliJ IDEA.
 * User: Havard
 * Date: 10.03.13
 * Time: 16:33
 */
public class ActualParam extends SyntaxUnit {

    Expression e;

    public ActualParam(Expression e) {
        this.e = e;
    }

    public String printAst() {
        String str = Indent.level + "(ACTUAL_PARAM " + e.printAst().trim() + ")\n";
        return str;
    }

    @Override
    public void checkCode(SymbolTable symbolTable) throws FunctionNotDeclaredError, ClassNotFoundError, VariableAlreadyDeclaredError, MissingReturnStmtError, TypeNotExistError, MainNotFoundError, VariableNotDeclaredError, TypeNotSameError, NotAClassError, ProcedureUsedInExpressionError, NotAVariableError, MainMustBeProcedureError {
        e.checkCode(symbolTable);
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }

    public String getType() throws VariableNotDeclaredError {
        return e.getType();
    }
}

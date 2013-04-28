package syntaxtree.expression;

import bytecode.CodeFile;
import error.*;
import symboltable.SymbolTable;
import syntaxtree.declaration.FunctionDecl;
import syntaxtree.statement.Stmt;

/**
 * User: Havard
 * Date: 10.03.13
 * Time: 16:48
 */
public class CallExpression extends Expression {

    private Stmt callStatement;

    public CallExpression(Stmt callStatement) {
        this.callStatement = callStatement;
    }

    @Override
    public String printAst() {
        return callStatement.printAst();
    }

    @Override
    public void checkWhetherVariable() throws NotAVariableError {
        throw new NotAVariableError(this.getClass().getName());
    }

    @Override
    public void checkCode(SymbolTable symbolTable) throws FunctionNotDeclaredError, ClassNotFoundError, VariableAlreadyDeclaredError, MissingReturnStmtError, TypeNotExistError, MainNotFoundError, VariableNotDeclaredError, TypeNotSameError, NotAClassError, ProcedureUsedInExpressionError, NotAVariableError, MainMustBeProcedureError, FunctionMustReturnTypeError, WrongNumberOfActualParametersError, ProcedureCantReturnValueError, NotAFunctionError, MainCantTakeParametersError, NotCallableError {

        callStatement.checkCode(symbolTable);

        FunctionDecl func = (FunctionDecl) symbolTable.getDecl(callStatement.getName());

        String funcType = func.getType();

        /*If the function type is null we know that the function is a procedure*/
        if (funcType == null) {
            this.error = true;
            throw new ProcedureUsedInExpressionError(callStatement.getName());
        }

        super.type = funcType;
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }
}

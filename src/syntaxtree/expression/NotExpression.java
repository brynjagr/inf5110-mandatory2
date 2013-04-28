package syntaxtree.expression;

import bytecode.CodeFile;
import error.*;
import symboltable.SymbolTable;
import syntaxtree.Indent;

/**
 * User: Havard
 * Date: 10.03.13
 * Time: 15:12
 */
public class NotExpression extends Expression {

    private Expression e;

    public NotExpression(Expression e) {
        this.e = e;
    }

    @Override
    public String printAst() {
        return Indent.level + "(NOT " + e.printAst().trim() + ")\n";
    }

    @Override
    public void checkWhetherVariable() throws NotAVariableError {
        throw new NotAVariableError(this.getClass().getName());
    }

    @Override
    public void checkCode(SymbolTable symbolTable) throws TypeNotSameError, VariableNotDeclaredError, MissingReturnStmtError, FunctionNotDeclaredError, VariableAlreadyDeclaredError, ClassNotFoundError, TypeNotExistError, MainNotFoundError, NotAClassError, ProcedureUsedInExpressionError, NotAVariableError, MainMustBeProcedureError, FunctionMustReturnTypeError, WrongNumberOfActualParametersError, ProcedureCantReturnValueError, NotAFunctionError, MainCantTakeParametersError, NotCallableError {
        e.checkCode(symbolTable);
        checkSameType(e.getType(), "bool");
        super.type = "bool";

    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }
}

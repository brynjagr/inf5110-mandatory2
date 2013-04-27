package syntaxtree.statement;

import bytecode.CodeFile;
import error.*;
import symboltable.SymbolTable;
import syntaxtree.Indent;
import syntaxtree.expression.Expression;

/**
 * Created with IntelliJ IDEA.
 * User: Havard
 * Date: 10.03.13
 * Time: 12:49
 */
public class ReturnStmt extends Stmt {

    private Expression e;

    public ReturnStmt(Expression e) {
        this.e = e;
    }

    @Override
    public String printAst() {
        String s = Indent.level + "(RETURN_STMT ";

        return Indent.level + "(RETURN_STMT" + (e.printAst().isEmpty() ?  "" : " " + e.printAst().trim()) + ")\n";
      /*  System.out.println("FIRST: " + s[0]);
        for (String ss : s) {
            System.out.println(ss);
        }

        return Indent.level

        return Indent.level + "(RETURN_STMT)";           */
    }

    @Override
    public void checkCode(SymbolTable symbolTable) throws VariableNotDeclaredError, FunctionNotDeclaredError, TypeNotSameError, MissingReturnStmtError, VariableAlreadyDeclaredError, TypeNotExistError, ClassNotFoundError, MainNotFoundError, NotAClassError, ProcedureUsedInExpressionError, NotAVariableError, MainMustBeProcedureError, MainCantTakeParameters, FunctionMustReturnTypeError, WrongNumberOfActualParametersError, ProcedureCantReturnValueError, NotAFunctionError {
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

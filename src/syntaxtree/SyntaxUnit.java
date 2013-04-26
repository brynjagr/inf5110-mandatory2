package syntaxtree;

import bytecode.CodeFile;
import error.*;
import symboltable.SymbolTable;

public abstract class SyntaxUnit {

    String val;
    protected boolean error = false;
    
    public SyntaxUnit(String val) {
	    this.val = val;
    }

    public SyntaxUnit() {}

    public abstract String printAst();

    public abstract void checkCode(SymbolTable symbolTable) throws VariableNotDeclaredError, TypeNotSameError, FunctionNotDeclaredError, VariableAlreadyDeclaredError, MissingReturnStmtError, TypeNotExistError, ClassNotFoundError, MainNotFoundError, NotAClassError, ProcedureUsedInExpressionError, NotAVariableError, MainMustBeProcedureError;

    public abstract void generateCode(CodeFile codeFile);

    public void checkSameType(String t1, String t2) throws TypeNotSameError, TypeNotExistError {

        if (!SymbolTable.containsType(t1)) {
            throw new TypeNotExistError(t1);
        }

        if (!SymbolTable.containsType(t2)) {
            throw new TypeNotExistError(t2);
        }

        if (!t1.equals(t2)) {
            throw new TypeNotSameError(t1, t2);
        }
    }

    public boolean getError()  {
        return error;
    }
}

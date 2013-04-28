package syntaxtree;

import bytecode.CodeFile;
import bytecode.CodeProcedure;
import error.*;
import symboltable.SymbolTable;
import bytecode.*;
import bytecode.type.*;

public abstract class SyntaxUnit {

    String val;
    protected boolean error = false;
    
    public SyntaxUnit(String val) {
	    this.val = val;
    }

    public SyntaxUnit() {}

    public abstract String printAst();

    public abstract void checkCode(SymbolTable symbolTable) throws VariableNotDeclaredError, TypeNotSameError, FunctionNotDeclaredError, VariableAlreadyDeclaredError, MissingReturnStmtError, TypeNotExistError, ClassNotFoundError, MainNotFoundError, NotAClassError, ProcedureUsedInExpressionError, NotAVariableError, MainMustBeProcedureError, FunctionMustReturnTypeError, WrongNumberOfActualParametersError, ProcedureCantReturnValueError, NotAFunctionError, MainCantTakeParametersError, NotCallableError;

    public abstract void generateCode(CodeFile codeFile);
    public abstract void generateInnerCode(CodeProcedure proc);

    public void checkSameType(String t1, String t2) throws TypeNotSameError, TypeNotExistError {



        if (!SymbolTable.containsType(t1)) {
            throw new TypeNotExistError(t1);
        }

        if (!SymbolTable.containsType(t2)) {
            throw new TypeNotExistError(t2);
        }

        /*int can be float and string can be null*/
        if (!t1.equals(t2)) {
            if (!(t1.equals("float") && t2.equals("int"))) {
                if (!(t1.equals("string") && t2.equals("null"))) {
                    throw new TypeNotSameError(t1, t2);
                }
            }
        }
    }

    public boolean getError()  {
        return error;
    }

    public static CodeType getCodeType(String type, CodeFile codeFile) {

	switch (type) {
	case "int":
	    return IntType.TYPE;
	case "float":
	    return FloatType.TYPE;
	case "bool":
	    return BoolType.TYPE;
	case "string":
	    return StringType.TYPE;
	case "null":
	    throw new UnsupportedOperationException("Invalid Type?");
	case "void":
	    return VoidType.TYPE;
	default:
	    return new RefType(codeFile.structNumber(type));
	}
    }
}

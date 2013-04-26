package syntaxtree.statement;

import bytecode.CodeFile;
import error.*;
import symboltable.SymbolTable;
import syntaxtree.ActualParam;
import syntaxtree.Indent;
import syntaxtree.LibraryFunction;
import syntaxtree.declaration.Decl;
import syntaxtree.declaration.ParamDecl;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Havard
 * Date: 10.03.13
 * Time: 16:24
 */
public class CallStmt extends Stmt {

    String name;
    List<ActualParam> actualParamList;

    public CallStmt(List<ActualParam> actualParamList, String name) {
        this.actualParamList = actualParamList;
        this.name = name;
    }

    @Override
    public String printAst() {
        String str = Indent.level + "(CALL_STMT (NAME " + name + ")";

        if (!actualParamList.isEmpty()) {
            str += "\n";
            Indent.inc();
            for (ActualParam ap : actualParamList) {
                str += ap.printAst();
            }
            Indent.dec();
            str += Indent.level;
        }

        str += ")\n";
        return str;
    }

    @Override
    public void checkCode(SymbolTable symbolTable) throws FunctionNotDeclaredError, MissingReturnStmtError, VariableAlreadyDeclaredError, VariableNotDeclaredError, NotAVariableError, TypeNotSameError, MainMustBeProcedureError, ProcedureUsedInExpressionError, ClassNotFoundError, TypeNotExistError, MainNotFoundError, NotAClassError, MainCantTakeParameters, FunctionMustReturnTypeError, WrongNumberOfActualParametersError, ProcedureCantReturnValueError {
        Decl funcDecl = symbolTable.getDecl(this.name);
        if (funcDecl == null) {
            throw new FunctionNotDeclaredError(this.name);
        }


        checkParamenters(funcDecl.getParamDeclList(), symbolTable, funcDecl instanceof LibraryFunction);

    }

    private void checkParamenters(List<ParamDecl> paramDeclList, SymbolTable sym, boolean libraryFunction) throws WrongNumberOfActualParametersError, TypeNotExistError, TypeNotSameError, FunctionNotDeclaredError, VariableAlreadyDeclaredError, NotAVariableError, MainNotFoundError, NotAClassError, ProcedureUsedInExpressionError, ClassNotFoundError, MainMustBeProcedureError, MissingReturnStmtError, VariableNotDeclaredError, MainCantTakeParameters, FunctionMustReturnTypeError, ProcedureCantReturnValueError {

        for (int i = 0; i < paramDeclList.size(); ++i) {
            try {
                ActualParam ap = actualParamList.get(i);
                ap.checkCode(sym);
                String actualParamType = ap.getType();
                String funcParamType = paramDeclList.get(i).getType();

                checkSameType(actualParamType, funcParamType);

                if (!libraryFunction) {
                    paramDeclList.get(i).setValue(ap);
                }

            } catch (IndexOutOfBoundsException x) {
                throw new WrongNumberOfActualParametersError("few");
            }
        }

        if (actualParamList.size() != paramDeclList.size()) {
            throw new WrongNumberOfActualParametersError("many");
        }
    }

    /**
     * Does the same as the overridden method in SyntaxUnit except that it won't cast a TypeNotSameError
     * if t1 is a float and t2 is a integer.
     * @param t1
     * @param t2
     * @throws TypeNotSameError
     * @throws TypeNotExistError
     */
    @Override
    public void checkSameType(String t1, String t2) throws TypeNotSameError, TypeNotExistError {

        if (!SymbolTable.containsType(t1)) {
            throw new TypeNotExistError(t1);
        }

        if (!SymbolTable.containsType(t2)) {
            throw new TypeNotExistError(t2);
        }

        /*If the types don't match (float can be int and string can be null)*/
        if (!t1.equals(t2)) {
            if (!(t1.equals("int") && t2.equals("float"))) {
                if ((!(t1.equals("null") && t2.equals("string")))) {
                    throw new TypeNotSameError(t1, t2);
                }
            }
        }
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();

    }

    @Override
    public String getName() {
        return this.name;
    }

}

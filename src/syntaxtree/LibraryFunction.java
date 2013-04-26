package syntaxtree;

import bytecode.CodeFile;
import error.*;
import symboltable.SymbolTable;
import syntaxtree.declaration.ParamDecl;
import syntaxtree.declaration.ProcedureDecl;

import java.util.List;

/**
 * User: brynjagr
 * Date: 4/23/13
 * Time: 3:51 PM
 */
public class LibraryFunction extends ProcedureDecl {

    public LibraryFunction(String name, List<ParamDecl> l) {
            super(name, l, null, null);
    }

    @Override
    public String printAst() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void checkCode(SymbolTable symbolTable) throws VariableNotDeclaredError, TypeNotSameError, FunctionNotDeclaredError, VariableAlreadyDeclaredError, MissingReturnStmtError, TypeNotExistError, ClassNotFoundError, MainNotFoundError {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int checkWhetherProc() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int checkWhetherFunction() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ParamDecl> getParamDeclList() {
        return super.paramDeclList;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

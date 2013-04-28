package syntaxtree;

import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.type.CodeType;

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

    public LibraryFunction(String name, List<ParamDecl> l, String type) {
            super(name, l, null, null);
	    super.type = type;
    }

    @Override
    public String printAst() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void checkCode(SymbolTable symbolTable) throws VariableNotDeclaredError, TypeNotSameError, FunctionNotDeclaredError, VariableAlreadyDeclaredError, MissingReturnStmtError, TypeNotExistError, ClassNotFoundError, MainNotFoundError {
	// 
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        
	codeFile.addProcedure(getName());
	CodeType type = SyntaxUnit.getCodeType(getType(), codeFile);
	CodeProcedure proc = new CodeProcedure(getName(), type, codeFile);
	for (ParamDecl pd : paramDeclList) {
	    CodeType paramType = SyntaxUnit.getCodeType(getType(), codeFile);
	    proc.addParameter(pd.getName(), paramType);
	}
	codeFile.updateProcedure(proc);
    }
}

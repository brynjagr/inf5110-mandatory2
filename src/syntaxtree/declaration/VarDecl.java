package syntaxtree.declaration;

import bytecode.CodeFile;
import error.VariableAlreadyDeclaredError;
import symboltable.SymbolTable;
import syntaxtree.Indent;

import java.util.List;

/**
 * User: Havard
 * Date: 09.03.13
 * Time: 19:37
 */
public class VarDecl extends Decl {

    SymbolTable classSymbolTable;

    public VarDecl(String type, String name) {
        super.type = type;
        super.name = name;
    }

    @Override
    public String printAst() {
        return Indent.level + "(VAR_DECL (TYPE " + type + ") (NAME " + name + "))\n";
    }

    @Override
    public void checkCode(SymbolTable symbolTable) throws VariableAlreadyDeclaredError {
        if (symbolTable.getLocalDecl(name) != null) {
            throw new VariableAlreadyDeclaredError(name);
        }

        symbolTable.insertDecl(name, this);
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int checkWhetherProc() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int checkWhetherFunction() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ParamDecl> getParamDeclList() {
        throw new UnsupportedOperationException();
    }
}

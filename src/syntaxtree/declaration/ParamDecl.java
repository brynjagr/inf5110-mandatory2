package syntaxtree.declaration;

import bytecode.CodeFile;
import error.VariableAlreadyDeclaredError;
import error.VariableNotDeclaredError;
import symboltable.SymbolTable;
import syntaxtree.ActualParam;
import syntaxtree.Indent;

import java.util.List;

/**
 * User: Havard
 * Date: 10.03.13
 * Time: 14:36
 */
public class ParamDecl extends Decl {

    private ActualParam value;

    public ParamDecl(String type, String name) {
        super.type = type;
        super.name = name;
    }

    @Override
    public String printAst() {
        return Indent.level + ("PARAM_DECL (TYPE " + type + ") (NAME " + name + "))\n");
        //return Indent.level + "(PARAM_DECL (TYPE " + type + ") (" + e.printAst().trim() + ")\n";
    }

    @Override
    public void checkCode(SymbolTable symbolTable) throws VariableAlreadyDeclaredError, VariableNotDeclaredError {
        if (symbolTable.getLocalDecl(this.name) != null) {
            throw new VariableAlreadyDeclaredError(this.name);
        }
        symbolTable.insertDecl(this.name, this);
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

    public void setValue(ActualParam value) {
        this.value = value;
    }
}

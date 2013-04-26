package syntaxtree.declaration;

import bytecode.CodeFile;
import error.VariableAlreadyDeclaredError;
import symboltable.SymbolTable;

/**
 * User: Havard
 * Date: 10.03.13
 * Time: 14:40
 */
public class VarParamDecl extends ParamDecl {

    public VarParamDecl(String type, String name) {
        super(type, name);
    }

    @Override
    public String printAst() {
        return super.printAst().replace("PARAM_DECL", "PARAM_DECL var");
    }

    @Override
    public void checkCode(SymbolTable symbolTable) throws VariableAlreadyDeclaredError {
        if (symbolTable.getLocalDecl(this.name) != null) {
            throw new VariableAlreadyDeclaredError(this.name);
        }

        symbolTable.insertDecl(this.name, this);
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }
}

package syntaxtree.expression.literal;

import bytecode.CodeFile;
import error.NotAVariableError;
import symboltable.SymbolTable;
import syntaxtree.Indent;

public class IntLiteral extends Literal {

    public IntLiteral(String val) {
        super(val);
    }

    public String printAst() {
        return Indent.level + "(INT_LITERAL " + super.val + ")\n";
    }

    @Override
    public void checkWhetherVariable() throws NotAVariableError {
        throw new NotAVariableError("IntLiteral with value " + val);
    }

    @Override
    public void checkCode(SymbolTable symbolTable) {
        super.type = "int";
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }
}
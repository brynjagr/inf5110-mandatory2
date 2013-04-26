package syntaxtree.expression.literal;

import bytecode.CodeFile;
import symboltable.SymbolTable;
import syntaxtree.Indent;

public class FloatLiteral extends Literal {

    public FloatLiteral(String val) {
        super(val);
    }

    public String printAst() {
        return Indent.level + "(FLOAT_LITERAL " + super.val + ")\n";
    }

    @Override
    public void checkWhetherVariable() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void checkCode(SymbolTable symbolTable) {
        super.type = "float";
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }
}

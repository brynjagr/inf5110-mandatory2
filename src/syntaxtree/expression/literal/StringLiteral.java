package syntaxtree.expression.literal;

import bytecode.CodeFile;
import symboltable.SymbolTable;
import syntaxtree.Indent;

public class StringLiteral extends Literal {

    public StringLiteral(String val) {
	    super(val);
    }

    public String printAst() {
	    return Indent.level + "(STRING_LITERAL \"" + super.val + "\" )\n";
    }

    @Override
    public void checkWhetherVariable() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void checkCode(SymbolTable symbolTable) {
        super.type = "string";
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }
}
package syntaxtree.expression.literal;

import bytecode.CodeProcedure;
import bytecode.CodeFile;
import bytecode.instructions.PUSHSTRING;
import error.NotAVariableError;
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
    public void checkWhetherVariable() throws NotAVariableError {
        throw new NotAVariableError(this.getClass().getName());
    }

    @Override
    public void checkCode(SymbolTable symbolTable) {
        super.type = "string";
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void generateInnerCode(CodeProcedure proc) {
	proc.addInstruction(new PUSHSTRING(proc.addStringConstant(super.val)));
    }
}

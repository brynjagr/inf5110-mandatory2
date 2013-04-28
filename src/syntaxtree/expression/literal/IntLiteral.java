package syntaxtree.expression.literal;

import bytecode.CodeProcedure;
import bytecode.instructions.PUSHINT;
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
        throw new NotAVariableError(this.getClass().getName());
    }

    @Override
    public void checkCode(SymbolTable symbolTable) {
        super.type = "int";
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void generateInnerCode(CodeProcedure proc) {
	proc.addInstruction(new PUSHINT(Integer.valueOf(super.val)));
    }
}

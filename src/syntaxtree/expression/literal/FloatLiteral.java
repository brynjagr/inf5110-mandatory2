package syntaxtree.expression.literal;

import bytecode.CodeProcedure;
import bytecode.instructions.PUSHFLOAT;
import bytecode.CodeFile;
import error.NotAVariableError;
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
    public void checkWhetherVariable() throws NotAVariableError {
        throw new NotAVariableError(this.getClass().getName());
    }

    @Override
    public void checkCode(SymbolTable symbolTable) {
        super.type = "float";
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void generateInnerCode(CodeProcedure proc) {
	proc.addInstruction(new PUSHFLOAT(Float.parseFloat(super.val)));
    }
}

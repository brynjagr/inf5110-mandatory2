package syntaxtree.expression.literal;

import bytecode.CodeFile;
import error.NotAVariableError;
import symboltable.SymbolTable;
import syntaxtree.Indent;

/**
 * User: Havard
 * Date: 10.03.13
 * Time: 11:50
 */
public class FalseLiteral extends Literal {

    public FalseLiteral(String val) {
        super(val);
    }

    @Override
    public String printAst() {
        return Indent.level + "(FALSE_LITERAL " + super.val + ")\n";
    }

    @Override
    public void checkWhetherVariable() throws NotAVariableError {
        throw new NotAVariableError(this.getClass().getName());
    }

    @Override
    public void checkCode(SymbolTable symbolTable) {
        super.type = "bool";
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }
}

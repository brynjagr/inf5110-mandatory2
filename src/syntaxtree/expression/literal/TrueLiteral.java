package syntaxtree.expression.literal;

import bytecode.CodeFile;
import symboltable.SymbolTable;
import syntaxtree.Indent;

/**
 * Created with IntelliJ IDEA.
 * User: Havard
 * Date: 10.03.13
 * Time: 11:48
 */
public class TrueLiteral extends Literal {

    public TrueLiteral(String val) {
        super(val);
    }

    @Override
    public String printAst() {
        return Indent.level + "(TRUE_LITERAL " + super.val + ")\n";
    }

    @Override
    public void checkWhetherVariable() {
        throw new UnsupportedOperationException();
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

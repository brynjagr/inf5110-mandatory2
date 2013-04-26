package syntaxtree.expression;

import bytecode.CodeFile;
import symboltable.SymbolTable;

/**
 * User: Havard
 * Date: 10.03.13
 * Time: 12:47
 */
public class EmptyExpression extends Expression {

    @Override
    public String printAst() {
        return "";
    }

    @Override
    public void checkWhetherVariable() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void checkCode(SymbolTable symbolTable) {
        // OK!
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }
}

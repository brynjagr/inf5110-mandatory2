package syntaxtree.expression.variable;

import bytecode.CodeFile;
import symboltable.SymbolTable;
import syntaxtree.Indent;
import syntaxtree.expression.Expression;

/**
 * User: Havard
 * Date: 10.03.13
 * Time: 12:00
 */
public class ClassVariable extends Expression {

    private String varName;
    private String className;

    public ClassVariable(Expression name, String className) {
	    this.varName = name.printAst();
	    this.className = className;
    }

    public ClassVariable(String varName, String className) {
        this.varName = varName;
        this.className = className;
    }

    @Override
    public String printAst() {
        return Indent.level + "( . (NAME " + varName + ") (NAME " + className + "))\n";
    }

    @Override
    public void checkWhetherVariable() {
        // OK!
    }

    @Override
    public void checkCode(SymbolTable symbolTable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }
}

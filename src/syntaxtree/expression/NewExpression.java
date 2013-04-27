package syntaxtree.expression;

import bytecode.CodeFile;
import error.ClassNotFoundError;
import error.NotAVariableError;
import symboltable.SymbolTable;
import syntaxtree.Indent;

/**
 * User: Havard
 * Date: 10.03.13
 * Time: 15:19
 */
public class NewExpression extends Expression {

    private String name;

    public NewExpression(String name) {
        this.name = name;
    }

    @Override
    public String printAst() {
        return Indent.level + "(NEW (TYPE " + name + "))\n";
    }

    @Override
    public void checkWhetherVariable() throws NotAVariableError {
        throw new NotAVariableError(this.getClass().getName());
    }

    @Override
    public void checkCode(SymbolTable symbolTable) throws ClassNotFoundError {
        if (!SymbolTable.containsType(name)) {
            super.error = true;
            throw new ClassNotFoundError(name);
        }

        super.type = name;
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }
}

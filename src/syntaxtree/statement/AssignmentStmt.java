package syntaxtree.statement;

import bytecode.CodeFile;
import error.*;
import symboltable.SymbolTable;
import syntaxtree.Indent;
import syntaxtree.expression.Expression;
import syntaxtree.expression.variable.Variable;

/**
 * Created with IntelliJ IDEA.
 * User: Havard
 * Date: 10.03.13
 * Time: 12:13
 */
public class AssignmentStmt extends Stmt {

    Expression e1;
    Expression e2;

    public AssignmentStmt(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public String printAst() {

        String s = Indent.level + "(ASSIGN STMT\n";
        Indent.inc();
        s += e1.printAst();
        s += e2.printAst();
        Indent.dec();
        s += Indent.level + ")\n";
        return s;
    }

    @Override
    public void checkCode(SymbolTable symbolTable) throws VariableNotDeclaredError, TypeNotSameError, MissingReturnStmtError, FunctionNotDeclaredError, VariableAlreadyDeclaredError {

        try {
            e1.checkCode(symbolTable);
            e1.checkWhetherVariable();
        } catch(error.Error e) {
            error = true;
        }

        try {
            e2.checkCode(symbolTable);
        } catch(error.Error e) {
            error = true;
        }

        /*If there is nothing wrong we try to do the actual assignment*/
        if (!error) {
            try {
                checkSameType(e1.getType(), e2.getType());
                Variable v = e1.getVariable();
                v.setValue(e2);
            } catch(error.Error e) {
                error = true;
            }
        }
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }

}

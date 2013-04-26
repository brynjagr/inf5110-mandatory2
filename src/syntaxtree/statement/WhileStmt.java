package syntaxtree.statement;

import bytecode.CodeFile;
import error.*;
import error.Error;
import symboltable.SymbolTable;
import syntaxtree.Indent;
import syntaxtree.expression.Expression;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Havard
 * Date: 10.03.13
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
public class WhileStmt extends Stmt {

    private Expression test;
    private List<Stmt> stmtList;
    private SymbolTable localSymbolTable;

    public WhileStmt(Expression test, List<Stmt> stmtList) {
        this.test = test;
        this.stmtList = stmtList;
    }

    @Override
    public String printAst() {
        String str = Indent.level + "(WHILE " + test.printAst().trim();

        if (!stmtList.isEmpty()) {
            str += "\n";
            Indent.inc();
            str += Indent.level + "(\n";
            Indent.inc();
            for (Stmt s : stmtList) {
                str += s.printAst();
            }
            Indent.dec();
            str += Indent.level + ")\n";
            Indent.dec();
            str += Indent.level;
        }

        str += ")\n";
        return str;
    }

    @Override
    public void checkCode(SymbolTable outerSymbolTable) throws FunctionNotDeclaredError, VariableAlreadyDeclaredError, TypeNotSameError, NotAClassError, ProcedureUsedInExpressionError, ClassNotFoundError, TypeNotExistError, MainNotFoundError, MissingReturnStmtError, VariableNotDeclaredError, NotAVariableError, MainMustBeProcedureError, MainCantTakeParameters, FunctionMustReturnTypeError, WrongNumberOfActualParametersError, ProcedureCantReturnValueError {
        test.checkCode(outerSymbolTable);

        /*TODO: fiks error-meldinger: 'must have bool'*/
        checkSameType(test.getType(), "bool");

        this.localSymbolTable = new SymbolTable(outerSymbolTable);

        for (Stmt stmt: stmtList) {
            try {
                stmt.checkCode(localSymbolTable);
            } catch (Error e) {
                super.error = true;
            }
        }

    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }
}

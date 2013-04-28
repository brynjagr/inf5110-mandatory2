package syntaxtree.statement;

import bytecode.CodeFile;
import error.*;
import symboltable.SymbolTable;
import syntaxtree.Indent;
import syntaxtree.expression.Expression;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Havard
 * Date: 10.03.13
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */
public class IfElseStmt extends Stmt {

    private Expression test;
    private List<Stmt> stmtList;
    private ElsePart   elsePart;
    private SymbolTable localSymbolTable;

    public IfElseStmt(Expression test, List<Stmt> stmtList, ElsePart elsePart) {
        this.test = test;
        this.stmtList = stmtList;
        this.elsePart = elsePart;
    }

    @Override
    public String printAst() {
        String s = Indent.level + "(IF_STMT " + test.printAst().trim();

        if (!stmtList.isEmpty()) {
            s += "\n";
            Indent.inc();
            s += Indent.level + "(\n";
            Indent.inc();
            for (Stmt st : stmtList) {
                s += st.printAst();
            }
            Indent.dec();
            s += Indent.level + ")\n";
            Indent.dec();
            s += Indent.level;
        }

        s += ") " + elsePart.printAst() + "\n";
        return s;
    }

    @Override
    public void checkCode(SymbolTable outerSymbolTable) throws FunctionNotDeclaredError, MainNotFoundError, TypeNotSameError, NotAVariableError, FunctionMustReturnTypeError, ProcedureCantReturnValueError, TypeNotExistError, MainMustBeProcedureError, ProcedureUsedInExpressionError, VariableNotDeclaredError, WrongNumberOfActualParametersError, ClassNotFoundError, VariableAlreadyDeclaredError, NotAClassError, MissingReturnStmtError, NotAFunctionError, MainCantTakeParametersError, NotCallableError {
        test.checkCode(outerSymbolTable);

        checkSameType(test.getType(), "bool");

        this.localSymbolTable = new SymbolTable(outerSymbolTable);

        for (Stmt stmt: stmtList) {
            try {
                stmt.checkCode(localSymbolTable);
            } catch (error.Error e) {
                super.error = true;
            }
        }

        elsePart.checkCode(outerSymbolTable);
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }
}

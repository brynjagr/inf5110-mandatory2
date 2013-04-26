package syntaxtree.expression.operator;

import bytecode.CodeFile;
import error.*;
import symboltable.SymbolTable;
import syntaxtree.Indent;
import syntaxtree.expression.Expression;

/**
 * User: Havard
 * Date: 09.03.13
 * Time: 18:18
 */
public class OperatorExpression extends Expression {

    private Operator op;
    private Expression e1;
    private Expression e2;

    public OperatorExpression(Operator op, Expression e2, Expression e1) {
        this.e2 = e2;
        this.e1 = e1;
        this.op = op;
    }

    @Override
    public String printAst() {

        String s = Indent.level + "(" + op.printAst();
        Indent.inc();
        s += e1.printAst();
        s += e2.printAst();
        Indent.dec();
        s += Indent.level + ")\n";

        return s;
    }

    @Override
    public void checkCode(SymbolTable symbolTable) throws FunctionNotDeclaredError, ClassNotFoundError, VariableAlreadyDeclaredError, MissingReturnStmtError, TypeNotExistError, MainNotFoundError, VariableNotDeclaredError, TypeNotSameError, NotAClassError, ProcedureUsedInExpressionError, NotAVariableError, MainMustBeProcedureError, MainCantTakeParameters, FunctionMustReturnTypeError, WrongNumberOfActualParametersError, ProcedureCantReturnValueError {
        e1.checkCode(symbolTable);
        e2.checkCode(symbolTable);

        /*Stemmer dette?*/
        checkSameType(e1.getType(), e2.getType());
        type = e1.getType();
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void checkWhetherVariable() {
        throw new UnsupportedOperationException();
    }
}

package syntaxtree.expression;

import bytecode.CodeFile;
import compiler.Compiler;
import error.*;
import symboltable.SymbolTable;
import syntaxtree.declaration.ClassDecl;
import syntaxtree.expression.variable.Variable;

/**
 * User: brynjagr
 * Date: 4/19/13
 * Time: 5:32 PM
 */
public class DotExpression extends Expression {

    private Expression e1, e2;
    Variable righthandSide;


    public DotExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public String printAst() {
        return "FIX ME!!!";
       // throw new UnsupportedOperationException();
    }

    @Override
    public void checkWhetherVariable() throws NotAVariableError {
        e2.checkWhetherVariable();
    }

    @Override
    public Variable getVariable(){
        /*TODO: fix for nested dotexpressions */
        return righthandSide;
    }

    @Override
    public void checkCode(SymbolTable symbolTable) throws FunctionNotDeclaredError, ClassNotFoundError, VariableAlreadyDeclaredError, MissingReturnStmtError, TypeNotExistError, VariableNotDeclaredError, TypeNotSameError, NotAClassError, ProcedureUsedInExpressionError, MainNotFoundError, NotAVariableError, MainMustBeProcedureError, MainCantTakeParameters, FunctionMustReturnTypeError, WrongNumberOfActualParametersError, ProcedureCantReturnValueError {
        Compiler.log(this);
        /* 1. Check e1 is a class and is declared
        *  2. Check if e2 is in e1.symbolTable  */

        // Both e1 and e2 must be variables
        e1.checkWhetherVariable();
        e2.checkWhetherVariable();

        righthandSide = (Variable) e2;

        e1.checkCode(symbolTable);

        Variable classPointer = e1.getVariable();

            /*The variable must be a classPointer*/
        if (!SymbolTable.containsClassType(classPointer.getType())) {
            throw new NotAClassError("NotAClassError: Expected class on lefthandside of '.', found: " + classPointer.getName());
        }

        ClassDecl classDecl = (ClassDecl) symbolTable.getDecl(classPointer.getType());

        e2.checkCode(classDecl.getSymboltable());

        super.type = righthandSide.getType();

    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }
}

package syntaxtree.declaration;

import error.*;
import error.Error;
import symboltable.SymbolTable;
import syntaxtree.statement.ReturnStmt;
import syntaxtree.statement.Stmt;

import java.util.Iterator;
import java.util.List;

/**
 * User: brynjagr
 * Date: 4/11/13
 * Time: 11:12 AM
 */
public class ProcedureDecl extends FunctionDecl {

    public ProcedureDecl(String name, List<ParamDecl> paramDeclList, List<Decl> declList, List<Stmt> stmtList) {
        super(null, name, paramDeclList, declList, stmtList);
    }

    @Override
    public void checkWhetherMain () throws MainMustBeProcedureError, MainCantTakeParameters {
        if (!name.equals("Main")) {
            throw new MainMustBeProcedureError();
        }

        if (paramDeclList.size() != 0) {
            throw new MainCantTakeParameters();
        }
    }

    @Override
    public void checkWhetherProc() {
        // OK!
    }

    public void checkWhetherFunction() throws NotAFunctionError {
        throw new NotAFunctionError(name);
    }

    //@Override
    public void checkCode(SymbolTable outerSymbolTable) throws VariableNotDeclaredError, FunctionNotDeclaredError, TypeNotSameError, VariableAlreadyDeclaredError, MissingReturnStmtError, TypeNotExistError, ClassNotFoundError, MainNotFoundError, FunctionMustReturnTypeError, ProcedureCantReturnValueError {
        localSymbolTable = new SymbolTable(outerSymbolTable);

        for (ParamDecl p : paramDeclList) {
            try {
                p.checkCode(localSymbolTable);
                if (p.getError()) {
                    super.error = true;
                }
            } catch (Error e) {
                System.out.println("ProcedureDecl 43: Error!");
                super.error = true;
            }
            // Check for error in sym table
        }

        for (Decl d : declList) {
            try {
                d.checkCode(localSymbolTable);
                if (d.getError()) {
                    super.error = true;
                }
            } catch(Error e) {
                super.error = true;
            }

        }

        Iterator<Stmt> iterator = stmtList.iterator();
        Stmt s = null;
        while (iterator.hasNext()) {

            s = iterator.next();
            try {
                s.checkCode(localSymbolTable);
                if (s.getError()) {
                    super.error = true;
                }

                if (s instanceof ReturnStmt && iterator.hasNext()) {
                }
            } catch(Error e) {
                super.error = true;
            }
        }

        if (s != null && s instanceof ReturnStmt) {
            ReturnStmt returnStmt = (ReturnStmt) s;

                if (returnStmt.getType() != null) {
                    throw new ProcedureCantReturnValueError(this.name);
                }
        }

        // Prevent recursive calls
        outerSymbolTable.insertDecl(name, this);
    }

}

package syntaxtree.declaration;

import bytecode.CodeFile;
import error.*;
import symboltable.SymbolTable;
import syntaxtree.Indent;
import syntaxtree.statement.ReturnStmt;
import syntaxtree.statement.Stmt;

import java.util.Iterator;
import java.util.List;

/**
 * User: Havard
 * Date: 09.03.13
 * Time: 19:35
 */
public class FunctionDecl extends Decl {

    protected SymbolTable localSymbolTable;
    protected List<ParamDecl> paramDeclList;
    protected List<Decl> declList;
    protected List<Stmt> stmtList;
    private ReturnStmt returnStmt;

    public FunctionDecl(String type, String name, List<ParamDecl> paramDeclList, List<Decl> declList, List<Stmt> stmtList) {
        this.type = type;
        this.name = name;
        this.paramDeclList = paramDeclList;
        this.declList = declList;
        this.stmtList = stmtList;
    }

    @Override
    public String printAst() {
        String s = Indent.level + "(PROC_DECL (TYPE " + type + ") (NAME " + name + ")";

        if (!paramDeclList.isEmpty()) {
            s += "\n";
            Indent.inc();
            for (Decl d : paramDeclList) {
                s += d.printAst();
            }
            Indent.dec();
        }

        if (!declList.isEmpty()) {
            s += "\n";
            Indent.inc();
            for (Decl d : declList) {
                s += d.printAst();
            }
            Indent.dec();
        }

        if (!stmtList.isEmpty()) {
            s += "\n";
            Indent.inc();
            for (Stmt st : stmtList) {
                s += st.printAst();
            }
            Indent.dec();
        }

        if (!(paramDeclList.isEmpty() && declList.isEmpty() && stmtList.isEmpty())) {
            s += Indent.level;
        }

        s += ")\n";
        return s;
    }

    @Override
    public void checkCode(SymbolTable outerSymbolTable) throws VariableNotDeclaredError, FunctionNotDeclaredError, TypeNotSameError, VariableAlreadyDeclaredError, MissingReturnStmtError, TypeNotExistError, ClassNotFoundError, MainNotFoundError, NotAClassError, ProcedureUsedInExpressionError, NotAVariableError, MainMustBeProcedureError, MainCantTakeParameters, FunctionMustReturnTypeError, WrongNumberOfActualParametersError, ProcedureCantReturnValueError, NotAFunctionError {

        localSymbolTable = new SymbolTable(outerSymbolTable);

        for (ParamDecl p : paramDeclList) {
            p.checkCode(localSymbolTable);
            if (p.getError()) {
                super.error = true;
            }
        }

        for (Decl d : declList) {
            d.checkCode(localSymbolTable);
            if (d.getError()) {
                super.error = true;
            }
        }

        Iterator<Stmt> iterator = stmtList.iterator();
        Stmt s = null;
        while (iterator.hasNext()) {

            s = iterator.next();

            s.checkCode(localSymbolTable);
            if (s.getError()) {
                super.error = true;
            }
            if (s instanceof ReturnStmt && iterator.hasNext()) {

                super.error = true;
            }
        }

        if (s != null && s instanceof ReturnStmt) {

            returnStmt = (ReturnStmt) s;

            try {
                if (returnStmt.getType() == null) {
                    throw new FunctionMustReturnTypeError(this.name);
                }
                checkSameType(type, returnStmt.getType());
            } catch (TypeNotSameError e) {
                super.error = true;
            } catch (TypeNotExistError e) {
                super.error = true;
            }
        } else {
            throw new MissingReturnStmtError(name);
        }

        if (super.error) {
            return;
        }

         // Prevent recursive calls
        outerSymbolTable.insertClassDecl(this.name, this);
    }

    @Override
    public void generateCode(CodeFile codeFile) {

	codeFile.addProcedure(getName());

	CodeType type = SyntaxUnit.getCodeType(getType(), codeFile);
	CodeProcedure proc = new CodeProcedure(getName(), type, codeFile);

	// Add parameters to proc
	for (ParamDecl pd : paramDeclList) {
	    CodeType paramType = SyntaxUnit.getCodetype(pd.getType(), codeFile);
	    proc.addParameter(pd.getName(), paramType);
	}

	for (Decl d : declList) {
	    if (!(d instanceof VarDecl)) {
		    throw new UnsupportedOperationException("Can't have procedure/function/class declarations in a function!");
	    }

	    proc.addLocalVariable(d.getName(), SyntaxUnit.getCodeType(d.getType(), codeFile));
	}

	for (Stmt s : stmtList) {
	    s.generateCode(proc);
	}

	proc.addInstruction(new RETURN());
        codeFile.updateProcedure(proc);	
    }

    @Override
    public void checkWhetherFunction() throws NotAFunctionError {
        // OK!
    }

    @Override
    public List<ParamDecl> getParamDeclList() {
        return paramDeclList;
    }

    public ReturnStmt getReturnStmt() {
        return this.returnStmt;
    }
}

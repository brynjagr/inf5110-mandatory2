package syntaxtree;
import bytecode.CodeFile;
import error.*;
import error.Error;
import symboltable.SymbolTable;
import syntaxtree.declaration.Decl;

import java.util.List;

public class Program extends SyntaxUnit {

    SymbolTable localSymbolTable;

    List<Decl> decls;

    public Program(List<Decl> decls) {
        this.decls = decls;
    }

    public String printAst(){
        StringBuilder sb = new StringBuilder();
        sb.append("(Program\n");
        Indent.inc();
        for (Decl decl : decls) {
            sb.append(decl.printAst());
        }
        Indent.dec();
        sb.append(")");
        return sb.toString();
    }

    public void checkCode(SymbolTable outerSymbolTable) throws MainNotFoundError, MainMustBeProcedureError, MainCantTakeParameters {

        this.localSymbolTable = new SymbolTable(outerSymbolTable);
        for (Decl d: decls) {

            try {
                d.checkCode(localSymbolTable);
                if (d.getError()) {
                    super.error = true;
                }

            } catch (Error e) {
                super.error = true;
            }
        }

        Decl main = localSymbolTable.getDecl("Main");

        if (main == null) {
            throw new MainNotFoundError();
        }

        main.checkWhetherMain();
    }

    /*
     * foreach declaration
     *    generateCode
     * set main method
     */
    public void generateCode(CodeFile codeFile) {

	/* Add Library functions */
	for (Decl d : localSymbolTable.getLibraryFunctions()) {
	    d.generateCode(codeFile);
	}

	for (Decl d : decls) {
	    d.generateCode(codeFile);
	}

	codeFile.setMain("Main");
    }
}

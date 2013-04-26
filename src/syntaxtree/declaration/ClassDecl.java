package syntaxtree.declaration;

import bytecode.CodeFile;
import error.AlreadyDecalredError;
import error.Error;
import error.TypeAlreadyDecalredError;
import symboltable.SymbolTable;
import syntaxtree.Indent;

import java.util.List;

public class ClassDecl extends Decl {

    private String name;
    private List<Decl> varDeclList;
    private SymbolTable localSymbolTable;
    boolean typeError = false;

    public ClassDecl (String name, List<Decl> varDeclList) {
        this.name = name;
        this.varDeclList = varDeclList;
    }

    public String printAst() {
        String s = Indent.level + ("CLASS_DECL (NAME " + name + ")");

        if (!varDeclList.isEmpty()) {
            s += "\n";
            Indent.inc();
            for (Decl d : varDeclList) {
                s += d.printAst();
            }
            Indent.dec();
            s += Indent.level;
        }

        s += ")\n";
        return s;
    }

    @Override
    public void checkCode(SymbolTable symbolTable) {

        this.localSymbolTable = new SymbolTable(symbolTable);

        /*Checks if class is already declared, or name is already used*/
        try {
            if (localSymbolTable.getDecl(name) != null) {
                throw new AlreadyDecalredError(name);
            }

            if (SymbolTable.containsType(name)) {
                throw new TypeAlreadyDecalredError(name);
            }
        } catch (AlreadyDecalredError alreadyDecalredError) {
            super.error = true;
        } catch (TypeAlreadyDecalredError typeAlreadyDecalredError) {
            typeError = true;
            super.error = true;
        }

        for (Decl d : varDeclList) {
            try {
                d.checkCode(localSymbolTable);
            } catch (Error e) {
                super.error = true;
            }
        }

        if (!typeError) {
            symbolTable.insertClassDecl(name, this);
        }
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int checkWhetherProc() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int checkWhetherFunction() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ParamDecl> getParamDeclList() {
        throw new UnsupportedOperationException();
    }

    public SymbolTable getSymboltable() {
        return localSymbolTable;
    }

    public Decl containsDecl(String name) {
        return localSymbolTable.getLocalDecl(name);
    }
}
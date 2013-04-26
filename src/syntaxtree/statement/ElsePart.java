package syntaxtree.statement;

import bytecode.CodeFile;
import symboltable.SymbolTable;
import syntaxtree.Indent;
import syntaxtree.SyntaxUnit;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Havard
 * Date: 10.03.13
 * Time: 15:28
 * To change this template use File | Settings | File Templates.
 */
public class ElsePart extends SyntaxUnit {

    List<Stmt> stmtList;

    public ElsePart(List<Stmt> stmtList) {
        this.stmtList = stmtList;
    }

    public String printAst() {
        if (stmtList.isEmpty()) {
            return "";
        }
        String t = "(ELSE_PART\n";
        Indent.inc();
        t += Indent.level + "(\n" ;
        Indent.inc();
        for (Stmt s : stmtList) {
            t += s.printAst();
        }
        Indent.dec();
        t += Indent.level + ")\n";
        Indent.dec();
        t += Indent.level + ")";

        return t;
    }

    @Override
    public void checkCode(SymbolTable symbolTable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        throw new UnsupportedOperationException();
    }
}

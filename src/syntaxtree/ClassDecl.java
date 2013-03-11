package syntaxtree;

import java.util.List;

public class ClassDecl extends Decl {

    List<VarDecl> varDeclList;
    public ClassDecl (String name, List<VarDecl> varDeclList) {
        super(name);
        this.varDeclList = varDeclList;
    }

    public String printAst() {
        StringBuilder sb = new StringBuilder();

        sb.append(Indent.level);
        sb.append("(CLASS_DECL (NAME ");
        sb.append(name);
        sb.append(")");

        if (!varDeclList.isEmpty()) {
            sb.append("\n");
            Indent.inc();
            for (VarDecl vd: varDeclList) {
                sb.append(Indent.level);
                sb.append(vd.printAst());
            }
            Indent.dec();
            sb.append(Indent.level);
        }

        sb.append(")\n");
        return sb.toString();
    }
}

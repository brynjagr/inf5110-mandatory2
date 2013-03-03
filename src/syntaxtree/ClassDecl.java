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
   
        sb.append("(CLASS_DECL (NAME " + name + ")");
        for (VarDecl vd: varDeclList) {
            sb.append("\n    " + vd.printAst());
        }
        sb.append(")\n");
        return sb.toString();
    }
}

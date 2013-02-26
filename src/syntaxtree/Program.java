package syntaxtree;
import java.util.List;

public class Program {

    List<Decl> decls;

    public Program(List<Decl> decls) {
        this.decls = decls;
    }

    public String printAst(){
        StringBuilder sb = new StringBuilder();
        for (Decl decl : decls) {
            sb.append(decl.printAst());
            sb.append("\n");
        }
        return sb.toString();
    }
}

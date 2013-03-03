package syntaxtree;

public class VarDecl extends Decl {

    Type type;

    public VarDecl(String name, Type type) {
        super(name);
        this.type = type;
    }

    public String printAst() {
        return "(VAR_DECL " + type.printAst() + " (NAME " + name + "))\n";
    }
}

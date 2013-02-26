package syntaxtree;

public class ClassDecl extends Decl {

    VarDecl vd;
    public ClassDecl (String name, VarDecl vd) {
        super(name);
        this.vd = vd;
    }

    public String printAst() {
        return "(CLASS_DECL (NAME " + name + ")\n\t"+vd.printAst()+"\n)";
    }
}

package syntaxtree;

public abstract class Decl {
    protected String name;

    public Decl(String name) {
        this.name = name;
    }

    public abstract String printAst();
}

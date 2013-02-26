package syntaxtree;

public class Type {
    String name;

    public Type(String name) {
        this.name = name;
    }

    public String printAst() {
        return "(TYPE " + name + ")"; 
    }
}

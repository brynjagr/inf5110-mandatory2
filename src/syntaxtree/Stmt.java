package syntaxtree;

public abstract class Stmt {

	protected String name;
	
	public Stmt(String name) {
		this.name = name;
	}

    public Stmt() {}
	
	abstract String printAst();
}
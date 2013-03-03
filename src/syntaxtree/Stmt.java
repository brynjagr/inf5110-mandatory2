package syntaxtree;

public abstract class Stmt {

	protected String name;
	
	public Stmt(String name) {
		this.name = name;
	}
	
	abstract String printAst();
}
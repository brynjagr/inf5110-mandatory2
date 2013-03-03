package syntaxtree;

public class ParamDecl extends Decl {

	Type type;
	boolean var;

	public ParamDecl(String name, Type type, boolean var) {
		super(name);
		this.type = type;
		this.var = var;
	}
	
	public String printAst() {
		StringBuilder sb = new StringBuilder();
		sb.append("(PARAM_DECL ");
		if (var) {
			sb.append("var ");
		}
		sb.append(type.printAst());
		sb.append(" (NAME ");
		sb.append(super.name);
		sb.append("))\n");
		return sb.toString();
	}
}
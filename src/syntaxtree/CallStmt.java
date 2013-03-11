package syntaxtree;

public class CallStmt extends Stmt {

	public CallStmt(String name) {
		super(name);
	}
	
	public String printAst() {
		StringBuilder sb = new StringBuilder();
        sb.append(Indent.level);
		sb.append("(CALL_STMT (NAME ");
		sb.append(super.name);
		sb.append(")");
		
		// for (ActualParam ap : actualParamList) { ... }
		sb.append(")\n");
		
		return sb.toString();
	}
}
package syntaxtree;
import java.util.List;

public class ProcDecl extends Decl {

	List<ParamDecl> paramDeclList;

	public ProcDecl(String name, List<ParamDecl> paramDeclList) {
		super(name);
		this.paramDeclList = paramDeclList;
	}
	
	public String printAst() {
		StringBuilder sb = new StringBuilder();
		sb.append("(PROC_DECL ");
		sb.append("(NAME ");
		sb.append(super.name);
		sb.append(")\n");
		for (ParamDecl pd : paramDeclList) {
			sb.append("\t");
			sb.append(pd.printAst());
		}
		
		// for (Decl d : declList) { }
		// for (Stmt s : stmtList) { }
		
		sb.append(")\n");
		return sb.toString();
	}
}
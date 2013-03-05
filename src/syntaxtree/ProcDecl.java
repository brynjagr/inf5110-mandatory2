package syntaxtree;
import java.util.List;

public class ProcDecl extends Decl {

	List<ParamDecl> paramDeclList;
	List<Decl> declList;
	List<Stmt> stmtList;

	public ProcDecl(String name, List<ParamDecl> paramDeclList, List<Decl> declList, List<Stmt> stmtList) {
		super(name);
		this.paramDeclList = paramDeclList;
		this.declList = declList;
		this.stmtList = stmtList;
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

		for (Decl d : declList) { 
			sb.append("\t");
			sb.append(d.printAst());
		}
		
		for (Stmt s : stmtList) { 
			sb.append("\t");
			sb.append(s.printAst());
		}
		
		sb.append(")\n");
		return sb.toString();
	}
}
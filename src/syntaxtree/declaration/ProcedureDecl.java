package syntaxtree.declaration;

import syntaxtree.statement.Stmt;

import java.util.List;

/**
 * User: brynjagr
 * Date: 4/11/13
 * Time: 11:12 AM
 */
public class ProcedureDecl extends FunctionDecl {

    public ProcedureDecl(String name, List<ParamDecl> paramDeclList, List<Decl> declList, List<Stmt> stmtList) {
        super(null, name, paramDeclList, declList, stmtList);
    }


}

package syntaxtree.declaration;

import error.MainMustBeProcedureError;
import syntaxtree.SyntaxUnit;

import java.util.List;

/**
 * User: Havard
 * Date: 09.03.13
 * Time: 19:37
 */
public abstract class Decl extends SyntaxUnit {

    protected String type;
    protected String name;

    /*
       1. ChecKWhetherProc
       2. check num parameters == 0
        */
    public void checkWhetherMain() throws MainMustBeProcedureError {
        throw new MainMustBeProcedureError();
    }

    /*
        1. Check if type == ""
     */
    public abstract int checkWhetherProc();

    /*
        1. Check if type != ""
     */
    public abstract int checkWhetherFunction();

    public abstract List<ParamDecl> getParamDeclList();

    public String getType() {
        return type;
    }
}

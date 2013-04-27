package syntaxtree.declaration;

import error.MainCantTakeParameters;
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

    /* Procedure overrides this one*/
    public void checkWhetherMain() throws MainMustBeProcedureError, MainCantTakeParameters {
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

    /**
     * Gets the type of the declaration
     * @return the type
     */
    public String getType() {
        return type;
    }
}

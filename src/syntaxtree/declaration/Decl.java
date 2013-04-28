package syntaxtree.declaration;

import error.*;
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
    public void checkWhetherMain() throws MainMustBeProcedureError, MainCantTakeParametersError {
        throw new MainMustBeProcedureError();
    }

    public void checkWhetherProc() throws NotAProcedureError{
        throw new NotAProcedureError(name);
    }

    public void checkWhetherFunction() throws NotAFunctionError {
        throw new NotAFunctionError(name);
    }

    public List<ParamDecl> getParamDeclList() throws NotAFunctionError {
        throw new NotAFunctionError(name);
    }

    /**
     * Gets the type of the declaration
     * @return the type
     */
    public void checkWhetherCallable() throws NotCallableError {
        throw new NotCallableError(name);
    }
    public String getType() {
        return type;
    }


}

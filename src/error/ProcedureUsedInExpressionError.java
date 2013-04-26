package error;

/**
 * User: brynjagr
 * Date: 4/24/13
 * Time: 3:47 PM
 */
public class ProcedureUsedInExpressionError extends Error {
    public ProcedureUsedInExpressionError(String name) {
        super("ProcedureUsedInExpression: Procedure " + name + " tried used in expression.");
    }
}

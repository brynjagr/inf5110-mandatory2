package error;

/**
 * User: brynjagr
 * Date: 4/26/13
 * Time: 7:00 PM
 */
public class ProcedureCantReturnValueError extends Error {
    public ProcedureCantReturnValueError(String name) {
        super("ProcedureCantReturnValueError: Procedure " + name + " returns a value.");
    }
}

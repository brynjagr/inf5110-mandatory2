package error;

/**
 * User: brynjagr
 * Date: 4/27/13
 * Time: 10:39 PM
 */
public class NotAProcedureError extends Error {
    public NotAProcedureError(String name) {
        super("NotAProcedureError: " + name + " is not a procedure");
    }
}

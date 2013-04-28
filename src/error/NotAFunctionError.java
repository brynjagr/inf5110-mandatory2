package error;

/**
 * User: brynjagr
 * Date: 4/27/13
 * Time: 10:29 PM
 */
public class NotAFunctionError extends Error {
    public NotAFunctionError(String name) {
        super("NotAFunctionError: " + name + " is not a function.");
    }
}

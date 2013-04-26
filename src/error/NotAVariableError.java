package error;

/**
 * User: brynjagr
 * Date: 4/23/13
 * Time: 1:41 PM
 */
public class NotAVariableError extends Error {
    public NotAVariableError(String s) {
        super("NotAVariableError: " + s + " is not a variable.");
    }
}

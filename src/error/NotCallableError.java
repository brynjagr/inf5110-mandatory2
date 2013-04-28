package error;/**
 * User: brynjagr
 * Date: 4/28/13
 * Time: 2:08 PM
 */
public class NotCallableError extends Error {
    public NotCallableError(String name) {
        super("NotCallableError: " + name + " is not callable.");
    }
}

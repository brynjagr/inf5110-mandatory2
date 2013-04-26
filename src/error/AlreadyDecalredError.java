package error;

/**
 * User: brynjagr
 * Date: 4/23/13
 * Time: 12:04 PM
 */
public class AlreadyDecalredError extends Error {
    public AlreadyDecalredError(String name) {
        super("AlreadyDeclaredError: " + name);
    }
}

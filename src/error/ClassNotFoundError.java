package error;
/**
 * User: brynjagr
 * Date: 4/23/13
 * Time: 12:12 PM
 */
public class ClassNotFoundError extends Error {
    public ClassNotFoundError(String name) {
        super("ClassNotFoundError: Class with name " + name + " not found.");
    }
}

package error;

/**
 * User: brynjagr
 * Date: 4/23/13
 * Time: 12:06 PM
 */
public class TypeAlreadyDecalredError extends Error {
    public TypeAlreadyDecalredError(String name) {
        super("TypeAlreadyDeclaredError: " + name);
    }
}

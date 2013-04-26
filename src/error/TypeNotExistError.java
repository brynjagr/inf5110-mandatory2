package error;

/**
 * User: brynjagr
 * Date: 4/23/13
 * Time: 11:49 AM
 */
public class TypeNotExistError extends Error {
    public TypeNotExistError(String t1) {
        super("TypeNotExistError: Type '" + t1 + "' does not exist!");
    }
}

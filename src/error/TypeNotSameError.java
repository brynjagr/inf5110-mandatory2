package error;

/**
 * User: brynjagr
 * Date: 4/19/13
 * Time: 5:12 PM
 */
public class TypeNotSameError extends Error {
    public TypeNotSameError(String t1, String t2) {
        super("TypeNotSameError: \n\tFound: " + t1 + "\n\tExpected: " + t2);
    }
}

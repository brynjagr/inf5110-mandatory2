package error;

/**
 * User: brynjagr
 * Date: 4/26/13
 * Time: 4:53 PM
 */
public class FunctionMustReturnTypeError extends Error {
    public FunctionMustReturnTypeError(String name) {
        super("FunctionMustReturnTypeError: Function " + name + " doesn't have a return value");
    }
}

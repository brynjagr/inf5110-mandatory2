package error;

/**
 * User: brynjagr
 * Date: 4/19/13
 * Time: 3:40 PM
 */

public class FunctionNotDeclaredError extends Error {

    public FunctionNotDeclaredError(String funcName) {
        super("FunctionNotDeclaredError: Undefined reference to function " + funcName);
    }
}

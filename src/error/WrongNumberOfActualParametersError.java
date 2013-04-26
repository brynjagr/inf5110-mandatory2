package error;

/**
 * User: brynjagr
 * Date: 4/19/13
 * Time: 4:39 PM
 */
public class WrongNumberOfActualParametersError extends Error {
    public WrongNumberOfActualParametersError(String s) {
        super("WrongNumberOfActualParametersError: Too " + s + " arguments");
    }
}

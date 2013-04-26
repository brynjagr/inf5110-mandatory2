package error;/**
 * User: brynjagr
 * Date: 4/19/13
 * Time: 6:15 PM
 */
public class VariableAlreadyDeclaredError extends Error {


    public VariableAlreadyDeclaredError(String name) {
        super("VariableAlreadyDeclaredError: " + name);
    }
}

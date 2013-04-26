package error;

/**
 * User: brynjagr
 * Date: 4/19/13
 * Time: 6:32 PM
 */
public class VariableNotDeclaredError extends Error {
    public VariableNotDeclaredError(String name) {
        super("VariableNotDeclaredError: "  + name + " does not exist.");
    }
}

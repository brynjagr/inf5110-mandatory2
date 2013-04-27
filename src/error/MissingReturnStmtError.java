package error;

/**
 * User: brynjagr
 * Date: 4/19/13
 * Time: 5:04 PM
 */
public class MissingReturnStmtError extends Error {
    public MissingReturnStmtError(String name) {
        super("MissingReturnStmtError: Missing return statement in function " + name);
    }
}

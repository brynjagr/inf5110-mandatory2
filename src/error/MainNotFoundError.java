package error;

/**
 * User: brynjagr
 * Date: 4/19/13
 * Time: 3:41 PM
 */

public class MainNotFoundError extends Error {

    public MainNotFoundError() {
        super("Undefined reference to Main");
    }
}



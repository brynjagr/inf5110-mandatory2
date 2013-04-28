package error;/**
 * User: brynjagr
 * Date: 4/26/13
 * Time: 4:22 PM
 */
public class MainCantTakeParametersError extends Error {

    public MainCantTakeParametersError() {
        super("MainCantTakeParameters: Main was declared with parameters");
    }
}

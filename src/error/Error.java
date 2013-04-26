package error;

/**
 * User: brynjagr
 * Date: 4/11/13
 * Time: 3:14 PM
 */
public abstract class Error extends Exception {

    static StringBuilder errorMessages = new StringBuilder();

    public Error(String errormsg) {
        errorMessages.append(errormsg + "\n");
    }

    public static String getErrorMessages() {
        return errorMessages.toString();
    }

    public static void clearErrorMessages() {
        errorMessages = new StringBuilder();
    }

    public static void append(String s) {
        errorMessages.append(s + "\n");
    }
}
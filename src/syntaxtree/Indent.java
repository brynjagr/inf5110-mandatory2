package syntaxtree;

/**
 * Created with IntelliJ IDEA.
 * User: Havard
 * Date: 09.03.13
 * Time: 19:09
 * To change this template use File | Settings | File Templates.
 */
public class Indent {

    public static String level = "";
    
    public static void inc() {
        level += "\t";
    }
    
    public static void dec() {
        level = level.substring(0,level.length()-1);
    }
}

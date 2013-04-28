package symboltable;

import syntaxtree.declaration.Decl;

import java.util.HashMap;
import java.util.HashSet;

/**
 * User: brynjagr
 * Date: 4/11/13
 * Time: 10:20 AM
 */
public class SymbolTable {
    public static HashSet<String> classTypes = new HashSet<String>();
    public static HashSet<String> basicTypes = new HashSet<String>();

    /* Inititalize of the static hashset of types */
    static {
        basicTypes.add("int");
        basicTypes.add("float");
        basicTypes.add("bool");
        basicTypes.add("string");
        basicTypes.add("null");

    }

    SymbolTable outerScopeSymbolTable;
    HashMap<String, Decl> localDecls;

    public SymbolTable(SymbolTable outerScopeSymbolTable) {
        this.outerScopeSymbolTable = outerScopeSymbolTable;
        this.localDecls = new HashMap<String, Decl>();
    }

    public void insertClassDecl(String className, Decl d) {
        localDecls.put(className, d);
        classTypes.add(className);
    }

    public void insertDecl(String varName, Decl d) {
        localDecls.put(varName, d);
    }

    public Decl getDecl(String key) {

        Decl d = localDecls.get(key);
        if (d == null) {

            if (outerScopeSymbolTable == null) {
                return null;
            }
            return outerScopeSymbolTable.getDecl(key);
        }

        return d;
    }

    public Decl getLocalDecl(String name) {
        return localDecls.get(name);
    }

    public static boolean containsType(String type) {
        return basicTypes.contains(type) || classTypes.contains(type);
    }

    public static boolean containsClassType(String type) {
        return classTypes.contains(type);
    }

    public void print() {

        if (outerScopeSymbolTable != null) {
            outerScopeSymbolTable.print();
        } else {
            System.out.println("Printing a symbol table");
        }

        for (String s : localDecls.keySet()) {
            System.out.println(s);
        }
    }

    public static void clearClassTypes() {
        classTypes = new HashSet<String>();
    }


}

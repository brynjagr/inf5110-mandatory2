package compiler;

import bytecode.CodeFile;
import error.Error;
import oblig1parser.*;
import symboltable.SymbolTable;
import syntaxtree.LibraryFunction;
import syntaxtree.Program;
import syntaxtree.declaration.ParamDecl;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Compiler {
    private String inFilename = null;
    private String astFilename = null;
    private String binFilename = null;
    public String syntaxError;
    public String error;

    /* Debug log */
    static StringBuilder logger = new StringBuilder();

    public static void log(Object o) {
        //Compiler.logger.append("CheckCode: " + o.getClass().getName() + "\n");
    }

    public static void plog(String msg) {
        System.out.println("-----------------------------------------");
        System.out.println("|            PRINT PLOG                 |");
        System.out.println("-----------------------------------------\n");
        System.out.println(msg);
        System.out.println("\n-----------------------------------------\n");
    }

    public Compiler(String inFilename, String astFilename, String binFilename){
        this.inFilename = inFilename;
        this.astFilename = astFilename;
        this.binFilename = binFilename;
    }
    public int compile() throws Exception {
        InputStream inputStream = null;
        inputStream = new FileInputStream(this.inFilename);
        Lexer lexer = new Lexer(inputStream);
        parser parser = new parser(lexer);
        Program program;

        try {
            program = (Program) parser.parse().value;
        } catch (Exception e) {
            // Do something here?
            throw e; // Or something.
        }

        // Check semanics.
        int retVal = 0;
        SymbolTable library;
        try {
            library = createLibrary();
            Error.clearErrorMessages();
            program.checkCode(library);
        } catch(error.Error e) {
            System.out.println("PROGRAM ERROR: error caught");
            retVal = 2;
        }

        if(program.getError()) {
            System.out.println("PROGRAM ERROR: something else");
            retVal = 2;
        }

        if (retVal == 2) {
            System.out.println("-----------------------------------------");
            System.out.println("|           ERROR MESSAGES               |");
            System.out.println("-----------------------------------------\n");
            System.out.println(Error.getErrorMessages());
            System.out.println("-----------------------------------------");
        } else {
            System.out.println("NO ERROR MESSAGES!\n");
        }

        /*Must clear the static class types */
        SymbolTable.clearClassTypes();

        if(retVal == 0){ // If it is all ok:
            writeAST(program);
            generateCode(program);
            return 0;
        } else if (retVal == 1){ // If there is a SYNTAX ERROR (Should not get that for the tests):
            return 1;
        } else { // If there is a SEMANTIC ERROR (Should get that for the test with "_fail" in the name):
            error = Error.getErrorMessages();
            return 2;
        }
    }

    private SymbolTable createLibrary() {
        SymbolTable library = new SymbolTable(null);

        List<ParamDecl> l = new LinkedList<ParamDecl>();
        l.add(new ParamDecl("int", null));
        library.insertDecl("printint", new LibraryFunction("printint", l));
        l = new LinkedList<ParamDecl>();
        l.add(new ParamDecl("float", null));
        library.insertDecl("printfloat", new LibraryFunction("printfloat", l));

        //Start here! Must add library fuctions in test4
        return library;
    }
    private void writeAST(Program program) throws Exception {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.astFilename));
        bufferedWriter.write(program.printAst());
        bufferedWriter.close();
    }
    private void generateCode(Program program) throws Exception {
        CodeFile codeFile = new CodeFile();
        program.generateCode(codeFile);
        byte[] bytecode = codeFile.getBytecode();
        DataOutputStream stream = new DataOutputStream(new FileOutputStream (this.binFilename));
        stream.write(bytecode);
        stream.close();
    }
    public static void main(String[] args) {
        Compiler compiler = new Compiler(args[0], args[1], args[2]);
        int result;
        try {
            result = compiler.compile();
            if(result == 1){
                System.out.println(compiler.syntaxError);
            } else if(result == 2){
                System.out.println(compiler.error);
            }
            System.exit(result);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            e.printStackTrace();
            // If unknown error.
            System.exit(3);
        }
    }
    public static String indent(int indent){
        String result = "";
        for(int i=0;i<indent; i++){
            result+=" ";
        }
        return result;
    }
}

package syntaxtree.expression.variable;

import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.instructions.*;
import error.VariableNotDeclaredError;
import symboltable.SymbolTable;
import syntaxtree.Indent;
import syntaxtree.declaration.Decl;
import syntaxtree.expression.Expression;

/**
 * User: Havard
 * Date: 10.03.13
 * Time: 11:58
 */
public class Variable extends Expression {

    String name;
    Expression value;
    Decl decl = null;

    public Variable(String name) {
        this.name = name;
        this.value = null;
    }

    @Override
    public String printAst() {
        return Indent.level + "(NAME " + name + ")\n";
    }

    @Override
    public void checkWhetherVariable() {
        // OK!
    }

    @Override
    public void checkCode(SymbolTable symbolTable) throws VariableNotDeclaredError {
        decl = symbolTable.getDecl(name);

        if (decl != null) {
        // OK!
        } else {
            throw new VariableNotDeclaredError(name);
        }
    }

    public void generateCode(CodeFile proc) {
	throw new UnsupportedOperationException();
    }

    public void generateInnerCode(CodeProcedure proc) {

	System.out.println(decl.getType());
	int num = proc.variableNumber(name);

	    System.out.println(name + " " + num);

	    // Global Variable
	    if(num == -1) {
		num = proc.globalVariableNumber(name);
		System.out.println(num);
		proc.addInstruction(new LOADGLOBAL(num));
	    } else {
		proc.addInstruction(new LOADLOCAL(num));
	    }
    }

    public void generateStoreCode(CodeProcedure proc) {

	System.out.println(decl.getType());
	int num = proc.variableNumber(name);

	    System.out.println(name + " " + num);

	    // Global Variable
	    if(num == -1) {
		num = proc.globalVariableNumber(name);
		System.out.println(num);
		proc.addInstruction(new STOREGLOBAL(num));
	    } else {
		proc.addInstruction(new STORELOCAL(num));
	    }
    }

    public void setValue(Expression e) {
        this.value = e;
    }

    @Override
    public String getType() throws VariableNotDeclaredError {

        if (decl == null) {
            throw new VariableNotDeclaredError(name);
        }

        return decl.getType();
    }

    @Override
    public Variable getVariable() {
        return this;
    }

    public Variable getVariabel() {
        return this;
    }


    public String getName() {
        return name;
    }

    public Decl getDecl() {
        return decl;
    }

    public void setDecl(Decl d) {
        this.decl = d;
    }
}

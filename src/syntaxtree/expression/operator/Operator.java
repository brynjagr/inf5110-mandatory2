package syntaxtree.expression.operator;

import bytecode.CodeProcedure;
import bytecode.instructions.*;
/**
 * User: Havard
 * Date: 09.03.13
 * Time: 18:22
 */
public class Operator {

    protected String val;
    protected String type;

    public Operator(String val) {
        this.val = val;
        this.type = "";
    }

    public String printAst() {
        return val + "\n";
    }

    public String getOperandType() {
        return this.type;
    }

    public void generateInnerCode(CodeProcedure proc) {
	switch (val) {
	case "/":
	    proc.addInstruction(new DIV());
	    break;
	case "#":
	    proc.addInstruction(new EXP());
	    break;
	case "+":
	    proc.addInstruction(new ADD());
	    break;
	case "-":
	    proc.addInstruction(new SUB());
	    break;
	case "*":
	    proc.addInstruction(new MUL());
	    break;
	case "<>":
	    proc.addInstruction(new NEQ());	    
	    break;
	case "=":
	    proc.addInstruction(new EQ());
	    break;
	case "<":
	    proc.addInstruction(new LT());
	    break;
	case "<=":
	    proc.addInstruction(new LTEQ());
	    break;
	case ">=":
	    proc.addInstruction(new GTEQ());
	    break;
	case ">":
	    proc.addInstruction(new GT());
	    break;
	default:
	    throw new UnsupportedOperationException("Invalid operator: " + val + " " + type + " " + this + " " + this.printAst());
	}
    }
}

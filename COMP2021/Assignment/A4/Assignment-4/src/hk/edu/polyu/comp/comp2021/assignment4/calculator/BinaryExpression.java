package hk.edu.polyu.comp.comp2021.assignment4.calculator;

/**
 * A binary expression.
 */
public class BinaryExpression extends Expression{

    private final Expression left, right;
    private final BinaryOperator binaryOperator;

    public BinaryExpression(Expression left, Expression right,
                            BinaryOperator binaryOperator) {
        this.left = left;
        this.right = right;
        this.binaryOperator = binaryOperator;
    }
	
    public int evaluate(Environment env) {
        return binaryOperator.calculate(left, right, env);
    }

    public String toString(){
        return "(" + left.toString() + binaryOperator.toString() + right.toString()+")";
    }


    
}

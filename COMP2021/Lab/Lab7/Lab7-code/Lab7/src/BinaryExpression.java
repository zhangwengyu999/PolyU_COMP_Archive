public class BinaryExpression extends Expression {
    //Adjust Operand class to Expression class
    private Expression left;
    private Expression right;
    private BinaryOperator binaryOperator;


    public BinaryExpression(Expression left, Expression right,
                            BinaryOperator binaryOperator) {
        this.left = left;
        this.right = right;
        this.binaryOperator = binaryOperator;
    }
    public int evaluate() {
        return binaryOperator.calculate(left, right);
    }

    public String toInfixString(){
        return "( "+left.toInfixString()+" "+binaryOperator.toString()+" "+right.toInfixString()+" )";
    }

    public String toPrefixString(){
        return binaryOperator.toString()+" "+left.toPrefixString()+" "+right.toPrefixString();
    }

    public String toPostfixString(){
        return left.toPostfixString()+" "+right.toPostfixString()+" "+binaryOperator.toString();
    }

}


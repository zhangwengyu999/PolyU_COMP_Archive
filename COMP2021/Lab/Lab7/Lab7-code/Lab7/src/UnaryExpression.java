public class UnaryExpression extends Expression{
    private Expression right;
    private UnaryOperator unaryOperator;

    public UnaryExpression(UnaryOperator unaryOperator, Expression right){
        this.unaryOperator=unaryOperator;
        this.right=right;
    }

    public int evaluate() {
        return unaryOperator.calculate(right);
    }

    public String toInfixString(){
        return unaryOperator.toString()+" "+right.toInfixString();
    }
    public String toPrefixString(){
        return unaryOperator.toString()+" "+right.toPrefixString();
    }
    public String toPostfixString(){
        return unaryOperator.toString()+" "+right.toPostfixString();
    }

}

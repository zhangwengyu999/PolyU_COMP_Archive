public class UnaryExpression {
    private Operand right;
    private UnaryOperator unaryOperator;

    public UnaryExpression(UnaryOperator unaryOperator, Operand right){
        this.unaryOperator=unaryOperator;
        this.right=right;
    }

    public int evaluate() {
        return unaryOperator.calculate(right);
    }

    public String toString(){
        return unaryOperator.toString()+" "+right.toString();
    }

    public static UnaryExpression fromString(String expString){

        Operand r;
        UnaryOperator uop;

        String[] tmp = expString.split(" ");
        uop=UnaryOperator.fromString(tmp[0]);
        r=Operand.fromString(tmp[1]);

        return new UnaryExpression(uop,r);
    }
}

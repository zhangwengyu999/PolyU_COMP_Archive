public class BinaryExpression {
    private Operand left;
    private Operand right;
    private BinaryOperator binaryOperator;


    public BinaryExpression(Operand left, Operand right,
                            BinaryOperator binaryOperator) {
        this.left = left;
        this.right = right;
        this.binaryOperator = binaryOperator;
    }
    public int evaluate() {
        return binaryOperator.calculate(left, right);
    }

    public String toString(){
        return left.toString()+" "+binaryOperator.toString()+" "+right.toString();
    }

    public static BinaryExpression fromString(String expString){
        Operand l;
        Operand r;
        BinaryOperator bop;

        String[] tmp = expString.split(" ");
        l=Operand.fromString(tmp[0]);
        bop=BinaryOperator.fromString(tmp[1]);
        r=Operand.fromString(tmp[2]);

        return new BinaryExpression(l,r,bop);
    }


}


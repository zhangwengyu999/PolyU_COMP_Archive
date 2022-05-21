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

    public static BinaryExpression fromString(String inStr) {
        String[] arrStr = inStr.split(" ");
        return new BinaryExpression(new Operand(Integer.parseInt(arrStr[0])),new Operand(Integer.parseInt(arrStr[2])),BinaryOperator.getOperator(arrStr[1]));
    }

    public String toString() {
        return left.toString()+" "+binaryOperator.toString()+" "+right.toString();
    }
}

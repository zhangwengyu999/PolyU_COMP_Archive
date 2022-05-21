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
}

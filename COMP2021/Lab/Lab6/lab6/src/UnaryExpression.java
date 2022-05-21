public class UnaryExpression {
    private Operand op;
    private UnaryOperator uo;
    public UnaryExpression(UnaryOperator inUo, Operand inOp) {
        this.uo = inUo;
        this.op = inOp;
    }
    public int evaluate() {
        return uo.calculator(op);
    }

    public static UnaryExpression fromString(String inStr) {
        String[] arrStr = inStr.split(" ");
        return new UnaryExpression(UnaryOperator.getOperator(arrStr[0]), new Operand(Integer.parseInt(arrStr[1])));
    }

    public String toString() {
        return uo.toString()+" "+op.toString();
    }
}

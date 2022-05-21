public class BinaryExpression {
    private Operand op1;
    private Operand op2;
    private BinaryOperator bo;
    BinaryExpression(Operand inOp1, Operand inOp2, BinaryOperator inBo){
        op1 = inOp1;
        op2 = inOp2;
        bo = inBo;
    }
    public int evaluate(){
        return bo.calculate(op1,op2);
    }
}

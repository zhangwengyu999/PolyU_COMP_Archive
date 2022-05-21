import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryExpressionTest {

    @Test
    public void evaluate() {
        Operand exp1 = new Operand(1);
        Operand exp2 = new Operand(2);
        //1+2
        BinaryExpression binaryExp1 = new BinaryExpression(exp1, exp2, BinaryOperator.ADD);
        assertEquals(3, binaryExp1.evaluate());
    }

    @Test
    public void evaluate1() {
        Operand exp1 = new Operand(4);
        Operand exp2 = new Operand(2);
        //1+2
        BinaryExpression binaryExp1 = new BinaryExpression(exp1, exp2, BinaryOperator.DIV);
        assertEquals(2, binaryExp1.evaluate());
    }
}
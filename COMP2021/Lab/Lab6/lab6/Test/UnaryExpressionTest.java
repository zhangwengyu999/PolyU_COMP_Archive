import org.junit.Test;

import static org.junit.Assert.*;

public class UnaryExpressionTest {

    @Test
    public void evaluate() {
        Operand exp = new Operand(2);
        UnaryExpression unaryExp = new UnaryExpression(UnaryOperator.POS,exp);
        assertEquals(2, unaryExp.evaluate());
    }

    @Test
    public void evaluate1() {
        Operand exp = new Operand(2);
        UnaryExpression unaryExp = new UnaryExpression(UnaryOperator.NEG,exp);
        assertEquals(-2, unaryExp.evaluate());
    }

    @Test
    public void StringTest() {
        String myExp = "+ 2";
        UnaryExpression exp = UnaryExpression.fromString(myExp);
        assertEquals(+2, exp.evaluate());
        assertEquals("+ 2", exp.toString());
    }

    @Test
    public void StringTest1() {
        String myExp = "- 2";
        UnaryExpression exp = UnaryExpression.fromString(myExp);
        assertEquals(-2, exp.evaluate());
        assertEquals("- 2", exp.toString());
    }

}
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExpressionTest {
    Expression exp1;
    Expression exp2;
    Expression exp3;

    @Before
    public void prepareExpressions(){
        exp1 = new Operand(1);
        exp2 = new Operand(2);
        exp3 = new Operand(3);
    }



    @Test
    public void evaluate1(){
        //1+2
        Expression BiExp1 = new BinaryExpression(exp1, exp2, BinaryOperator.ADD);
        //1+2+3
        Expression BiExp2 = new BinaryExpression(exp3, BiExp1,BinaryOperator.ADD);

        assertEquals(3, BiExp1.evaluate());
        assertEquals(6, BiExp2.evaluate());

    }

    @Test
    public void evaluate2(){
        Expression UnExp1=new UnaryExpression(UnaryOperator.MINUS,exp1);
        //(-1)+2
        Expression BiExp3=new BinaryExpression(UnExp1,exp2,BinaryOperator.ADD);
        //3+[(-1)+2]
        Expression BiExp4 = new BinaryExpression(exp3, BiExp3,BinaryOperator.ADD);
        System.out.println(BiExp4.evaluate());
    }

    @Test
    public void expression1(){
        Expression BiExp1 = new BinaryExpression(exp1, exp2, BinaryOperator.ADD);
        Expression BiExp2 = new BinaryExpression(exp3, BiExp1,BinaryOperator.ADD);

        System.out.println(BiExp2.toInfixString());
        System.out.println(BiExp2.toPrefixString());
        System.out.println(BiExp2.toPostfixString());
    }
}

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryExpressionTest {

    @Test
    public void StringTest(){
        String myExpression="10 / 2";
        BinaryExpression exp1=BinaryExpression.fromString(myExpression);
        System.out.println(exp1.evaluate());
        System.out.println(exp1.toString());
    }
}
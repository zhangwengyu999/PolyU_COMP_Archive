import org.junit.Test;

import static org.junit.Assert.*;

public class UnaryExpressionTest {
    @Test
    public void StringTest(){
        String myExpression="+ 2";
        UnaryExpression exp2=UnaryExpression.fromString(myExpression);
        System.out.println(exp2.evaluate());
        System.out.println(exp2.toString());
    }

}
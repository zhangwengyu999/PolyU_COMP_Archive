import org.junit.Test;

import static org.junit.Assert.*;

public class OperandTest {

    @Test
    public void evaluate() {
        Operand exp1 = new SingleOperand(1);
        Operand exp2 = new SingleOperand(2);
        //1+2
        Operand binaryExp1 = new BinaryExpression(exp1, exp2, BinaryOperator.ADD);
        assertEquals(3, binaryExp1.evaluate());
    }

    @Test
    public void evaluate1() {
        Operand exp1 = new SingleOperand(1);
        //+1
        Operand binaryExp1 = new UnaryExpression(BinaryOperator.ADD,exp1);
        assertEquals(1, binaryExp1.evaluate());
    }

    @Test
    public void evaluate2() {
        Operand exp1 = new SingleOperand(2);
        //-2
        Operand binaryExp1 = new UnaryExpression(BinaryOperator.SUB,exp1);
        assertEquals(-2, binaryExp1.evaluate());
    }

    @Test
    public void evaluate3() {
        Operand exp1 = new SingleOperand(1);
        //1
        assertEquals(1, exp1.evaluate());
    }

    @Test
    public void StringTest1(){
        String myExpression="10 / 2";
        Operand exp1 = Operand.fromString(myExpression);
        System.out.println(exp1.evaluate());
        System.out.println(exp1.toString());
    }

    @Test
    public void StringTest2(){
        String myExpression="+ 2";
        Operand exp2 = Operand.fromString(myExpression);
        System.out.println(exp2.evaluate());
        System.out.println(exp2.toString());
    }

    @Test
    public void StringTest3(){
        String myExpression="2";
        Operand exp2 = Operand.fromString(myExpression);
        System.out.println(exp2.evaluate());
        System.out.println(exp2.toString());
    }
}
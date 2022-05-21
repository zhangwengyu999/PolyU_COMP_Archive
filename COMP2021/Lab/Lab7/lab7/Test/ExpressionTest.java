import org.junit.Before;
import org.junit.Test;

import java.security.spec.ECParameterSpec;

import static org.junit.Assert.*;

public class ExpressionTest {


    Expression opA = new Operand(1);
    Expression opB = new Operand(2);
    Expression opC = new Operand(3);
    Expression opD = new Operand(4);
    // 1 + 2
    Expression expAB = new BinaryExpression(opA,opB,BinaryOperator.ADD);
    // 2 * 3
    Expression expBC = new BinaryExpression(opB,opC,BinaryOperator.MUL);
    // 1 + 2 * 3
    Expression expABC = new BinaryExpression(opA,expBC,BinaryOperator.ADD);
    // 1 + 2 * 3 + 4
    Expression expABCD = new BinaryExpression(expABC,opD,BinaryOperator.ADD);
    // ~ 1
    Expression expUn1 = new UnaryExpression(UnaryOperator.MINUS,opA);
    // ~ (1 + 2)
    Expression expUn2 = new UnaryExpression(UnaryOperator.MINUS,expAB);
    // # (1 + 2 * 3 + 4)
    Expression expUn3 = new UnaryExpression(UnaryOperator.PLUS,expABCD);


    /** Test for BinaryExpression*/
    @Test
    public void evaluate() {
        System.out.println(expAB.evaluate());
        System.out.println(expBC.evaluate());
        System.out.println(expABC.evaluate());
        System.out.println(expABCD.evaluate());
    }

    /** Test for BinaryExpression*/
    @Test
    public void toInfixString() {

        System.out.println(expABCD.toInfixString());
    }

    /** Test for BinaryExpression*/
    @Test
    public void toPrefixString() {

        System.out.println(expABCD.toPrefixString());
    }

    /** Test for BinaryExpression*/
    @Test
    public void toPostfixString() {

        System.out.println(expABCD.toPostfixString());
    }

    /** Test for UnaryExpression*/
    @Test
    public void evaluate1() {

        System.out.println(expUn1.evaluate());
        System.out.println(expUn2.evaluate());
        System.out.println(expUn3.evaluate());
    }

    /** Test for UnaryExpression*/
    @Test
    public void toInfixString1() {

        System.out.println(expUn1.toInfixString());
        System.out.println(expUn2.toInfixString());
        System.out.println(expUn3.toInfixString());
    }

    /** Test for UnaryExpression*/
    @Test
    public void toPrefixString1() {

        System.out.println(expUn1.toPrefixString());
        System.out.println(expUn2.toPrefixString());
        System.out.println(expUn3.toPrefixString());
    }

    /** Test for UnaryExpression*/
    @Test
    public void toPostfixString1() {

        System.out.println(expUn1.toPostfixString());
        System.out.println(expUn2.toPostfixString());
        System.out.println(expUn3.toPostfixString());
    }

}
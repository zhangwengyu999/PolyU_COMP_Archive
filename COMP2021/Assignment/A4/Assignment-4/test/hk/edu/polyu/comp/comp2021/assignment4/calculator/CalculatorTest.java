package hk.edu.polyu.comp.comp2021.assignment4.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Calculator.
 */
public class CalculatorTest {

    Expression l1, l2, l3;
    Expression b1, b2, b3;

    Variable v1;
    Expression b4;
    Environment e1;

    Expression b5;

    /** additional test*/
//    Variable v2;
//    Expression b42;
//    Variable v3;

    @BeforeEach
    public void prepare(){
        l1 = new Literal(1);
        l2 = new Literal(3);
        l3 = new Literal(5);

        b1 = new BinaryExpression(l2, l1, BinaryOperator.DIVIDE);
        b2 = new BinaryExpression(b1, l3, BinaryOperator.ADD);
        b3 = new BinaryExpression(b1, b2, BinaryOperator.MULTIPLY);

        v1 = new Variable("a");
        b4 = new BinaryExpression(b2, v1, BinaryOperator.DIVIDE);
        e1 = new Environment();
        e1.defineVariable(v1, 2);

        b5 = new BinaryExpression(l3, l1, BinaryOperator.MINUS);

        /** additional test*/
        //v3 = new Variable("");
        //v3 = new Variable(null);
        //v3 = new Variable("10");
//        v2 = new Variable("b");
//        b42 = new BinaryExpression(b2, v2, BinaryOperator.DIVIDE);
//        e1.defineVariable(v2, 2);
    }

    @Test
    public void testLiteral(){
        assertEquals("3", l2.toString());
        assertEquals(3, l2.evaluate(null));
    }

    @Test
    public void testBinary(){
        assertEquals("(3/1)", b1.toString());
        assertEquals("((3/1)+5)", b2.toString());

        assertEquals(8, b2.evaluate(null));
        assertEquals(24, b3.evaluate(null));
    }

    @Test
    public void testMinus() {
        assertEquals("(5-1)", b5.toString());
        assertEquals(4, b5.evaluate(null));
    }

    @Test
    public void testVariable(){
        assertEquals("(((3/1)+5)/a)", b4.toString());
        assertEquals(4, b4.evaluate(e1));
    }

    /** additional test*/
    @Test
    public void testVariable2(){
//        assertEquals("(((3/1)+5)/b)", b42.toString());
//        assertEquals(4, b42.evaluate(e1));
        //e1.getValue(v2);
    }
}

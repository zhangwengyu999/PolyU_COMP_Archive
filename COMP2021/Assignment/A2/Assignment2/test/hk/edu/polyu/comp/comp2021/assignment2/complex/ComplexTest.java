package hk.edu.polyu.comp.comp2021.assignment2.complex;

import org.junit.Test;
import org.junit.Before;


import static org.junit.Assert.*;

public class ComplexTest {
    @Test
    public void testConstructor() {
        Rational real = new Rational(1, 2);
        Rational imag = new Rational(1, 3);

        Complex c1 = new Complex(real, imag);

        assertEquals(c1.toString(), "(1/2,1/3)");
    }

    @Test
    public void testAddition() {
        Complex c1 = new Complex(new Rational(1, 2), new Rational(1, 3));
        Complex c2 = new Complex(new Rational(2, 3), new Rational(1, 4));

        Complex cSum = c1.add(c2);
        cSum.simplify();

        assertEquals(cSum.toString(), "(7/6,7/12)");
    }

    @Test
    public void testSubstraction() {
        Complex c1 = new Complex(new Rational(1, 2), new Rational(2, 3));
        Complex c2 = new Complex(new Rational(1, 3), new Rational(1, 4));

        Complex cSum = c1.subtract(c2);
        cSum.simplify();

        assertEquals(cSum.toString(), "(1/6,5/12)");
    }

    @Test
    public void testMultiplication() {
        Complex c1 = new Complex(new Rational(1, 4), new Rational(3, 7));
        Complex c2 = new Complex(new Rational(1, 5), new Rational(5, 8));

        Complex cMul = c1.multiply(c2);
        cMul.simplify();

        assertEquals(cMul.toString(), "(-61/280,271/1120)");
    }


    @Test
    public void testMultiplication_02() {
        Complex c1 = new Complex(new Rational(0, 1), new Rational(1, 1));
        Complex c2 = new Complex(new Rational(1, 5), new Rational(5, 8));

        Complex cMul = c1.multiply(c2);
        cMul.simplify();

        assertEquals(cMul.toString(), "(-5/8,1/5)");
    }

    @Test
    public void testDivision() {
        Complex c1 = new Complex(new Rational(2, 5), new Rational(3, 7));
        Complex c2 = new Complex(new Rational(1, 3), new Rational(1, 6));

        Complex cDiv = c1.divide(c2);
        cDiv.simplify();

        assertEquals(cDiv.toString(), "(258/175,96/175)");
    }

    @Test
    public void testConstructor_ZERO() {
        Complex c2 = new Complex(new Rational(0, 2), new Rational(0, 3));
        c2.simplify();
        assertEquals(c2.toString(), "(0/1,0/1)");
    }

}

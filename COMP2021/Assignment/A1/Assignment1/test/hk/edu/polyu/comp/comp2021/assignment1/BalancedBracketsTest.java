package hk.edu.polyu.comp.comp2021.assignment1;

import org.junit.Test;

import static org.junit.Assert.*;

public class BalancedBracketsTest {

    @Test
    public void test1() {
        assertTrue(BalancedBrackets.isBalanced("()"));
    }

    @Test
    public void test2() {
        assertTrue(BalancedBrackets.isBalanced("(){}"));
    }

    @Test
    public void test3() {
        assertTrue(BalancedBrackets.isBalanced("({})"));
    }

    @Test
    public void test4() {
        assertFalse(BalancedBrackets.isBalanced("(("));
    }

    @Test
    public void test5() {
        assertFalse(BalancedBrackets.isBalanced("({)}"));
    }

    @Test
    public void test6() {
        assertFalse(BalancedBrackets.isBalanced("(){"));
    }

    @Test
    public void test7() {
        assertTrue(BalancedBrackets.isBalanced("(()[{}])"));
    }

    @Test
    public void test8() {assertFalse(BalancedBrackets.isBalanced("()[.]"));}

    @Test
    public void test9() {assertFalse(BalancedBrackets.isBalanced(""));}

}

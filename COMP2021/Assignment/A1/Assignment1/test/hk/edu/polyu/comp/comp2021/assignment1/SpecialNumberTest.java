package hk.edu.polyu.comp.comp2021.assignment1;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpecialNumberTest {

    @Test
    public void test1() {
        assertTrue(SpecialNumber.isSpecial(30));
    }

    @Test
    public void test2() {
        assertFalse(SpecialNumber.isSpecial(210));
    }

    @Test
    public void test3() {
        assertFalse(SpecialNumber.isSpecial(4));
    }

    @Test
    public void test4() {
        assertFalse(SpecialNumber.isSpecial(-30));
    }

    @Test
    public void test5() {
        assertFalse(SpecialNumber.isSpecial(0));
    }

}

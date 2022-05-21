package hk.edu.polyu.comp.comp2021.assignment1;

import org.junit.Test;

public class MiniFloatTest {
    public static float DELTA = 1E-6f;

    @Test
    public void test1() {
        assert (Math.abs(MiniFloat.miniFloatFromString("00100110") - 28) < DELTA);
    }

    @Test
    public void test2() {
        assert (Math.abs(MiniFloat.miniFloatFromString("10100110") - -28) < DELTA);
    }

    @Test
    public void test3() {
        assert (Math.abs(MiniFloat.miniFloatFromString("00000000") - 1) < DELTA);
    }

    @Test
    public void test4() {
        assert (Math.abs(MiniFloat.miniFloatFromString("10000000") - -1) < DELTA);
    }

    @Test
    public void test5() {
        assert (Math.abs(MiniFloat.miniFloatFromString("00001000") - 2) < DELTA);
    }

    @Test
    public void test6() {
        assert (Math.abs(MiniFloat.miniFloatFromString("00001100") - 3) < DELTA);
    }

    @Test
    public void test7() {
        assert (Math.abs(MiniFloat.miniFloatFromString("10111111") - -240) < DELTA);
    }

    // additional test for a number with negative exponent
    @Test
    public void test8_Neg_Exp() {
        assert (Math.abs(MiniFloat.miniFloatFromString("11111110") - -0.875) < DELTA);
    }

}

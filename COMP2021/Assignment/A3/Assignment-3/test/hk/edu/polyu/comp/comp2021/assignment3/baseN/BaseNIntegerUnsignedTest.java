package hk.edu.polyu.comp.comp2021.assignment3.baseN;

import org.junit.Before;
import org.junit.Test;
import static hk.edu.polyu.comp.comp2021.assignment3.baseN.BaseNIntegerUnsigned.*;

import static org.junit.Assert.*;

public class BaseNIntegerUnsignedTest {
    int v0 = 0;
    int v1 = 0;
    int v2 = 1;
    int v3 = 160;
    int v4 = 95;
    int v5 = 32;

    int base1 = 11;
    int base2 = 5;

    BaseNIntegerUnsigned u0;
    BaseNIntegerUnsigned u1;
    BaseNIntegerUnsigned u2;
    BaseNIntegerUnsigned u3;
    BaseNIntegerUnsigned u4;
    BaseNIntegerUnsigned u5;

    String u1Magnitude = "A";
    String u2Magnitude = "B";
    String u3Magnitude = "BDG";
    String u4Magnitude = "DEA";
    String u5Magnitude = "BBC";

    String u1String = "A(11)";
    String u2String = "B(11)";
    String u3String = "BDG(11)";
    String u4String = "DEA(5)";
    String u5String = "BBC(5)";

    @Before
    public void before(){
        u0 = new BaseNIntegerUnsigned(encode(v0, base1), base1);
        u1 = new BaseNIntegerUnsigned(DIGIT_ZERO + encode(v1, base1), base1);
        u2 = new BaseNIntegerUnsigned(DIGIT_ZERO + (DIGIT_ZERO + encode(v2, base1)), base1);
        u3 = new BaseNIntegerUnsigned(encode(v3, base1), base1);

        u4 = new BaseNIntegerUnsigned(encode(v4, base2), base2);
        u5 = new BaseNIntegerUnsigned(encode(v5, base2), base2);
    }

    @Test
    public void testConstructor01(){
        assertEquals(v0, u0.toInteger());
        assertEquals(v1, u1.toInteger());
        assertEquals(v2, u2.toInteger());
        assertEquals(v3, u3.toInteger());
        assertEquals(v4, u4.toInteger());
        assertEquals(v5, u5.toInteger());
    }

    @Test
    public void testIsValidBase01() {
        assertTrue(BaseNIntegerUnsigned.isValidBase(base1));
        assertTrue(BaseNIntegerUnsigned.isValidBase(base2));
        assertFalse(BaseNIntegerUnsigned.isValidBase(0));
        assertFalse(BaseNIntegerUnsigned.isValidBase(-1));
        assertFalse(BaseNIntegerUnsigned.isValidBase(27));
        assertFalse(BaseNIntegerUnsigned.isValidBase(1));
        assertTrue(BaseNIntegerUnsigned.isValidBase(2));
        assertTrue(BaseNIntegerUnsigned.isValidBase(26));

    }

    @Test
    public void testIsValidMagnitude01() {
        assertTrue(BaseNIntegerUnsigned.isValidMagnitude(u1Magnitude, base1));
        assertFalse(BaseNIntegerUnsigned.isValidMagnitude(u3Magnitude, base2));
        // test for Magnitude is still valid even it CANNOT be represented in integer, BDDEECCDEDEAED is 2147483647(Integer.MAX_VALUE) + 1 in base 5
        assertTrue(BaseNIntegerUnsigned.isValidMagnitude("BDDEECCDEDEAED",5));
        assertTrue(BaseNIntegerUnsigned.isValidMagnitude("DEBDDEECCDEDEAED",5));
        assertFalse(BaseNIntegerUnsigned.isValidMagnitude("DFBDDEECCDEDEAED",5));
        assertFalse(BaseNIntegerUnsigned.isValidMagnitude(null,5));
        assertFalse(BaseNIntegerUnsigned.isValidMagnitude("",5));
        assertFalse(BaseNIntegerUnsigned.isValidMagnitude("bcda",5));
        assertFalse(BaseNIntegerUnsigned.isValidMagnitude("BCDA",0));
    }

    @Test
    public void testToInteger01(){
        assertEquals(v3, u3.toInteger());
        assertEquals(v4, u4.toInteger());
        // test for one CANNOT be represented in integer, BDDEECCDEDEAED is 2147483647(Integer.MAX_VALUE) + 1 in base 5
        assertFalse(new BaseNIntegerUnsigned("BDDEECCDEDEAED",5).canBeRepresentedInInteger());
        assertFalse(new BaseNIntegerUnsigned("BBDDEECCDEDEAED",5).canBeRepresentedInInteger());
        assertTrue(new BaseNIntegerUnsigned("DDEECCDEDEAED",5).canBeRepresentedInInteger());
    }

    @Test
    public void testToString01(){
        assertEquals(u1String, u1.toString());
        assertEquals(u2String, u2.toString());
        assertEquals(u3String, u3.toString());
    }

    @Test
    public void testEquals01(){
        assertEquals(u0, u1);
        assertNotEquals(u1, u2);
        assertNotEquals(u1, new Object());
        // test when they CANNOT be represented in integer, BDDEECCDEDEAED is 2147483647(Integer.MAX_VALUE) + 1 in base 5
        assertEquals(new BaseNIntegerUnsigned("BDDEECCDEDEAED",5), new BaseNIntegerUnsigned("BDDEECCDEDEAED",5));
        assertNotEquals(new BaseNIntegerUnsigned("BDDEECCDEDEAED",5), u2);
        assertEquals(new BaseNIntegerUnsigned("DEBDDEECCDEDEAED",5), new BaseNIntegerUnsigned("DEBDDEECCDEDEAED",5));
        assertNotEquals(new BaseNIntegerUnsigned("DDEECCDEDEAED",5), new BaseNIntegerUnsigned("BDDEECCDEDEAED",5));
        assertNotEquals(new BaseNIntegerUnsigned("BCDEECCDEDEAED",5), new BaseNIntegerUnsigned("BDDEECCDEDEAED",5));

    }

    @Test
    public void testCompare01(){
        assertTrue(u1.compare(u2) < 0);
        assertTrue(u2.compare(u1) > 0);
        assertTrue(u0.compare(u1) == 0);
        // test when they CANNOT be represented in integer, BDDEECCDEDEAED is 2147483647(Integer.MAX_VALUE) + 1 in base 5
        assertTrue(new BaseNIntegerUnsigned("BDDEECCDEDEAED",5).compare(new BaseNIntegerUnsigned("BDDEECCDEDEAED",5)) == 0);
        assertTrue(new BaseNIntegerUnsigned("BDDEECCDEDEAED",5).compare(new BaseNIntegerUnsigned("BDDEECCDEDEAEE",5)) < 0);
        assertTrue(new BaseNIntegerUnsigned("BDDEECCDEDEAEE",5).compare(new BaseNIntegerUnsigned("BDDEECCDEDEAED",5)) > 0);
        assertTrue(new BaseNIntegerUnsigned("EBDDEECCDEDEAEE",5).compare(new BaseNIntegerUnsigned("EBDDEECCDEDEAEE",5)) == 0);
        assertTrue(new BaseNIntegerUnsigned("EBDDEECCDEDEAEE",5).compare(new BaseNIntegerUnsigned("EBCDEECCDEDEAED",5)) > 0);
        assertFalse(new BaseNIntegerUnsigned("EBDDEECCDEDEAEE",5).compare(new BaseNIntegerUnsigned("EBCDEECCDEDEAED",5)) == 0);
        assertTrue(new BaseNIntegerUnsigned("EBADEECCDEDEAED",5).compare(new BaseNIntegerUnsigned("EBCDEECCDEDEAED",5)) < 0);
        assertTrue(new BaseNIntegerUnsigned("EDEBDDEECCDEDEAEE",5).compare(new BaseNIntegerUnsigned("DEEEBCDEECCDEDEAED",5)) < 0);

    }

    @Test
    public void testEncode01(){
        assertEquals(u2Magnitude, BaseNIntegerUnsigned.encode(v2, base2));
        assertEquals(u3Magnitude, BaseNIntegerUnsigned.encode(v3, base1));
    }

    @Test
    public void testAdd01(){
        assertEquals(v2 + v3, u2.add(u3).toInteger());
        assertEquals(v1 + v2, u1.add(u2).toInteger());
        // test for overflow and carry out:
        assertEquals(82 + 113, new BaseNIntegerUnsigned(encode(82, 5), 5).add(new BaseNIntegerUnsigned(encode(113, 5), 5)).toInteger());
        // test when an addend CANNOT be represented in integer, BDDEECCDEDEAED is 2147483647(Integer.MAX_VALUE) + 1 in base 5
        assertEquals(new BaseNIntegerUnsigned("BDDEECCDEDEAEE",5), new BaseNIntegerUnsigned("BDDEECCDEDEAED",5).add(new BaseNIntegerUnsigned("B",5)));
        assertEquals(new BaseNIntegerUnsigned("EBDDEECCDEDEAED",5), new BaseNIntegerUnsigned("BDDEECCDEDEAED",5).add(new BaseNIntegerUnsigned("EAAAAAAAAAAAAAA",5)));
        assertEquals(new BaseNIntegerUnsigned("EBDDEECCDEDEAEC",5), new BaseNIntegerUnsigned("BDDEECCDEDEAED",5).add(new BaseNIntegerUnsigned("DEEEEEEEEEEEEEE",5)));
        assertNotEquals(new BaseNIntegerUnsigned("EBDDEECCDEDEAED",5), new BaseNIntegerUnsigned("BDDEECCDEDEAED",5).add(new BaseNIntegerUnsigned("DEEEEEEEEEEEEEE",5)));
    }

    @Test
    public void testSubtract01(){
        assertEquals(v2 - v1, u2.subtract(u1).toInteger());
        assertEquals(v4 - v5, u4.subtract(u5).toInteger());
        assertEquals(new BaseNIntegerUnsigned("CCBB",5), new BaseNIntegerUnsigned("CCCC",5).subtract(new BaseNIntegerUnsigned("BB",5)));
        assertEquals(new BaseNIntegerUnsigned("CBDE",5), new BaseNIntegerUnsigned("CCCC",5).subtract(new BaseNIntegerUnsigned("DD",5)));
        // test when the minuend CANNOT be represented in integer, BDDEECCDEDEAED is 2147483647(Integer.MAX_VALUE) + 1 in base 5
        assertEquals(new BaseNIntegerUnsigned("BDDEECCDEDEAEC",5), new BaseNIntegerUnsigned("BDDEECCDEDEAED",5).subtract(new BaseNIntegerUnsigned("B",5)));
        assertEquals(new BaseNIntegerUnsigned("BDDEECCDEDEAED",5), new BaseNIntegerUnsigned("EBDDEECCDEDEAED",5).subtract(new BaseNIntegerUnsigned("EAAAAAAAAAAAAAA",5)));
        assertEquals(new BaseNIntegerUnsigned("BDDEECCDEDEAEE",5), new BaseNIntegerUnsigned("EBDDEECCDEDEAED",5).subtract(new BaseNIntegerUnsigned("DEEEEEEEEEEEEEE",5)));
        assertNotEquals(new BaseNIntegerUnsigned("BDDEECCDEDEAEC",5), new BaseNIntegerUnsigned("EBDDEECCDEDEAED",5).subtract(new BaseNIntegerUnsigned("DEEEEEEEEEEEEEE",5)));
    }

}

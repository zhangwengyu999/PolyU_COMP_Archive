package hk.edu.polyu.comp.comp2021.assignment2.charintmap;


import org.junit.Test;
import org.junit.Before;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;


public class CharIntMapTest {
    CharIntMap map1, map2, other;

    @Before
    public void init() {
        map1 = new CharIntMap(4);
        map2 = new CharIntMap(5);
        other = new CharIntMap(5);

        map1.put('a', 1);
        map1.put('b', 2);
        map1.put('c', 3);

        map2.put('d', 4);
    }

    @Test
    public void testIsFull() {
        map1.put('d', 4);
        assertTrue(map1.isFull());
        assertFalse(map2.isFull());
    }

    @Test
    public void testGetValue() {
        assertEquals(map1.getValue('c'), 3);
    }


    @Test
    public void testPut() {
        map1.put('d', 4);
        assertEquals(map1.keyString(), "abcd");
    }


    @Test
    public void testRemove() {
        map1.remove('c');

        other.put('a', 2);
        other.put('b', 3);

        assertTrue(map1.hasSameKeys(other));
    }



    @Test
    public void testHasSameKeys() {
        assertFalse(map1.hasSameKeys(map2));
    }

    @Test
    public void testHasSameKeys_02() {
        other.put('c', 2);
        other.put('b', 3);
        other.put('a', 4);

        assertTrue(map1.hasSameKeys(other));
    }

    @Test
    public void testContainsKey() {
        assertTrue(map1.containsKey('a'));
        assertTrue(map1.containsKey('b'));
    }


    @Test
    public void testContainsValue() {
        assertTrue(map1.containsValue(1));
    }


    @Test
    public void testMerge() {
        map1.merge('a', 10);

        assertTrue(map1.containsValue(11));
    }

    @Test
    public void testMerge_02() {
        map1.merge('d', 20);

        assertTrue(map1.containsValue(20));
    }




    @Test
    public void testReplace() {
        map1.replace('a', 2);

        assertEquals(map1.getValue('a'), 2);
    }

    @Test
    public void testReplace_02() {
        map1.replace('e', 100);

        assertFalse(map1.containsValue(100));
    }

    @Test // additional test Put() when there is empty element in the list except only in the end of the list
    public void testPut_02() {
        map2.put('b', 4);
        map2.put('c', 5);
        map1.remove('a'); // remove the head one in the list
        map1.put('d', 6);

        map1.remove('b'); // remove the middle one in the list
        map2.remove('b'); // remove the middle one in the list
        map1.put('e', 7);
        map2.put('e', 7);
        assertTrue(map1.hasSameKeys(map2));
    }

}


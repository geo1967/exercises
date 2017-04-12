/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prime.algos;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author georgemullen
 */
public class SieveOfSundaramTest {

    static final int[] expected_primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    static final int maxLimit = 100;
    private SieveOfSundaram testClazz;

    public SieveOfSundaramTest() {
    }

    @Before
    public void setUp() {
        this.testClazz = new SieveOfSundaram();
    }

    @After
    public void tearDown() {
    }

   

    @Test
    public void sundNum() {

        assertEquals(4, testClazz.sundNum(1, 1));
        assertEquals(7, testClazz.sundNum(1, 2));
        assertEquals(10, testClazz.sundNum(1, 3));
        assertEquals(12, testClazz.sundNum(2, 2));
        assertEquals(17, testClazz.sundNum(2, 3));

    }

    @Test
    public void sundNumPartStream() {
        List<Integer> actual = testClazz.sundNumPartStream(4).boxed().collect(Collectors.toList());
        List<Integer> expected = Arrays.asList(testClazz.sundNum(1, 4), testClazz.sundNum(2, 4), testClazz.sundNum(3, 4), testClazz.sundNum(4, 4));
        assertEquals(expected, actual);            
    }

     @Test
    public void getPrimes_in_range() {
        final int min = 4;
        final int max = 23;
        int[] actual = testClazz.getPrimes(min, max).toArray();
        int[] expected = {5, 7, 11, 13, 17, 19, 23};
        assertArrayEquals(expected, actual);
    }
    
    
    @Test
    public void primes() {
        int[] actual = testClazz.getPrimes(1, maxLimit).toArray();
        int[] expected = expected_primes;
        assertArrayEquals(expected, actual);     
    }

    
    
    
}

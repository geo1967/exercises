/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prime.algos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author georgemullen
 */
public class SpoofSegmentedSieveTest {

    public SpoofSegmentedSieveTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGenIndices() {
        System.out.println("genPrimes");
        int[][] expResult = {{0, 6}, {7, 13}, {14, 20}, {21, 29}};
        int[][] result = SpoofSegmentedSieve.genIndices(30, 4);
        assertArrayEquals(expResult, result);

    }

    @Test
    public void testDispatchCalculation() throws Exception {
        SpoofSegmentedSieve testClazz = new SpoofSegmentedSieve();
        Consumer< Set<Integer>> mockAccumulator = mock(Consumer.class);
        final CountDownLatch countdownLatch = new CountDownLatch(1);
        doAnswer(new Answer() {
	    public Object answer(InvocationOnMock invocation) {
	       countdownLatch.countDown();
	        return null;
	    }}).when(mockAccumulator).accept(any(Set.class));
               

        int[] limits = new int[]{7, 13};
        testClazz.dispatchCalculation(limits, mockAccumulator);
        Set<Integer> expected = new HashSet<>(Arrays.asList(7, 11, 13));

        countdownLatch.await(10, TimeUnit.SECONDS);
        verify(mockAccumulator).accept(eq(expected));

    }

}

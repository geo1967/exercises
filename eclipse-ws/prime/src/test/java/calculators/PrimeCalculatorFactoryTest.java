/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import prime.algos.SieveOfSundaram;
import prime.algos.TrialDivision;
/**
 *
 * @author georgemullen
 */
public class PrimeCalculatorFactoryTest {
    private PrimeCalculatorFactory testClazz;

    public PrimeCalculatorFactoryTest() {
    }

    @Before
    public void setUp() {
        this.testClazz = new PrimeCalculatorFactory();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetCalculator_SieveOfSundaram() {
        String algo = "SieveOfSundaram";
        Class<SieveOfSundaram> expResult = SieveOfSundaram.class;
        PrimeGeneratorI result = testClazz.getCalculator(algo);
        assertEquals(expResult, result.getClass());
    }

    @Test
    public void testGetCalculator_TrialDivision() {
        String algo = "TrialDivision";
        Class<TrialDivision> expResult = TrialDivision.class;
        PrimeGeneratorI result = testClazz.getCalculator(algo);
        assertEquals(expResult, result.getClass());

    }

    @Test
    public void testGetCalculator_UnknownAlgo() {
        String algo = "UnknownAlgo";
        Class<TrialDivision> expResult = TrialDivision.class;
        PrimeGeneratorI result = testClazz.getCalculator(algo);
        assertEquals(expResult, result.getClass());
    }

}

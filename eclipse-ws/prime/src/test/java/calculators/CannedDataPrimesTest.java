/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author georgemullen
 */
public class CannedDataPrimesTest {

    private int maximumPrimeLimit;
    private CannedDataPrimes testClazz;
    private File testPrimesDataFile;

    public CannedDataPrimesTest() {
    }

    @Before
    public void setUp() {
        maximumPrimeLimit = 20;
        testClazz = new CannedDataPrimes(maximumPrimeLimit);
         testPrimesDataFile = new File("cannedDataPrimesTest_Primes.data");
    }

    @After
    public void tearDown() throws IOException {
        Files.deleteIfExists(testPrimesDataFile.toPath());
    }

    @Test
    public void testReadFromFile() {     
        testClazz.writeToFile(testPrimesDataFile);
        int[] expResult = {2,3, 5, 7, 11, 13, 17, 19};;
        int[] result = testClazz.readFromFile(testPrimesDataFile);
        assertArrayEquals(expResult, result);
       
    }

    @Test
    public void testWriteToFile() {     
        testClazz.writeToFile(testPrimesDataFile);
        assertTrue(testPrimesDataFile.exists());
         assertTrue(testPrimesDataFile.length() > 0);

    }

    @Test
    public void testPrimes() {
        int max = 0;
        CannedDataPrimes instance = new CannedDataPrimes(maximumPrimeLimit);
        int[] expResult = {2,3, 5, 7, 11, 13, 17, 19};
        int[] result = instance.primes(max);
        assertArrayEquals(expResult, result);
    }

}

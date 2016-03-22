/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prime.algos;

import calculators.PrimeGeneratorI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.*;

/**
 *
 * @author georgemullen
 */
/**
 * This class is not strictly thread safe owing to shared state holding the set of discovered primes 
 * and on the implicit ordering of the stream of natural numbers 
 * @author georgemullen
 */
public class TrialDivision implements PrimeGeneratorI {

    private final Set<Integer> primesSet = new ConcurrentSkipListSet<Integer>(Arrays.asList(2));

    private boolean isPrime(final int val) {
        boolean res = false;
        boolean isCompoundNumber = primesSet.stream().anyMatch((x) -> val % x == 0);
        if (!isCompoundNumber) {
            primesSet.add(val);
        }
        return !isCompoundNumber;
    }

    
    /**
     * Searches all odd integers  for values not divisible by primes already accumulated
     * @param max limiting value which maximum prime number must not exceed
     * @return array of primes up to limiting value specified.
     */
    public int[] primes(int max) {
        IntStream numbers = IntStream.range(3, max).filter((x) -> x % 2 == 1).filter((x) -> isPrime(x));
        numbers = IntStream.concat(IntStream.of(2), numbers); // add back the only even prime       
        return numbers.toArray();
    }

}

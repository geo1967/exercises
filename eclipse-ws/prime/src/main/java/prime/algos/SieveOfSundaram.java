package prime.algos;

import calculators.PrimeGeneratorException;
import calculators.PrimeGeneratorI;
import java.util.Set;
import java.util.stream.*;

/**
 * Note the implementation is STATELESS and therefore can safely be called by multiple threads concurrently
 * @author georgemullen
 */
public class SieveOfSundaram implements PrimeGeneratorI {

    /*
    * SieveOfSundaram Method - see https://en.wikipedia.org/wiki/Sieve_of_Sundaram
    * Start with a list of the integers from 1 to n. 
    * From this list, remove all numbers of the form i + j + 2ij 
    * where:
            i,j in Natural numbers , 1 <= i <= j
            i + j + 2i*j <= n

     The remaining numbers are doubled and incremented by one, giving a list of the odd prime numbers (i.e., all primes except 2) below 2n + 2. 
     * @param max
     * @return 
     */
    public int[] primes(int max) throws PrimeGeneratorException{
        try
        {
            return getPrimes(1, max).toArray();
        }
        catch(Exception ex)
        {
            throw new PrimeGeneratorException("Failed SieveOfSundaram sieve calculation - " + ex.getMessage(), ex);
        }       
    }
    
    public static int sundNum(int i, int j) {
        return i + j + 2 * i * j;
    }

    public static IntStream sundNumPartStream(int max) {           
        return IntStream.rangeClosed(1, max).map(i -> sundNum(i, max));
    }

    public static IntStream accumulate(IntStream lhs, IntStream rhs) {
        return IntStream.concat(lhs, rhs);
    }

    public static IntStream sundNumFullStream(int max) {
        return IntStream.rangeClosed(1, max)
                .mapToObj((int j) -> sundNumPartStream(j))
                .reduce(IntStream.empty(), SieveOfSundaram::accumulate);
    }

    public static IntStream getPrimes(final int start, final int max) {
        int sundLimit = 2*(int)Math.sqrt(max); // sundNum squared of same order magnitude as max prime - optimisation to reduce redundant calcs
       
        Set<Integer> sundSet = sundNumFullStream(sundLimit).boxed().collect(Collectors.toSet());
        Set<Integer> naturalNumberSet = IntStream.rangeClosed(1, max).boxed().collect(Collectors.toSet());
        naturalNumberSet.removeAll(sundSet);
        return IntStream.concat(IntStream.of(2), naturalNumberSet.stream().mapToInt(x -> (2 * x) + 1)).filter(x->(x>=start && x<=max ));
    }

 
}

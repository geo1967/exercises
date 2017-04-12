package prime.algos;

import calculators.PrimeGeneratorI;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Fake class to show how we could parallelise processing of primes across
 * threads, grid, cloud etc
 *
 * @author georgemullen
 */
public class SpoofSegmentedSieve implements PrimeGeneratorI {
    private final static int[] CANNED_PRIMES = SieveOfSundaram.getPrimes(1, 1000).toArray();// for spoofing
    static final int N_PARALLEL_PROCESSES = 3; // change according to available cores n machine
    private final ExecutorService threadPool = Executors.newFixedThreadPool(4);

    public int[] primes(int max) {
        assert (max < 1000); //force termination if basic assumptions violated
        int[][] indices = genIndices(max, N_PARALLEL_PROCESSES);
        PrimeConsumer primeConsumer = new PrimeConsumer(indices.length);
        for (int i = 0; i < indices.length; i++) {
           dispatchCalculation(indices[i], primeConsumer);
        }
        return primeConsumer.getPrimes();
    }

    /**
     * @param maxPrime - upper limit of prime number range
     * @param availableCores - to scale the number of processors to the number
     * of worker threads.
     * @return start and end values of range in which to compute primes
     */
    public static int[][] genIndices(int maxPrime, int availableCores) {
        int rangeSize = maxPrime / availableCores;
        int leftOver = maxPrime % availableCores;
        int[][] result = new int[availableCores][2];
        int start = 0;
        for (int i = 0; i < availableCores; i++) {
            result[i][0] = start;
            result[i][1] = start + rangeSize - 1;
            start = result[i][1] + 1;
        }
        result[result.length - 1][1] += leftOver;
        return result;
    }

    /**
     * All dispatch jobs to calculate a subset of primes are consumed here and
     * aggregated into a master set
     *
     */
    static class PrimeConsumer implements Consumer< Set<Integer>> {

        private final Set<Integer> setPrimes;
        private final CountDownLatch countDownLatch;

        /**
         * @param count Number of subsets of primes to wait for
         */
        public PrimeConsumer(int count) {
            this.setPrimes = new ConcurrentSkipListSet<>();
            this.countDownLatch = new CountDownLatch(count);
        }

        @Override
        public void accept(Set<Integer> subSetPrimes) {
            setPrimes.addAll(subSetPrimes);
            countDownLatch.countDown();
        }

        /**
         * This method will block until all prime subsets are received         *
         * @return complete set of primes up to maximum value
         */
        public int[] getPrimes() {
            try {
                countDownLatch.await(1, TimeUnit.MINUTES);
            } catch (InterruptedException ex) {
                Logger.getLogger(SpoofSegmentedSieve.class.getName()).log(Level.SEVERE, "Time out waiting for all dispatched prime calculation jobs to return", ex);
            }
            return this.setPrimes.stream().mapToInt(x -> x).toArray();
        }
    }

    /**
     *
     * @param range array of length 2 - containing the start and end values
     * @param accumulator class to aggregegate to sub set of primes generated by
     * the future task
     * @return A completable future whose results will have been aggregated into
     * the accumulator on completion.
     */
    public void  dispatchCalculation(int[] range, final Consumer accumulator) {       
                CompletableFuture.supplyAsync(() -> this.genPrimes(range[0], range[1]), threadPool)
                .thenApply(p -> IntStream.of(p).boxed().collect(Collectors.toSet())).thenAcceptAsync(accumulator, threadPool);        
    }

    /**
     * Simulator method - read range of primes between specified limits and
     * sleep to simulate cup time to calc primes
     *
     * @param min
     * @param max
     * @return
     */
    public int[] genPrimes(int min, int max) {
        int[] result = IntStream.of(CANNED_PRIMES).filter(n -> (n >= min && n <= max)).toArray();
        //now simulate load factor delay
        double sleepTime = Math.random() * 1000 * 3;
        try {
            Thread.sleep((long) sleepTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(SpoofSegmentedSieve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}

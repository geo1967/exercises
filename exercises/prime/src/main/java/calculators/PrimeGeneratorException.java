/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

/**
 * Wrapper exception to report all unhandled exceptions arising from the
 * calculation of primes
 *
 * @author georgemullen
 */
public class PrimeGeneratorException extends Exception {

    public PrimeGeneratorException(String errMsg) {
        super(errMsg);
    }

    public PrimeGeneratorException(String errMsg, Exception ex) {
        super(errMsg, ex);
    }
}

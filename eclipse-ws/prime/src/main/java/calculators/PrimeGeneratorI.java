/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

/**
 *
 * @author georgemullen
 */
public interface PrimeGeneratorI {
	int[]  primes(int max) throws PrimeGeneratorException;
   
}

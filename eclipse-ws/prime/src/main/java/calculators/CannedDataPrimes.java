/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import prime.algos.SieveOfSundaram;

/**
 *
 * @author georgemullen
 */
public class CannedDataPrimes implements PrimeGeneratorI {

   //static final int MAX_PRIME_LIMIT = 2147483647;
    final int MAX_PRIME_LIMIT;
    private String dataFile = "primes.data";
    private File src;
    private final int[] primes;

    CannedDataPrimes(int maximumPrimeLimit) {
       this.MAX_PRIME_LIMIT = maximumPrimeLimit;
       File f = new File(dataFile);
       if(f.exists() && f.canRead())
       {
           primes = readFromFile(f);
       }
       else
       {
           writeToFile(f);
           primes = readFromFile(f);
       }
    }

  

    int[] readFromFile(File f ) {
        IntStream.Builder primes = IntStream.builder();
        try (final FileReader in = new FileReader(f)) {
            
            int val;
            while((val=in.read()) !=-1)
            {
              primes.add(val);
            }
        
        }       
        catch (IOException ex) {
            Logger.getLogger(CannedDataPrimes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return primes.build().toArray();
    }

    void writeToFile(File f)  {
       
        try (final FileWriter out = new FileWriter(f)) {
             IntStream primes = SieveOfSundaram.getPrimes(1, MAX_PRIME_LIMIT);
             primes.forEach((int p)->{
                 try {
                     out.write(p);
                 } catch (IOException ex) {
                     Logger.getLogger(CannedDataPrimes.class.getName()).log(Level.SEVERE, null, ex);
                 }
             });

        } catch (IOException ex) {
            Logger.getLogger(CannedDataPrimes.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }

    @Override
    public int[] primes(int max) {
        return this.primes;
    }

}

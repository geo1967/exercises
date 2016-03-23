/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

import prime.algos.SieveOfSundaram;
import prime.algos.SpoofSegmentedSieve;
import prime.algos.TrialDivision;

/**
 *
 * @author georgemullen
 */
public class PrimeCalculatorFactory {

    private final SieveOfSundaram _SieveOfSundaram = new SieveOfSundaram();
    private final TrialDivision _TrialDivision = new TrialDivision();
    private final CannedDataPrimes _CannedDataPrimes = new CannedDataPrimes(200000);
    private final SpoofSegmentedSieve _SpoofSegmentedSieve = new SpoofSegmentedSieve();

    
    public PrimeGeneratorI getCalculator(String algo) {
        switch (algo) {
            case "TrialDivision": {
                return _TrialDivision;
            }
            case "SieveOfSundaram": {
                return _SieveOfSundaram;
            }
            case "CannedDataPrimes": {
                return _CannedDataPrimes;
            }
             case "SpoofSegmentedSieve": {
                return _SpoofSegmentedSieve;
            }
            default: {
                return _TrialDivision;
            }
        }
    }
}

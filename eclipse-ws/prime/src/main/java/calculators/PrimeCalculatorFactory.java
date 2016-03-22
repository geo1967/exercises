/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

import prime.algos.SieveOfSundaram;
import prime.algos.TrialDivision;

/**
 * 
 * @author georgemullen
 */
public class PrimeCalculatorFactory {

    private final SieveOfSundaram _SieveOfSundaram = new SieveOfSundaram();
    private final TrialDivision _TrialDivision = new TrialDivision();

    public PrimeGeneratorI getCalculator(String algo) {
        switch (algo) {
            case "TrialDivision": {
                return _TrialDivision;
            }
            case "SieveOfSundaram": {
                return _SieveOfSundaram;
            }
            default: {
                return _TrialDivision;
            }
        }
    }
}

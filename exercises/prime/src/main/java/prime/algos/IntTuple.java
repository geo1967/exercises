/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prime.algos;

import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author georgemullen
 */
public class IntTuple {
    
    public final int first;
    public final int second;

    public IntTuple(int f, int s) {
        first = f;
        second = s;
    }
    
    public static Stream<IntTuple> zip(final IntStream stream1, final IntStream stream2)
    {
        Iterator<Integer> s2iterator = stream2.boxed().iterator();
        return  stream1.boxed().map(x->new IntTuple(x, s2iterator.next()));
    }
    
}

package prime.algos;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TrialDivisionTest {
	
	static final int[] expected_primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
	static final int maxLimit = 100;
	private TrialDivision testClazz;

	@Before
	public void setup()
	{
		this.testClazz = new TrialDivision();
	}

	@Test
	public void test() {
		int[] actual = testClazz.primes(maxLimit);		
		int[] expected =expected_primes;
		assertArrayEquals(expected, actual);
	}

}

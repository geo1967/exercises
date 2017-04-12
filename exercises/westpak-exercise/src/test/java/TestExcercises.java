import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestExcercises {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public void star(int i)
	{
		if(i>1)
		{
			star(i/2);
			star(i/2);
		}
		System.out.println("hello");
	}
	
	
	@Test
	public void testStar()
	{
		testStar(1);
		testStar(2);
		testStar(2);
	}
	
	private  void testStar(int k)
	{
		//star(k);
		int count = 1;
		int a =k;
		
		while( a >1)
		{
			count ++;	
			a = a/2;
		}
		int numhellos = (int) (Math.pow(2, count) - 1) ;
		System.out.println(numhellos);;
	}
	
	
	@Ignore
	@Test
	public void test() throws IOException {
		System.out.println("please enter number of steps");
		BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
	
	String ss = bis.readLine();
	int k = Integer.valueOf(ss);
	
		for (int i=1; i<k; i++)
		{
			StringBuffer sb = new StringBuffer();
			int n = k -i;
			for (int p =0; p< k-i; p++ ){
				sb.append(' ');
			}
			for(int q=0; q<i; q++)
			{
				sb.append('#');
			}
			String s = sb.toString();
		
			System.out.println(s);
		}
		
		
	}

}

package laboratory.test.training;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 최대공약수 구하기
 * 
 * @since 2017-05-30
 * @author fixalot
 */
public class GreatestCommonDivisorTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(GreatestCommonDivisorTest.class);
	
	@Test
	public void test1() {
		/*
		input a     input b         출력
		169         104      ->     13
		100         250      ->     50
		1           1        ->     1
		1000000     5        ->     5
		104711      104717   ->     1
		98304       65536    ->     32768
		 */
		Assert.assertEquals(13, getGCD(169, 104));
		Assert.assertEquals(50, getGCD(100, 250));
		Assert.assertEquals(1, getGCD(1, 1));
		Assert.assertEquals(5, getGCD(1000000, 5));
		Assert.assertEquals(1, getGCD(104711, 104717));
		Assert.assertEquals(32768, getGCD(98304, 65536));		
	}
	
	private int getGCD(int a, int b) {
		int bigger = a;
		a = b > a ? b : a;
		int gcd = 0;
		for (int i = 1; i <= bigger; i++) {
			if (a % i == 0 && b % i == 0) {
				gcd = i;
			}
		}
		return gcd;
	}
}

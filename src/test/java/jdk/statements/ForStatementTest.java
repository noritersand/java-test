package jdk.statements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * for문 테스트
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class ForStatementTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ForStatementTest.class);

	@Test
	public void usage() {
		// 기본 사용 방법
		int i;
		for (i = 0; i < 1; i++) {
			logger.debug("{} 바퀴", i);
		}
		Assert.assertEquals(i, 1);
		
		// 무한 루프
		for (;;) {
			logger.debug("비상탈출!");
			break;
		}
		
		// 이런 모양으로도 됨.
		int j = 0;
		for (;j < 1;) {
			j++;
			logger.debug("{} 바퀴", j);
		}
	}
	
	@Test
	public void testIterator() {
		ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			logger.debug(iterator.next());
			// 마지막 요소 삭제
			if (!iterator.hasNext()) {
				iterator.remove();
			}
		}
		Assert.assertEquals(3, list.size());
	}
	
}

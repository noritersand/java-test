package jdk18.generic;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class UncheckedTest {
	private static final Logger logger = LoggerFactory.getLogger(UncheckedTest.class);

	@Test
	public void thisCodeShouldUnchecked() {
		// TODO 어떻게 하면 경고 뜨더라?

		List<String> list = new LinkedList<>();
		list.add("야");
		List<?> uncheckedList = list;
		logger.debug(String.valueOf(uncheckedList.get(0)));
		String a = (String) uncheckedList.get(0);
		logger.debug(a);

		// TODO 이렇게
//		String[] strs = { "a", "b", "c", "d", "e" };
//		ArrayList<String> list2 = (ArrayList) Arrays.asList(strs);
	}
}

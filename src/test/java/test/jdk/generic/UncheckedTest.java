package test.jdk.generic;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UncheckedTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(UncheckedTest.class);

	@Test
	public void testUnchecked() {
		// TODO 어떻게 하면 경고 뜨더라?

		List<String> list = new LinkedList<>();
		list.add("야");
		List<?> uncheckedList = list;
		System.out.println(uncheckedList.get(0));
		String a = (String) uncheckedList.get(0);
		System.out.println(a);

		// TODO 이렇게
//		String[] strs = { "a", "b", "c", "d", "e" };
//		ArrayList<String> list2 = (ArrayList) Arrays.asList(strs);
	}
}

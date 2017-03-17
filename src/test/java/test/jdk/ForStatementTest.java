package test.jdk;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForStatementTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ForStatementTest.class);

	@Test
	public void testRemoveList() {
		String[] strs = { "a", "b", "c", "d", "e" };
		
		List<String> list = Arrays.stream(strs).collect(Collectors.toList());
		for (int cnt = 0, i = 0; i < list.size(); i++) { // 3개 자르기
			if (cnt < 3) {
				list.remove(0);
				i--;
				cnt++;
			}
		}
		Assert.assertEquals("[d, e]", list.toString());
		
		list = Arrays.stream(strs).collect(Collectors.toList());
		for (int cnt = 0, i = list.size(); i >= 0; i--) { // 뒤에서 3개 자르기
			if (cnt < 3) {
				list.remove(list.size() - 1);
				i++;
				cnt++;
			}
		}
		Assert.assertEquals("[a, b]", list.toString());
	}
}

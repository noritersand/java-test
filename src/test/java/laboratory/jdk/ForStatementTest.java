package laboratory.jdk;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForStatementTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ForStatementTest.class);

	@Test
	public void removeList() {
		String[] strs = { "a", "b", "c", "d", "e" };

		// 앞에서 3개 자르기
		List<String> list = Arrays.stream(strs).collect(Collectors.toList());
//		for (int cnt = 0, i = 0; i < list.size(); i++) {
//			if (cnt < 3) {
//				list.remove(0);
//				i--;
//				cnt++;
//			}
//		} // for 필요 없잖여
		{
			int cnt = 0;
			while (true) {
				if (cnt == 3) {
					break;
				}
				list.remove(0);
				cnt++;
			}
		}
		Assert.assertEquals("[d, e]", list.toString());

		// 뒤에서 3개 자르기
		list = Arrays.stream(strs).collect(Collectors.toList());
		for (int cnt = 0, i = list.size(); i >= 0; i--) {
			if (cnt < 3) {
				list.remove(list.size() - 1);
				i++;
				cnt++;
			}
		}
		Assert.assertEquals("[a, b]", list.toString());
	}
}

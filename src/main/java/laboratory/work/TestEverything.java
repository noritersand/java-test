package laboratory.work;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEverything {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(TestEverything.class);

	public static void main(String[] args) throws IOException {

		int input = System.in.read(); // 1을 입력하면 케릭터 '1'의 아스키코드인 49가 저장됨.
		System.out.println(input); // 49

		char ch = (char) input; // 아스키코드 49가 '1'로 바뀜
		System.out.println(ch); // '1'
		
	}
}

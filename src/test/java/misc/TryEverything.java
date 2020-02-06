package misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TryEverything {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(TryEverything.class);

	public static void main(String[] arg) {
		int a = 3;
		int A = a * 2 - 1;
		int K = 1;
		int B = a - 1;

		for (int i = 1; i <= A; i++) {
			if (i <= a) {
				for (int k = 0; k < K; k++) {
					System.out.print("*");
				}
			} else {
				for (int k = 0; k < B; k++) {
					System.out.print("*");
				}
				B--;
			}
			K++;
			System.out.print("\n");
		}
	}
}

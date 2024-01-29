package testbed.util;

import java.security.SecureRandom;

/**
 * 랜덤으로 뭔가 만드는 뇨솤
 * 
 * @author fixalot
 * @since 2024-01-29
 */
public enum RandomGenerator {
    ;

    /**
     * 난수 생성
     *
     * @param lengthOfRandomValue 원하는 난수의 길이
     * @return
     */
    public static String generateFixedLengthRandomDecimal(int lengthOfRandomValue) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < lengthOfRandomValue; ++j) {
            int num = random.nextInt(10); // 0에서 9 사이의 난수 생성
            sb.append(num);
        }
        return sb.toString();
    }
}

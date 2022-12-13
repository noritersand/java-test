package misc;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TryEverything {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(TryEverything.class);

    public static void main(String[] arg) {
        int numArr[] = new int[5];
        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = i + 1;
        }

        String str = Arrays.toString(numArr);
        System.out.println(str);
        System.out.println(str.substring(3, 6));
        System.out.println(str.substring(0, 4));
        System.out.println(str.substring(5));
    }
}

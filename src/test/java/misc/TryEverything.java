package misc;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public enum TryEverything {
	;

    public static void main(String[] arg) {
        int[] numArr = new int[5];
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

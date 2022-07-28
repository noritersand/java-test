package lab.work;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEverything {
    private static final Logger logger = LoggerFactory.getLogger(TestEverything.class);

    public static List<Integer> reverse(List<Integer> arr) {
        if (arr.size() == 1) {
            return arr;
        }
        int base = arr.get(0);
        arr = arr.subList(1, arr.size());
        arr = reverse(arr); // arr에 reverse(arr)을 대입하지 않았을 때 왜 base 값이 붙어서 나오는지 궁금
        arr.add(base);
        return arr;
    }

    public static List<Integer> reverse(List<Integer> arr, int start, int end) {
        if (start < end - 1) {
            int base = arr.get(start);
            arr.set(start, arr.get(end - 1));
            arr.set(end - 1, base);
            return reverse(arr, start + 1, end - 1);
        } else {
            return arr;
        }
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> arr2 = new ArrayList<>();
        List<Integer> arr3 = new ArrayList<>();
        arr2 = reverse(arr);
        System.out.println(arr);
        System.out.println(arr2);
//        for (int a : arr2) {
//            System.out.print(a + " ");
//        }
//        System.out.println();

        arr3 = reverse(arr, 0, 6);
        System.out.println(arr3);
//        for (int a : arr3) {
//            System.out.print(a + " ");
//        }
        Collections.reverse(arr);
        System.out.println(arr);
    }
}

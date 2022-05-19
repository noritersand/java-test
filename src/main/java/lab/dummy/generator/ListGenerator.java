package lab.dummy.generator;

import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.commons.nullanalysis.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ListGenerator {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ListGenerator.class);

    private ListGenerator() {
    }

    public static List<String> generateAlphabeticList(@NotNull final int size) {
        final List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(RandomStringUtils.randomAlphabetic(1));
        }
        return list;
    }

    @NotNull
    public static List<String> generateStringList(int size) {
        List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(randomString());
        }
        return list;
    }

    @NotNull
    public static List<String> generateStringList(int size, int targetIndex, String specificString) {
        List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            if (i == targetIndex) {
                list.add(specificString);
                continue;
            }
            list.add(randomString());
        }
        return list;
    }

    @NotNull
    private static String randomString() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}

package lab.dummy.generator;

import lombok.Data;
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
        return RandomStringUtils.random(length, true, false);
    }

    public static List<Obj> generateObjList(int size) {
        List<Obj> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Obj obj = new Obj();
            list.add(obj);
            obj.setIndex(i);
            obj.setName(RandomStringUtils.random(10, true, false));
        }
        return list;
    }

    @Data
    public static class Obj {
        private Integer index;
        private String name;
    }
}


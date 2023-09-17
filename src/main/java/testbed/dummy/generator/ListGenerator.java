package testbed.dummy.generator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.commons.nullanalysis.NotNull;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public enum ListGenerator {
    ;

    public static List<String> generateAlphabeticList(@NotNull int size) {
        List<String> list = new ArrayList<>(size);
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
            obj.setNum(i);
            obj.setName(RandomStringUtils.random(10, true, false));
        }
        return list;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Obj {
        private Integer num;
        private String name;
    }
}


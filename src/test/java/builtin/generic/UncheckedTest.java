package builtin.generic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class UncheckedTest {

    @Test
    void thisCodeShouldUnchecked() {
        // TODO 어떻게 하면 경고 뜨더라?

        List<String> list = new LinkedList<>();
        list.add("야");
        List<?> uncheckedList = list;
        log.debug("{}", uncheckedList.get(0));
        String a = (String) uncheckedList.get(0);
        log.debug(a);

        // TODO 이렇게
//		String[] strs = { "a", "b", "c", "d", "e" };
//		ArrayList<String> list2 = (ArrayList) Arrays.asList(strs);
    }
}

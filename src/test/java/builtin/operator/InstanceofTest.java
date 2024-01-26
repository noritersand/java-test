package builtin.operator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.management.relation.RoleList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class InstanceofTest {

    @Test
    void testExtends() {
        Object object = new Object();
        String string = "";

        assertThat(string instanceof String).isTrue();
        assertThat(string instanceof Object).isTrue();
        assertThat(object instanceof String).isFalse();
        assertThat(object instanceof Object).isTrue();
    }

    @Test
    void testExtends2() {
        ArrayList<Object> arrayList = new ArrayList<>();
        assertThat(arrayList instanceof List).isTrue();

        assertThat(arrayList instanceof RoleList).isFalse();

        RoleList roleList = new RoleList();
        assertThat(roleList instanceof List).isTrue();

        List<Object> linkedList = new LinkedList<>();
        assertThat(linkedList instanceof List).isTrue();
        assertThat(linkedList instanceof LinkedList).isTrue();
    }

    @Test
    void testThis() {
        assertThat(this instanceof InstanceofTest).isTrue();
    }
}

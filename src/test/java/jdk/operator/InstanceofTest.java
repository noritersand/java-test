package jdk.operator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.management.relation.RoleList;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class InstanceofTest {

    @Test
    void testExtends() {
        Object object = new Object();
        String string = "";

        assertTrue(string instanceof String);
        assertTrue(string instanceof Object);
        assertFalse(object instanceof String);
        assertTrue(object instanceof Object);
    }

    @Test
    void testExtends2() {
        ArrayList<Object> arrayList = new ArrayList<>();
        RoleList roleList = new RoleList();

        assertTrue(arrayList instanceof List);
        assertTrue(roleList instanceof List);
        assertFalse(arrayList instanceof RoleList);
    }

    @Test
    void testThis() {
        assertTrue(this instanceof InstanceofTest);
    }
}

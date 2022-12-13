package jdk.operator;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.RoleList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class InstanceofTest {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(InstanceofTest.class);

    @Test
    public void testExtends() {
        Object object = new Object();
        String string = new String();

        assertTrue(string instanceof String);
        assertTrue(string instanceof Object);
        assertFalse(object instanceof String);
        assertTrue(object instanceof Object);
    }

    @Test
    public void testExtends2() {
        ArrayList<Object> arrayList = new ArrayList<>();
        RoleList roleList = new RoleList();

        assertTrue(arrayList instanceof List);
        assertTrue(roleList instanceof List);
        assertFalse(arrayList instanceof RoleList);
    }

    @Test
    public void testThis() {
        assertTrue(this instanceof InstanceofTest);
    }
}

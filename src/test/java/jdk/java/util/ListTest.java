package jdk.java.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * {@link List} 테스트
 * 
 * @author fixalot
 * @since 2023-03-25
 */
public class ListTest {

    @Test
    public void testOf() {
        List<Object> of = List.of();
        assertEquals(0, of.size());
    }
}

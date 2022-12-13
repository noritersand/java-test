package jdk.statement;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
public class NestedEnumTest {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(NestedEnumTest.class);

    @Test
    public void test() {
        NestedEnumTestBean.InsertType insertType = NestedEnumTestBean.InsertType.APPEND;
        assertEquals("APPEND", insertType.toString());
    }
}

class NestedEnumTestBean {
    private InsertType emailInsertType;

    public enum InsertType {
        APPEND, NEW;
    }

    public InsertType getEmailInsertType() {
        return emailInsertType;
    }

    public void setEmailInsertType(InsertType emailInsertType) {
        this.emailInsertType = emailInsertType;
    }
}

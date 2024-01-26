package builtin.statement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class NestedEnumTest {

    @Test
    void test() {
        NestedEnumTestBean.InsertType insertType = NestedEnumTestBean.InsertType.APPEND;
        assertEquals("APPEND", insertType.toString());
    }
}

class NestedEnumTestBean {
    private InsertType emailInsertType;

    public enum InsertType {
        APPEND, NEW
    }

    public InsertType getEmailInsertType() {
        return emailInsertType;
    }

    public void setEmailInsertType(InsertType emailInsertType) {
        this.emailInsertType = emailInsertType;
    }
}

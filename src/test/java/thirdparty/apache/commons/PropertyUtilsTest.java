package thirdparty.apache.commons;

import lombok.extern.slf4j.Slf4j;
import model.TestModel;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class PropertyUtilsTest {

    /**
     * 인스턴스의 프로퍼티 복사. 복사하려는 타입이 private이면 작동하지 않음
     * TODO {@link BeanUtils#copyProperties(Object, Object)}랑 뭔 차이인지...
     * Spring의 {@link org.springframework.beans.BeanUtils#copyProperties(Object, Object)}가 더 좋다.
     *
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @author fixalot
     */
    @Test
    void testCopyProperties() throws Exception {
        TestModel oldbie = new TestModel();
        oldbie.setName("야");
        oldbie.setNumber(101);

        TestModel newbie = new TestModel();

        PropertyUtils.copyProperties(newbie, oldbie);
//		BeanUtils.populate(interworkModel, product); // target 필드가 java.util.Date 타입인데 값으로 null이 들어오면 에러남.

        assertNotSame(oldbie, newbie);
        assertEquals(oldbie.getName(), newbie.getName());
        assertEquals(oldbie.getNumber(), newbie.getNumber());
    }
}

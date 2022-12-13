package thirdparty.apache.commons;

import lombok.extern.slf4j.Slf4j;
import model.TestModel;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class BeanUtilsTest {

    /**
     * 인스턴스의 프로퍼티 복사. 복사하려는 타입이 private이면 작동하지 않음
     * TODO {@link PropertyUtils#copyProperties(Object, Object)}랑 뭔 차이인지...
     * Spring의 {@link org.springframework.beans.BeanUtils#copyProperties(Object, Object)}가 더 좋다.
     *
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @author fixalot
     */
    @Test
    public void testCopyProperties() throws Exception {
        TestModel oldbie = new TestModel();
        oldbie.setName("야");
        oldbie.setNumber(101);
        oldbie.setWhen(null);

        TestModel newbie = new TestModel();

        BeanUtils.copyProperties(newbie, oldbie);

        assertNotSame(oldbie, newbie);
        assertEquals(oldbie.getName(), newbie.getName());
        assertEquals(oldbie.getNumber(), newbie.getNumber());
    }

    /**
     * 맵 -> plain object 변환. 복사하려는 타입이 private이면 작동하지 않으며, 값이 null일 땐 ConversionException 발생함.
     *
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @author fixalot
     */
    @Test
    public void testPopulate() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "야");
        map.put("number", 101);
//		map.put("when", null); // ConversionException: No value specified for 'Date'
        map.put("when", new Date());

        TestModel model = new TestModel();

        BeanUtils.populate(model, map);

        assertEquals("야", model.getName());
        assertEquals(101, model.getNumber());
    }

    /**
     * plain object -> 맵 변환.
     *
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @author fixalot
     */
    @Test
    public void testDescribe() throws Exception {
        TestModel model = new TestModel();
        model.setName("야");
        model.setNumber(101);
        model.setWhen(null);

        BeanUtils.copyProperties(model, model);

        // 집에서 돌리면 이게 맞음.
        assertEquals("{number=101, name=야, when=null}",
                String.valueOf(BeanUtils.describe(model)));

        // TODO: null 무시 하려면 이렇게 하라는데 안됨
//		BeanUtilsBean instance = BeanUtilsBean.getInstance();
//		instance.getConvertUtils().register(false, false, 0);
//		log.debug("{}", instance.describe(model));
    }
}

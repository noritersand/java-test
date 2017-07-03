package laboratory.thirdparty.apache.commons;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import laboratory.model.TestModel;

public class BeanUtilsTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BeanUtilsTest.class);

	/**
	 * 인스턴스의 프로퍼티 복사. 복사하려는 타입이 private이면 작동하지 않음 TODO {@link PropertyUtils#copyProperties(Object, Object)}랑 뭔 차이인지... Spring의
	 * {@link org.springframework.beans.BeanUtils#copyProperties(Object, Object)}가 더 좋다.
	 * 
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @author fixalot
	 */
	@Test
	public void copyProperties() throws IllegalAccessException, InvocationTargetException {
		TestModel oldbie = new TestModel();
		oldbie.setName("야");
		oldbie.setNumber(101);
		oldbie.setWhen(null);

		TestModel newbie = new TestModel();

		BeanUtils.copyProperties(newbie, oldbie);

		Assert.assertTrue(oldbie != newbie);
		Assert.assertEquals(oldbie.getName(), newbie.getName());
		Assert.assertEquals(oldbie.getNumber(), newbie.getNumber());
	}

	/**
	 * 맵 -> POJO 변환. 복사하려는 타입이 private이면 작동하지 않으며, 값이 null일 땐 ConversionException 발생함.
	 * 
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @author fixalot
	 */
	@Test
	public void populate() throws IllegalAccessException, InvocationTargetException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", "야");
		map.put("number", 101);
//		map.put("when", null); // ConversionException: No value specified for 'Date'
		map.put("when", new Date());

		TestModel model = new TestModel();

		BeanUtils.populate(model, map);

		Assert.assertEquals("야", model.getName());
		Assert.assertEquals(101, model.getNumber());
	}
}

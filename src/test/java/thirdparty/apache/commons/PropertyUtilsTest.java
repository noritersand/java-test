package thirdparty.apache.commons;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.TestModel;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class PropertyUtilsTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PropertyUtilsTest.class);

	/**
	 * 인스턴스의 프로퍼티 복사. 복사하려는 타입이 private이면 작동하지 않음 
	 * TODO {@link BeanUtils#copyProperties(Object, Object)}랑 뭔 차이인지... 
	 * Spring의 {@link org.springframework.beans.BeanUtils#copyProperties(Object, Object)}가 더 좋다.
	 * 
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @author fixalot
	 * @throws NoSuchMethodException
	 */
	@Test
	public void testCopyProperties() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		TestModel oldbie = new TestModel();
		oldbie.setName("야");
		oldbie.setNumber(101);

		TestModel newbie = new TestModel();

		PropertyUtils.copyProperties(newbie, oldbie);
//		BeanUtils.populate(interworkModel, product); // target 필드가 java.util.Date 타입인데 값으로 null이 들어오면 에러남.

		Assert.assertTrue(oldbie != newbie);
		Assert.assertEquals(oldbie.getName(), newbie.getName());
		Assert.assertEquals(oldbie.getNumber(), newbie.getNumber());
	}
}

package jdk.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectionsTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CollectionsTest.class);

	@Test
	public void testMin() {
		List<Integer> list = Arrays.asList(6, 3, 1, 56, 99, 2, 41, 27, 54, 3);
		Assert.assertEquals(Integer.valueOf(1), Collections.min(list));
	}

	@Test
	public void testMax() {
		List<Integer> list = Arrays.asList(6, 3, 1, 56, 99, 2, 41, 27, 54, 3);
		Assert.assertEquals(Integer.valueOf(99), Collections.max(list));
	}

	/**
	 * <pre>
	 * An instantiation of a generic type where the type argument is a wildcard (as opposed to a concrete type). A wildcard parameterized
	 * type is an instantiation of a generic type where at least one type argument is a wildcard. Examples of wildcard parameterized types
	 * are Collection<?> , List<? extends Number> , Comparator<? super String> and Pair<String,?>. A wildcard parameterized type denotes a
	 * family of types comprising concrete instantiations of a generic type. The kind of the wildcard being used determines which concrete
	 * parameterized types belong to the family. For instance, the wildcard parameterized type Collection<?> denotes the family of all
	 * instantiations of the Collection interface regardless of the type argument. The wildcard parameterized type List<? extends Number>
	 * denotes the family of all list types where the element type is a subtype of Number. The wildcard parameterized type Comparator<?
	 * super String> is the family of all instantiations of the Comparator interface for type argument types that are supertypes of String.
	 * 
	 * A wildcard parameterized type is not a concrete type that could appear in a new expression. A wildcard parameterized type is similar
	 * to an interface type in the sense that reference variables of a wildcard parameterized type can be declared, but no objects of the
	 * wildcard parameterized type can be created. The reference variables of a wildcard parameterized type can refer to an object that is
	 * of a type that belongs to the family of types that the wildcard parameterized type denotes.
	 * </pre>
	 * 
	 * {@linkplain http://www.angelikalanger.com/GenericsFAQ/FAQSections/ParameterizedTypes.html#WIldcard+Instantiations}
	 * 
	 * @author noritersand
	 */
	@Test
	public void testWildcardParameterizedType() {
		List<? super String> strList = Arrays.asList(new String[] { "a", "b" });
		Assert.assertTrue(strList.contains("a"));
	}
}

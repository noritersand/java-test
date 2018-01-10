package jdk.operator;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.RoleList;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class InstanceofTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(InstanceofTest.class);

	@Test
	public void testExtends() {
		Object object = new Object();
		String string = new String();

		Assert.assertTrue(string instanceof String);
		Assert.assertTrue(string instanceof Object);
		Assert.assertFalse(object instanceof String);
		Assert.assertTrue(object instanceof Object);
	}

	@Test
	public void testExtends2() {
		ArrayList<Object> arrayList = new ArrayList<>();
		RoleList roleList = new RoleList();

		Assert.assertTrue(arrayList instanceof List);
		Assert.assertTrue(roleList instanceof List);
		Assert.assertFalse(arrayList instanceof RoleList);
	}
}

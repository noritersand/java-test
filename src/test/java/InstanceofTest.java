import java.util.ArrayList;
import java.util.List;

import javax.management.relation.RoleList;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InstanceofTest {
	private static final Logger log = LoggerFactory.getLogger(InstanceofTest.class);

	@Test
	public void testLog() {
		log.info("InstanceofTest");
	}
	
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

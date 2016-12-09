package noritersand;

import java.io.File;
import java.nio.file.Path;

import org.junit.Assert;
import org.junit.Test;

public class LogicTest {
	public static void main(String... args) {
//		m01();
//		m02();
//		System.out.println(m03()); 
		m03();
	}
	
	@Test
	public void m04() {
		String path = "/member/meberDetail.do#";
		Assert.assertEquals(path.length() - 1, path.indexOf("#"));
	}
	
	@Test
	public void m05() {
		String referrer = "/member/aa.do#";
		Assert.assertEquals("/member/aa.do", referrer.substring(0, referrer.length() - 1));
	}
	
	public static void m03() {
//		System.out.println(new DateTime().toString("yyyyMMddHHmmss"));
	}
	
	public static void m02() {
		File file = new File("c:\\dev\\");
		Path path = file.toPath();
		System.out.println(path);
	}
	
	public static void m01() {
		Child c = new Child();
		System.out.println(c.getClass());
		
		Parent p = c;
		System.out.println(p.getClass());	
	}
}


class Parent {
	
}

class Child extends Parent {
	
}
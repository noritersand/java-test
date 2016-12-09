package noritersand;

import java.util.HashMap;
import java.util.Map;

/**
 * <PRE>
 * Filename : CollectionTest.java 
 * Class    : CollectionTest
 * Function : 
 * Comment  : 
 * History  : 2016-09-20, fixalot, v1.0, 최초작성
 *
 * </PRE>
 * @version : 1.0
 * @author  : Copyright (c) 2015 by IDR Commerce Corp. All Rights Reserved.
 */
public class CollectionTest {
	public static void main(String[] args) {
		m01();
	}
	
	public static void m01() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("1", 1);
		map.put("1", 2);
		System.out.println(map);
	}
}

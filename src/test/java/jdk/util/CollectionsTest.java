package jdk.util;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CollectionsTest {
	@Test
	public void testArrayList() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(9);
		list.add(8);
		list.add(7);
		list.add(6);
		list.add(5);
		list.add(4);
		list.add(3);
		list.add(2);
		list.add(1);
		
		list.remove(1);
		
		Assert.assertEquals("[9, 7, 6, 5, 4, 3, 2, 1]", list.toString());
	}
	
	@Test
	public void testUnchecked() {
		// TODO 어떻게 하면 경고 뜨더라?
		
		List<String> list = new LinkedList<>();
		list.add("야");
		List<?> uncheckedList = list;
		System.out.println(uncheckedList.get(0));
		String a = (String) uncheckedList.get(0);
		System.out.println(a);
	}
	
}

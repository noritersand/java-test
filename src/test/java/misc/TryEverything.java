package misc;

import java.util.ArrayList;

public class TryEverything {

	public static void main(String[] args) {
		LifeCycle lc = new LifeCycle();
//		lc.Test();
		System.out.println(lc.getTest()); // misc.Adapter@22555ebf
	}
}

class LifeCycle {
	private Adapter test; // 인스턴스변수

	public Adapter getTest() {
		return test;
	}

	public void Test() {
		ArrayList<String> items = new ArrayList<>();
		test = new Adapter(items);
	}
}

class Adapter {
	private ArrayList<String> items;

	public Adapter(ArrayList<String> items) {
		this.items = items;
	}
}

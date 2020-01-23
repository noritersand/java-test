package misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImplicitInvokeSuper {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ImplicitInvokeSuper.class);

	public static void main(String[] args) {
		new Child();
	}
}

class Parent {
	String nation;

	Parent() {
		this("대한민국");
		System.out.println("Parent() call");
	}

	Parent(String nation) {
		this.nation = nation;
		System.out.println("Parent(String nation) call");
	}
}

class Child extends Parent {
	String name;

	Child() {
		this("홍길동");
		System.out.println("Child() call");
	}

	Child(String name) {
		this.name = name;
		System.out.println("Child(String name) call");
	}
}
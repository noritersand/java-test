package etc;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class AtomicTest {
	private int a = 0;

	public int incrementAndGet() {
		return a++;
	}

	public static void main(String[] args) {
		final AtomicTest test = new AtomicTest();
		for (int i = 0; i < 100; i++) {

			Thread thread = new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++) {
						System.out.println(test.incrementAndGet());
					}
				}
			};
			thread.start();
		}

//		synchronized (Object.class) {
//
//		}
//
//		List<String> list = new ArrayList<String>();
//		list.add("hello");
//		list.add("world");
//		list.add("!!!!!!");
//		for (String str : list) {
//			System.out.println(str);
//			if (str.equals("hello")) {
//				list.remove(str);
//			}
//		}

//		final Vector<Integer> a = new Vector<Integer>();
//		for (int i = 0; i < 100; i++) {
//			a.add(i);
//		}
//
//		new Thread() {
//			public void run() {
//				for (int i = 100; i < 1000; i++) {
//					a.add(i);
//				}
//			}
//		}.start();
//
//		for (Integer integer : a) {
//			System.out.println(integer);
//		}
	}
}
import static org.junit.Assert.*;
import java.util.HashMap;

import org.junit.Test;

public class MapTest {
	@Test
	public void testMap() {
		HashMap<String, String> map = new HashMap<>();
		assertNull(map.get("야"));
	}
}

package laboratory.util.request;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RequestParameter {
	private Map<String, String> parameterMap = new HashMap<>();

	public Map<String, String> getMap() {
		return parameterMap;
	}

	public void setMap(Map<String, String> map) {
		this.parameterMap = map;
	}
	
	public void addParameter(String key, String object) {
		parameterMap.put(key, object);
	}
	
	@Override
	public String toString() {
		final Map<String, String> params = this.parameterMap;
		Set<String> keySet = params.keySet();
		StringBuilder builder = new StringBuilder();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String value = params.get(key);
			builder.append(key + "=" + value);
			if (iterator.hasNext()) {
				builder.append(", ");
			}
		}
		return builder.toString();
	}
	
	/*
	private void printParameters(HttpServletRequest req) {
		Map<String, String[]> parameterMap = req.getParameterMap();
		Set<String> keySet = parameterMap.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String[] values = parameterMap.get(key);
			logger.debug(key + ": " + Arrays.toString(values));
		}
	}
	//		while (iterator.hasNext()) {
//			String key = iterator.next();
//			String[] values = parameterMap.get(key);
//			builder.append(key + ": " + Arrays.toString(values));
//			if (iterator.hasNext()) {
//				builder.append(", ");
//			}
//		}
//		return builder.toString();
	*/
}

package laboratory.thirdparty.apache.commons;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemUtilsTest {
//	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(SystemUtilsTest.class);
	
	@Test
	public void usage() {
		log.debug(String.valueOf(SystemUtils.IS_OS_WINDOWS));
		log.debug(String.valueOf(SystemUtils.IS_OS_UNIX));
		log.debug(String.valueOf(SystemUtils.IS_OS_LINUX));
		log.debug(String.valueOf(SystemUtils.IS_OS_MAC));
		log.debug(String.valueOf(SystemUtils.FILE_ENCODING));
	}
}

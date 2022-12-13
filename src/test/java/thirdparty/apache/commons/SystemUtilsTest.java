package thirdparty.apache.commons;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Test;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class SystemUtilsTest {

    @Test
    public void test() {
        log.debug("{}", SystemUtils.IS_OS_WINDOWS);
        log.debug("{}", SystemUtils.IS_OS_UNIX);
        log.debug("{}", SystemUtils.IS_OS_LINUX);
        log.debug("{}", SystemUtils.IS_OS_MAC);
        log.debug("{}", SystemUtils.FILE_ENCODING);
    }
}

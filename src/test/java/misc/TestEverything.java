package misc;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;

@Slf4j
public enum TestEverything {
    ;

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plus = now.plus(78, ChronoUnit.HOURS);
        LocalDateTime plus1 = plus.plus(55, ChronoUnit.MINUTES);
        log.debug("{}", plus1);
    }
}

package testbed.time;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@NoArgsConstructor
public class TimeSegment {
    private int hours;
    private int minuteOfHour;
    private long secondOfMinute;

    public TimeSegment(long totalSeconds) {
        this.hours = (int) (totalSeconds / 3600L);
        long remainingSeconds = totalSeconds % 3600;

        this.minuteOfHour = (int) (remainingSeconds / 60);
        this.secondOfMinute = remainingSeconds % 60;
    }

    public TimeSegment(Duration duration) {
        this((int) duration.getSeconds());
    }
}

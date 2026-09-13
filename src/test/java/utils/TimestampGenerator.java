package utils;

import java.time.Instant;
import org.jeasy.random.api.Randomizer;

public class TimestampGenerator implements Randomizer<String> {
    public String getCurrentTime() {
        return Instant.now().toString();
    }

    @Override
    public String getRandomValue() {
        return Instant.now().toString();
    }
}

package utils;

import org.jeasy.random.api.Randomizer;

import java.time.Instant;

public class TimestampGenerator implements Randomizer<String> {
    public String getCurrentTime() {
        return Instant.now().toString();
    }

    @Override
    public String getRandomValue() {
        return Instant.now().toString();
    }
}

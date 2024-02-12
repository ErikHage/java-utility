package com.tfr.executor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SturdySchedulerTest {

    private final SturdyScheduler sturdyScheduler = new SturdyScheduler(
            "sturdy-scheduler-unit-test",
            Executors.newScheduledThreadPool(2));

    @AfterEach
    public void tearDown() {
        sturdyScheduler.shutdown();
    }

    @Test
    void when() {
        AtomicInteger counter = new AtomicInteger();

        Runnable command = counter::incrementAndGet;

        sturdyScheduler.scheduleAtFixedRate(command, 0, 50, TimeUnit.MILLISECONDS);

        // wait a bit

        // assert counter is counting

        // stop
    }
}

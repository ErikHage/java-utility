package com.tfr.executor;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SturdySchedulerTest {

    private static SturdyScheduler sturdyScheduler = new SturdyScheduler(Executors.newScheduledThreadPool(2));

    @AfterAll
    public static void tearDown() {
        sturdyScheduler.stopAll();
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

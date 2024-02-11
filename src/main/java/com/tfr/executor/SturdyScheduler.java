package com.tfr.executor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SturdyScheduler {
    private final ScheduledExecutorService scheduledExecutorService;

    public SturdyScheduler(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }

    ScheduledFuture<?> scheduleAtFixedRate(
            Runnable command,
            long initialDelay,
            long period,
            TimeUnit unit) {
        return scheduledExecutorService.scheduleAtFixedRate(
                () -> runWithExceptionHandling(command),
                initialDelay,
                period,
                unit
        );
    }

    ScheduledFuture<?> scheduleWithFixedDelay(
            Runnable command,
            long initialDelay,
            long delay,
            TimeUnit unit) {
        return scheduledExecutorService.scheduleWithFixedDelay(
                () -> runWithExceptionHandling(command),
                initialDelay,
                delay,
                unit
        );
    }

    void stopAll() {
        scheduledExecutorService.shutdown();
    }

    private static void runWithExceptionHandling(final Runnable command)
    {
        try
        {
            command.run();
        }
        catch (final Exception e)
        {
            System.out.println("Failed, boo");
            e.printStackTrace();
            // log some stuff
        }
    }
}

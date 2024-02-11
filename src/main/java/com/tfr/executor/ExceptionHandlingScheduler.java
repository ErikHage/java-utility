package com.tfr.executor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ExceptionHandlingScheduler {
    private final ScheduledExecutorService scheduledExecutorService;

    public ExceptionHandlingScheduler(ScheduledExecutorService scheduledExecutorService) {
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

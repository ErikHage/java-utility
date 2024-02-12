package com.tfr.executor;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SturdyScheduler implements SafeExecutor {
    private final String threadName;
    private final ScheduledExecutorService scheduledExecutorService;

    public SturdyScheduler(String threadName, ScheduledExecutorService scheduledExecutorService) {
        this.threadName = threadName;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    ScheduledFuture<?> schedule(
            Runnable command,
            long initialDelay,
            TimeUnit unit) {
        return scheduledExecutorService.schedule(
                () -> runWithExceptionHandling(command),
                initialDelay,
                unit
        );
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

    @Override
    public String getName() {
        return threadName;
    }

    @Override
    public void shutdown() {
        scheduledExecutorService.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return scheduledExecutorService.shutdownNow();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit timeUnit) throws InterruptedException {
        return scheduledExecutorService.awaitTermination(timeout, timeUnit);
    }
}

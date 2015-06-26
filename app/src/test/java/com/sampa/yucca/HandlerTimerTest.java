package com.sampa.yucca;

import com.sampa.yucca.core.TickEvent;
import com.sampa.yucca.core.Timer;
import com.sampa.yucca.core.TimerListener;
import com.sampa.yucca.impl.HandlerTimer;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

final class TimerTestContext {
    private boolean startingFired;
    private boolean startedFired;
    private boolean tickFired;
    private boolean pausingFired;
    private boolean pausedFired;

    public void reset() {
        startingFired = false;
        startedFired = false;
        tickFired = false;
        pausingFired = false;
        pausedFired = false;
    }

    public boolean isStartingFired() {
        return startingFired;
    }

    public void startingFired() {
        startingFired = true;
    }

    public boolean isStartedFired() {
        return startedFired;
    }

    public void startedFired() {
        startedFired = true;
    }

    public boolean isTickFired() {
        return tickFired;
    }

    public void tickFired() {
        tickFired = true;
    }

    public boolean isPausingFired() {
        return pausingFired;
    }

    public void pausingFired() {
        pausingFired = true;
    }

    public boolean isPausedFired() {
        return pausedFired;
    }

    public void pausedFired() {
        pausedFired = true;
    }

    public boolean isStoppingFired() {
        return stoppingFired;
    }

    public void stoppingFired() {
       stoppingFired = true;
    }

    public boolean isStoppedFired() {
        return stoppedFired;
    }

    public void stoppedFired() {
        stoppedFired = true;
    }

    boolean stoppingFired;
    boolean stoppedFired;

}

class TimerTestListener implements TimerListener {

    protected TimerTestContext ctx;

    public TimerTestListener(TimerTestContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void starting() {
        ctx.startingFired();
    }

    @Override
    public void started() {
        ctx.startedFired();
    }

    @Override
    public void tick(TickEvent e) {

    }

    @Override
    public void pausing() {

    }

    @Override
    public void paused() {

    }

    @Override
    public void stopping() {

    }

    @Override
    public void stopped() {

    }
}


/**
 * Created by cclamb on 6/26/15.
 */
public final class HandlerTimerTest {

    private static TimerTestContext ctx;
    private static TimerListener listener;
    private static Timer timer;

    @BeforeClass
    public static void createTimer() {
        ctx = new TimerTestContext();
        listener = new TimerTestListener(ctx);
        timer = new HandlerTimer(listener);
    }

    @Test
    public void canFireStartEvents() {
        timer.start();
        timer.stop();
        assertTrue(ctx.isStartedFired());
        assertTrue(ctx.isStartingFired());
    }
}

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
    private boolean stoppingFired;
    private boolean stoppedFired;
    private TickEvent lastEvent;

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

    public void tickFired(TickEvent e) {
        tickFired = true;
        lastEvent = e;
    }

    public TickEvent getLastEvent() {
        return lastEvent;
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
        ctx.tickFired(e);
    }

    @Override
    public void pausing() {
        ctx.pausingFired();
    }

    @Override
    public void paused() {
        ctx.pausedFired();
    }

    @Override
    public void stopping() {
        ctx.stoppingFired();
    }

    @Override
    public void stopped() {
        ctx.stoppedFired();
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
        timer.start();
    }

    @Test
    public void canFireStartEvents() {
        assertTrue(ctx.isStartedFired());
        assertTrue(ctx.isStartingFired());
        ctx.reset();
    }

    @Test
    public void canFirePauseEvents() {
        timer.pause();
        assertTrue(ctx.isPausedFired());
        assertTrue(ctx.isPausingFired());
        ctx.reset();
        timer.start();
    }

    @Test
    public void canFireStopEvents() {
        timer.stop();
        assertTrue(ctx.isStoppedFired());
        assertTrue(ctx.isStoppingFired());
        ctx.reset();
        timer.start();
    }

    @Test
    public void canFireTickEvents() {
        assertTrue(ctx.isTickFired());
        TickEvent e = ctx.getLastEvent();
        assertNotNull(e);
    }
}

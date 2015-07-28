package com.sampa.yucca.impl;

import com.sampa.yucca.core.TickEvent;
import com.sampa.yucca.core.TickListener;
import com.sampa.yucca.core.TimeSource;
import com.sampa.yucca.core.Timer;
import com.sampa.yucca.core.ControlListener;

/**
 * Created by cclamb on 6/21/15.
 */
public class HandlerTimer implements Timer, TickListener {

    private TimeSource source;
    private TimerState state;

    private final ControlListener listener;

    public HandlerTimer(ControlListener listener, TimeSource source) {
        this.listener = listener;
        this.source = source;
        source.addTickListener(this);
        state = TimerState.stopped;
    }

    @Override
    public void start() {
        if (state != TimerState.paused || state != TimerState.stopped) {
            return;
        }
        source.start();
        state = TimerState.started;
        listener.started();
    }

    @Override
    public void pause() {
        listener.pausing();
        source.pause();
        state = TimerState.paused;
        listener.paused();
    }

    @Override
    public void stop() {
        listener.stopping();
        source.stop();
        state = TimerState.stopped;
        listener.stopped();
    }

    @Override
    public void tick(TickEvent e) {

    }

    private enum TimerState { started, paused, stopped }
}

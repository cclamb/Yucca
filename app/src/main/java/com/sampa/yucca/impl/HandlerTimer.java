package com.sampa.yucca.impl;

import android.os.Handler;
import android.util.Log;

import com.sampa.yucca.core.TickEvent;
import com.sampa.yucca.core.Timer;
import com.sampa.yucca.core.TimerListener;

/**
 * Created by cclamb on 6/21/15.
 */
public class HandlerTimer implements Timer {

    private static final int INTERVAL = 100;

    private Handler handler;
    private Runnable runner;
    private TimerState state;

    private final TimerListener listener;

    public HandlerTimer(TimerListener listener) {
        this.listener = listener;
        state = TimerState.stopped;
    }

    @Override
    public void start() {
        listener.starting();
        switch (state) {
            case stopped:
                startNewTimer();
                break;
            case paused:
                startTimer();
                break;
            default:
                throw new IllegalStateException();
        }
        state = TimerState.started;
        listener.started();
    }

    private void startNewTimer() {
        handler = new Handler();
        startTimer();
    }

    private void startTimer() {
        runner = new TimerRunner();
        handler.postDelayed(runner, INTERVAL);
    }

    @Override
    public void pause() {
        listener.pausing();
        state = TimerState.paused;
        listener.paused();
    }

    @Override
    public void stop() {
        listener.stopping();
        handler.removeCallbacks(runner);
        state = TimerState.stopped;
        listener.stopped();
    }

    private class TimerRunner implements Runnable {

        @Override
        public void run() {
            listener.tick(new TickEvent());
            Log.d("TR", "tick");
            if (handler != null) {
                handler.postDelayed(this, INTERVAL);
            }
        }
    }

    private enum TimerState { started, paused, stopped }
}

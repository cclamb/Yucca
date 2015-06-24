package com.sampa.yucca.impl;

import android.os.Handler;
import android.util.Log;

import com.sampa.yucca.core.Timer;

/**
 * Created by cclamb on 6/21/15.
 */
public class HandlerTimer implements Timer {

    private static final int INTERVAL = 100;

    private Handler handler;
    private Runnable runner;
    private TimerState state;

    public HandlerTimer() {
        state = TimerState.stopped;
    }

    @Override
    public void start() {
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
        state = TimerState.paused;
    }

    @Override
    public void stop() {
        handler.removeCallbacks(runner);
        state = TimerState.stopped;
    }

    private class TimerRunner implements Runnable {

        @Override
        public void run() {
            Log.d("TR", "tick");
        }
    }

    private enum TimerState { started, paused, stopped }
}

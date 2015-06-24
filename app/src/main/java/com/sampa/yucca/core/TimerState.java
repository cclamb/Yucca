package com.sampa.yucca.core;

/**
 * Created by cclamb on 6/20/15.
 */
public interface TimerState {
    void starting();
    void started();
    void tick();
    void stopping();
    void stopped();
}

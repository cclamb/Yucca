package com.sampa.yucca.core;

/**
 * Created by cclamb on 6/20/15.
 */
public interface TimerListener {
    void starting();
    void started();
    void tick(TickEvent e);
    void pausing();
    void paused();
    void stopping();
    void stopped();
}

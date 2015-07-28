package com.sampa.yucca.core;

/**
 * Created by cclamb on 7/28/15.
 */
public interface TimeSource {
    void addTickListener(TickListener l);
    void removeTickListener(TickListener l);
    void removeAllTickListeners();
    void start();
    void pause();
    void stop();
}

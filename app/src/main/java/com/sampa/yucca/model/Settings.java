package com.sampa.yucca.model;

import java.io.Serializable;

/**
 * Created by cclamb on 7/28/15.
 */
public final class Settings implements Serializable {

    private static final long serialVersionUID = 0x01;

    private static byte DEFAULT_INTERVAL = 100;

    private byte interval;

    public Settings() {
        this(DEFAULT_INTERVAL);
    }

    public Settings(byte interval) {
        this.interval = interval;
    }

    public void setInterval(byte interval) {
        this.interval = interval;
    }
    public byte getInterval() {
        return interval;
    }
}

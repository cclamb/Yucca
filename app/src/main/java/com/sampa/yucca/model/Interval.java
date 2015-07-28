package com.sampa.yucca.model;

import java.io.Serializable;

/**
 * Created by cclamb on 6/20/15.
 */
public final class Interval implements Serializable {

    private static final long serialVersionUID = 0x01;

    private final long duration;

    public Interval(long duration) {
        this.duration = duration;
    }
}

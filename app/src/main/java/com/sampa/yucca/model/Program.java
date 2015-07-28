package com.sampa.yucca.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by cclamb on 6/20/15.
 */
public final class Program implements Serializable {

    private static final long serialVersionUID = 0x01;

    private final Collection<Interval> intervals = new ArrayList<>();

    public Program() {}
    public Program(Collection<Interval> intervals) {
        this.intervals.addAll(intervals);
    }
    public Program(Program p) {
        this.intervals.addAll(p.intervals);
    }

    public void addInterval(Interval i) {
        this.intervals.add(i);
    }

    public void addAllIntervals(Collection<Interval> intervals) {
        this.intervals.addAll(intervals);
    }

    public void removeInterval(Interval i) {
        this.intervals.remove(i);
    }

    public void removeAllIntervals(Collection<Interval> intervals) {
        this.intervals.removeAll(intervals);
    }

    public void clear() {
        this.intervals.clear();
    }

}

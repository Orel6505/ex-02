package com.sapir.race;

public class Track {

    private int finishedRacers = 0;

    public synchronized int racerFinished() {
        finishedRacers++;
        return finishedRacers;
    }
}

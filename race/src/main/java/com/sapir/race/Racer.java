package com.sapir.race;

public class Racer implements Runnable {

    private static int globalId = 1;

    private int id;
    private int speed;
    private Track track;

    public Racer(int speed, Track track) {
        if (speed < 1 || speed > 10) {
            System.out.println("Error: speed must be between 1 and 10");
            return;
        }
        this.speed = speed;
        this.track = track;

        this.id = globalId;
        globalId++;
    }

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        current.setPriority(speed);

        for (int i = 1; i <= 100; i++) {
            System.out.println("Runner " + id + " ran " + i + " meters");

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int place = track.racerFinished();
        System.out.println("Runner " + id + " finished " + formatPlace(place));
    }

    private String formatPlace(int place) {
        switch (place) {
            case 1:
                return "1st";
            case 2:
                return "2nd";
            case 3:
                return "3rd";
            default:
                return place + "th";
        }
    }
}

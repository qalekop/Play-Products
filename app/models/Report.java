package models;

import play.Logger;

public class Report {

    private final static short DEFAULT_DURATION = 5;
    private final static short MILLISECS_IN_SEC = 1000;

    private String name;
    private short duration;

    public Report(String name) {
        this.name = name;
        this.duration = DEFAULT_DURATION;
    }

    public Report(String name, short duration) {
        this.name = name;
        this.duration = duration;
    }

    public void execute() {
        long start = System.currentTimeMillis();
        Logger.info("starting intensive " + name + " report at " + start);
        try {
            Thread.sleep(duration * MILLISECS_IN_SEC);
        } catch(Exception e) {
            // do nothing
        }
        Logger.info("done with intensive " + name + " report ");
        Logger.info("took " + ((System.currentTimeMillis() - start) / MILLISECS_IN_SEC) + "s");
    }

    public String toString() {
        return name;
    }
}

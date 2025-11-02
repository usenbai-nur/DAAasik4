package util;

public class Timer {
    private long startTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public double stop() {
        return (System.nanoTime() - startTime) / 1_000_000.0;
    }
}

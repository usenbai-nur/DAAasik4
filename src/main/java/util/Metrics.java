package util;

public class Metrics {
    private long operations;
    private double timeMs;

    public void addOperation() {
        operations++;
    }

    public void setTimeMs(double timeMs) {
        this.timeMs = timeMs;
    }

    public long getOperations() {
        return operations;
    }

    public double getTimeMs() {
        return timeMs;
    }

    @Override
    public String toString() {
        return String.format("Operations: %d | Time: %.3f ms", operations, timeMs);
    }
}

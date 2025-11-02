package util;

public class Metrics {
    private long start;
    private int ops;

    public void start() {
        start = System.nanoTime();
        ops = 0;
    }

    public void inc() {
        ops++;
    }

    public void stop(String label) {
        long end = System.nanoTime() - start;
        System.out.printf("%s: %d ops, %.3f ms%n", label, ops, end / 1_000_000.0);
    }
}

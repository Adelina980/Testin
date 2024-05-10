package prosseses;

public class SummTaskSync extends Thread {
    private static final Object lock = new Object();
    private int N = 1000;
    private double a;
    private double b;
    private double sum = 0;

    public double getSum() {
        return sum;
    }

    public SummTaskSync(double a, double b) {
        this.a = a;
        this.b = b;
    }

    private double func(double x) {
        return Math.sin(Math.exp(x));
    }

    public void run() {
        double h = (b - a) / N;
        double localSum = 0;
        for (int i = 0; i < N; i++) {
            localSum += (func(a + i * h) + func(a + (i + 1) * h)) * h / 2;
        }

        synchronized (lock) {
            sum += localSum;
        }
    }
}


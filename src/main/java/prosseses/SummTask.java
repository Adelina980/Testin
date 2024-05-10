package prosseses;

public class SummTask extends Thread {
    private int N = 1000;
    private double a;
    private double b;
    private double sum = 0;
    public double getSum() {
        return sum;
    }
    public SummTask(double a, double b) {
        this.a = a;
        this.b = b;
    }
    private double func(double x) {
        return Math.sin(Math.exp(x));
    }
    public void run() {
        double h = (b - a) / N;
        for (int i = 0; i < N; i++) {
            sum += (func(a + i * h) + func(a + (i + 1) * h)) * h / 2;
        }
    }
}

package prosseses;

import java.util.ArrayList;
import java.util.List;

public class MainSync {
    public static void main(String[] args) {
        long start1 = System.nanoTime();
        SummTaskSync t1 = new SummTaskSync(0, 1);
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end1 = System.nanoTime();
        System.out.println("количество потоков: 1");
        System.out.println("nanosecond: " + (end1 - start1));
        System.out.println(t1.getSum());

        List<SummTaskSync> lst = new ArrayList<>();
        int n = Runtime.getRuntime().availableProcessors();
        double h = 1.0 / n;
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            double startInterval = i * h;
            double endInterval = (i + 1) * h;
            SummTaskSync task = new SummTaskSync(startInterval, endInterval);
            lst.add(task);
            task.start();
        }

        lst.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        double sum = lst.stream().mapToDouble(SummTaskSync::getSum).sum();
        long end = System.nanoTime();
        System.out.println("количество потоков: " + n);
        System.out.println("nanosecond: " + (end - start));
        System.out.println(sum);
    }
}

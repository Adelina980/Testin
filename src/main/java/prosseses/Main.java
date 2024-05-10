package prosseses;

import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        long start1 = System.nanoTime();
        SummTask t1 = new SummTask(0, 1);
        t1.start();
        long end1 = System.nanoTime();
        System.out.println("количество потоков: 1");
        System.out.println("nanosecond: "+(end1 - start1));
        System.out.println(t1.getSum());

        List<SummTask> lst = new ArrayList<>();
        int n = Runtime.getRuntime().availableProcessors();
        double h = 1.0 / n;
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            double startInterval = i * h;
            double endInterval = (i + 1) * h;
            SummTask task = new SummTask(startInterval, endInterval);
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
        double sum = lst.stream().mapToDouble(SummTask::getSum).sum();
        long end = System.nanoTime();
        System.out.println("количество потоков: "+n);
        System.out.println("nanosecond: "+ (end - start));
        System.out.println(sum);

    }
}

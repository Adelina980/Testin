package prosseses;

import java.util.ArrayList;
import java.util.List;

public class Works {
    private static Thread[] threads;
    private static Long[] durationWithDependencies;
    private static Long[] durationWithoutDependencies;

    public static void main(String[] args) {
        List<BuildTask> tasks = new ArrayList<>();
        tasks.add(new BuildTask(0,"Начало работ", 0, new ArrayList<>()));
        tasks.add(new BuildTask(1,"Проект", 7, List.of(0)));
        tasks.add(new BuildTask(2, "Изготовление окон", 7, List.of(1)));
        tasks.add(new BuildTask(3, "Изготовление дверей", 7, List.of(1)));
        tasks.add(new BuildTask(4,"Возведение фундамента", 14, List.of(1)));
        tasks.add(new BuildTask(5,"Прокладка коммуникаций", 4, List.of(4)));
        tasks.add(new BuildTask(6,"Возведение стен", 14, List.of(4)));
        tasks.add(new BuildTask(7,"Возведение крыши", 3, List.of(6)));
        tasks.add(new BuildTask(8,"Установка окон", 1, List.of(2, 7)));
        tasks.add(new BuildTask(9,"Установка дверей", 1, List.of(3, 7)));
        tasks.add(new BuildTask(10,"Установка отопительных приборов (батарей)", 3, List.of(5, 8, 9)));
        tasks.add(new BuildTask(11,"Прокладка электропроводки", 3, List.of(5, 7)));
        tasks.add(new BuildTask(12,"Отделка стен и потолка", 7, List.of(8, 9, 11)));
        tasks.add(new BuildTask(13,"Отделка пола", 7, List.of(12))); // 13
        tasks.add(new BuildTask(14,"Установка осветительных приборов", 1, List.of(12)));
        tasks.add(new BuildTask(15,"Установка сантехники", 2, List.of(5, 11, 12)));
        tasks.add(new BuildTask(16,"Завершение строительства", 0, List.of(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)));

        threads = new Thread[tasks.size()];
        durationWithDependencies = new Long[tasks.size()];
        durationWithoutDependencies = new Long[tasks.size()];

        long startTime = System.nanoTime();

        for (int i = 0; i < tasks.size(); i++) {
            threads[i] = new Thread(tasks.get(i));
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }

        long endTime = System.nanoTime();
        System.out.println("Общая длительность всего строительства: " + (endTime - startTime) / 1000000000);

        List<Brigade> brigades = new ArrayList<>();
        brigades.add(new Brigade("Каменщики", List.of(1, 4, 6)));
        brigades.add(new Brigade("Плотники", List.of(1, 2, 3, 7, 8, 9)));
        brigades.add(new Brigade("Сантехники", List.of(1, 5, 10, 15)));
        brigades.add(new Brigade("Штукатуры", List.of(1, 12, 13)));
        brigades.add(new Brigade("Электрики", List.of(1, 11, 14)));

        for (int i = 0; i < brigades.size(); i++) {
            brigades.get(i).playingTime();
        }
    }

    static class BuildTask implements Runnable {
        int id;
        String name;
        int days;
        List<Integer> dependencies;

        public BuildTask(int id, String name, int days, List<Integer> dependencies) {
            this.id = id;
            this.name = name;
            this.days = days;
            this.dependencies = dependencies;
        }

        @Override
        public void run() {
            long startTimeFullTime = System.nanoTime();
            try {
                for (Integer dependency : dependencies) {
                    threads[dependency].join();
                }
            } catch (InterruptedException e) {
                e.fillInStackTrace();
            }

            System.out.println("Start " + name);
            long startTimeLocalTime = System.nanoTime();
            try {
                Thread.sleep(1000L * days);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            long endTimeLocalTime = System.nanoTime();
            long endTimeFullTime = System.nanoTime();
            System.out.println("End " + name);
            durationWithDependencies[id] = (endTimeFullTime - startTimeFullTime) / 1000000000;
            durationWithoutDependencies[id] = (durationWithDependencies[id] - (endTimeLocalTime - startTimeLocalTime) / 1000000000);
        }
    }
    static class Brigade {
        String specialization;
        List<Integer> jobs;

        public Brigade(String specialization, List<Integer> jobs) {
            this.specialization = specialization;
            this.jobs = jobs;
        }

        public void playingTime() {
            int time = 0;
            for (int i = 1; i < jobs.size(); i++) {
                if (durationWithoutDependencies[jobs.get(i)] - durationWithDependencies[jobs.get(i-1)] > 0) {
                    time += durationWithoutDependencies[jobs.get(i)] - durationWithDependencies[jobs.get(i-1)];
                }
            }
            System.out.println("Время игры бригады " + specialization + ": " + time);
        }
    }
}

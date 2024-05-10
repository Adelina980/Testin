package prosseses;

public class PrintTask implements Runnable {
    private String data;

    public PrintTask(String data) {
        this.data = data;
    }

    @Override
    public void run() {
        for (int i = 0; i < data.length(); ++i) {
            System.out.println(data.charAt(i));
        }
    }
}
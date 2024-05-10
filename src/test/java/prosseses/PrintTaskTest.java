package prosseses;

public class PrintTaskTest  {
    public static void main(String[] args) {
        int n = Runtime.getRuntime().availableProcessors();
        System.out.println(n);
        String data = "Вы также можете сопоставлять правила с помощью метода HTTP. Это удобно при авторизации с помощью предоставленных...";
        /*Thread thread1 = new Thread(new PrintTask(data));
        Thread thread2 = new Thread(new PrintTask(data));*/

        /*Thread thread1 = new Thread(() -> {
            for (int i = 0; i < data.length(); ++i){
                System.out.println(data.charAt(i));
            }
            System.out.println();
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < data.length(); ++i){
                System.out.println(data.charAt(i));
            }
            System.out.println();
        });*/

        /*Thread thread1 = new PrintTaskThread(data);
        Thread thread2 = new PrintTaskThread(data);
        thread1.start();
        thread2.start();*/
    }

}
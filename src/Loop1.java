
public class Loop1 {

    private static volatile int counter = 0;

    private static int getIncrementedCounter() {
        return ++counter;
    }

    public static void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(getIncrementedCounter() + ": " + Thread.currentThread().getName() + " (" + i + ")");
        }
    }

    public static void main(String[] args) {
        new Thread(() -> run()).start();
        new Thread(() -> run()).start();
        new Thread(() -> run()).start();
    }

}

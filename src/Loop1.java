
public class Loop1 {

    private static volatile int counter = 0;

    private static int getIncrementedCounter() {
        return ++counter;
    }

    public static void run(String name) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(getIncrementedCounter() + ": " + name + " (" + i + ")");
        }
    }

    public static void main(String[] args) {
        new Thread(() -> run("Thread 1")).start();
        new Thread(() -> run("Thread 2")).start();
        new Thread(() -> run("Thread 3")).start();
    }

}

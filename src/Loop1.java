
public class Loop1 implements Runnable {

    private String myName;

    public Loop1(String name) {
        myName = name;
    }

    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(myName + " (" + i + ")");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Loop1("Thread 1"));
        Thread t2 = new Thread(new Loop1("Thread 2"));
        Thread t3 = new Thread(new Loop1("Thread 3"));

        t1.start();
        t2.start();
        t3.start();
    }

}

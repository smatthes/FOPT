
public class Loop1 {
    
    public static void run(String name) {
        for(int i=1; i<=100; i++) { 
            System.out.println(name + " (" + i + ")"); 
        }
    }

    public static void main(String[] args) {
        new Thread(() -> run("Thread 1")).start();        
        new Thread(() -> run("Thread 2")).start();        
        new Thread(() -> run("Thread 3")).start();
    }

}

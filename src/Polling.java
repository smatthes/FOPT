
public class Polling extends Thread {
    
    public Polling() {
        start();
    }
    
    public void run() {
        int i = 0;
        
        while(!isInterrupted()) {
            System.out.println("Hallo Welt (" + ++i + ")");
        }
    }

    public static void main(String[] args) {
        Polling p = new Polling();
        
        try {
            Thread.sleep(5000);
        }
        catch(InterruptedException ex) {
            
        }
        
        System.out.println("isAlive liefert : " + p.isAlive());
        
        int i = 0;
        p.interrupt();
        
        while(p.isAlive()) {
            System.out.println("Thread lebt immer noch");
            System.out.println("Thread lebte noch " + ++i + " Durchläufe/Durchlauf");
        }
    }

}

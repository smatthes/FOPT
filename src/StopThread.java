
public class StopThread extends Thread {

    public StopThread() {
        start();
    }

    public void run() {
        int i = 0;

        try {
            while (!isInterrupted()) {
                i++;
                System.out.println("Hallo Welt (" + i + ")");
                Thread.sleep(3_000);
            }                       
        } catch (InterruptedException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
        
        System.out.println("Thread endet jetzt ...");
    }

    public static void main(String[] args) {
        StopThread st = new StopThread();

        try {
            Thread.sleep(9_100);
        } catch (InterruptedException ex) {
            // System.err.println(ex.getLocalizedMessage());
        }

        st.interrupt();
    }

}
